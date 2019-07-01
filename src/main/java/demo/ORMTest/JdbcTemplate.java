package demo.ORMTest;

import demo.ORMTest.util.Result2BeanTools;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
@Component
public class JdbcTemplate<T> {

    // jdbc url
    private String url;

    // Normally at least "user" and "password" properties should be included
    private Properties properties;

    private static Driver driver;

    // available connection
    private static ConcurrentLinkedDeque<Connection> connectionPool = new ConcurrentLinkedDeque<>();

    private static final int coreSize = 32;
    private static volatile LongAdder current = new LongAdder();

    public JdbcTemplate() {
        // if do not set data source, use default
        String url = "jdbc:mysql://localhost:3306/test";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "12345678");

        this.properties = p;
        this.url = url;
        init(1);
    }

    public JdbcTemplate(String url, Properties p) {
        this.url = url;
        this.properties = p;
        init(1);
    }

    private void init(int n) {
        try {
            if (driver == null)
                driver = new com.mysql.cj.jdbc.Driver();

            // batch initial connect and add to connection pool
            for (int i = 0; i < n; i++) {
                Connection connect = driver.connect(url, properties);
                connectionPool.add(connect);
                current.increment();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        while (true) {
            if (!connectionPool.isEmpty()) {
                Connection first = connectionPool.pollFirst();
                try {
                    if (first != null && first.isValid(0)) {
                        return first;
                    }
                } catch (SQLException ignored) {
                }
            } else if (current.intValue() < coreSize) {
                // todo optimize
                synchronized (this) {
                    if (current.intValue() < coreSize)
                        init(1);
                }
            }

        }
    }

    public List<T> query(String sql, Class<T> tClass) throws Throwable {
        Connection connect = getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet set = ps.executeQuery(sql);
        // reuse connection
        connectionPool.add(connect);
        return Result2BeanTools.transfer(set, tClass);
    }

    public int update(String sql) {
        Connection connect = getConnection();
        try {
            connect.setAutoCommit(false);
            connect.createStatement();
            PreparedStatement ps = connect.prepareStatement(sql);
            return ps.executeUpdate();
        } catch (Throwable e) {
            try {
                connect.rollback();
            } catch (Throwable e1) {
                e1.printStackTrace();
            }
            if (e.getMessage().contains("SQLSyntaxErrorException"))
                System.out.println("bad grammar");
            else
                e.printStackTrace();
        } finally {
            try {
                // reuse
                connect.setAutoCommit(true);
                connectionPool.add(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public long insert(T t) {
        Connection connect = getConnection();
        String sql = Result2BeanTools.generateSql(t);
        System.out.println(sql);
        try {
            connect.setAutoCommit(false);
            PreparedStatement ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connect.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionPool.add(connect);
        }
        // todo
        return 1;
    }
}
