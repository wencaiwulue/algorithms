package demo.ORMTest;

import demo.ORMTest.bean.User;
import demo.ORMTest.util.Result2BeanTools;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author fengcaiwen
 * @since 6/26/2019
 */
@SuppressWarnings("unchecked")
public class JdbcTemplate<T> {

    // jdbc url
    private String url;

    // Normally at least "user" and "password" properties should be included
    private Properties properties;

    // available connection
    private static ConcurrentLinkedDeque<Connection> connectionPool = new ConcurrentLinkedDeque<>();

    private static LongAdder coreSize = new LongAdder();

    public JdbcTemplate() {
        // if do not set data source, use default
        String user = "root";
        String password = "12345678";
        String url = "jdbc:mysql://localhost:3306/test";
        Properties p = new Properties();
        p.setProperty("user", user);
        p.setProperty("password", password);

        this.properties = p;
        this.url = url;
        try {
            init(url, p);
            coreSize.add(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JdbcTemplate(String url, Properties p) {
        this.url = url;
        this.properties = p;
        try {
            init(url, p);
            coreSize.add(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init(String url, Properties p) throws SQLException {

        Driver driver = new com.mysql.cj.jdbc.Driver();

        // batch initial connect and add to connection pool
        for (int i = 0; i < 10; i++) {
            Connection connect = driver.connect(url, p);
            connectionPool.add(connect);
            coreSize.increment();
        }
    }

    private Connection getConnect() {
        Connection first = connectionPool.pollFirst();
        try {
            if (first == null || first.isClosed()) {
                init(url, properties);
                first = connectionPool.getFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return first;
    }

    public List<T> query(String sql, Class<T> tClass) throws Throwable {
        Connection connect = getConnect();
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet set = ps.executeQuery(sql);
        // reuse connection
        connectionPool.add(connect);
        return Result2BeanTools.trans(set, tClass);
    }

    public int update(String sql) {
        Connection connect = getConnect();
        try {
            connect.setAutoCommit(false);
            connect.createStatement();
            PreparedStatement ps = connect.prepareStatement(sql);
            int i = ps.executeUpdate();
            connectionPool.add(connect);
            int c = 1 / 0;
            return i;
        } catch (Throwable e) {
            try {
                connect.rollback();
            } catch (Throwable e1) {
                if (e1.getMessage().contains("SQLSyntaxErrorException"))
                    System.out.println("bad grammar");
                else
                    e1.printStackTrace();
                e1.printStackTrace();
            }
            if (e.getMessage().contains("SQLSyntaxErrorException"))
                System.out.println("bad grammar");
            else
                e.printStackTrace();
        } finally {
            try {
                connect.setAutoCommit(true);
                connectionPool.add(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    public static void main(String[] args) throws Throwable {

        String user = "root";
        String password = "12345678";
        String url = "jdbc:mysql://localhost:3306/test";
        Properties p = new Properties();
        p.setProperty("user", user);
        p.setProperty("password", password);

        String sql = "select * from user";
        Class<User> userClass = User.class;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String updateSql = "delete from user where name='zhangsan' ";
        jdbcTemplate.update(updateSql);
        List<User> query = jdbcTemplate.query(sql, userClass);
        query.forEach(System.out::println);
    }
}
