package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author fengcaiwen
 * @since 8/14/2019
 */
public class SqlTest {
    public static void main(String[] args) throws SQLException, InterruptedException {
        String url = "jdbc:mysql://localhost:3306/test";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "12345678");
        Connection connect = new com.mysql.cj.jdbc.Driver().connect(url, p);
        // todo: 测试结果表明，无论两种写法都是ok的，可能是因为数据库的行级锁的原因，不过这样有个问题就是数据库的瓶颈，如果要秒杀的商品很多，比如1万个，直接打到库上去可能会炸。网上有许多帖子，有多办法，消息队列或者是缓存，还有曲线救国的暂扣
//        String sql = "update product set quantity = quantity -1 where quantity - 1 >= 0 and id = 1";
        String sql = "update product set quantity = quantity -1 where quantity >= 1 and id = 1";
        connect.setAutoCommit(true);
        connect.createStatement();
        int i = 0;
        while (i++ < 10000) {
            long start = System.currentTimeMillis();
            new Thread(() -> {
                while (true)
                    // 两秒钟够启动2000个左右的线程，机器不同，数量应该不同
                    while (start + 2000 < System.currentTimeMillis()) {
                        try {
                            PreparedStatement ps = connect.prepareStatement(sql);
                            int i1 = ps.executeUpdate();
                            System.out.println(i1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            }).start();
        }
        Thread.sleep(1000000);
    }
}
