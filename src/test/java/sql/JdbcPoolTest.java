package sql;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author fengcaiwen
 * @since 6/27/2019
 */
public class JdbcPoolTest {
    @Autowired
    private static JdbcPool jdbcTemplate;

    static {
        String url = "jdbc:mysql://localhost:3306/test";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "12345678");
        jdbcTemplate = new JdbcPool(url, p);
    }

    @Test
    public void query() throws Throwable {
        String sql = "select * from user ";
        Class<User> clazz = User.class;
        List<User> userList = jdbcTemplate.query(sql, clazz);
        userList.forEach(System.out::println);
    }

    @Test
    public void update() throws Throwable {
        String updateSql = "delete from user where name='zhangsan' ";
        jdbcTemplate.update(updateSql);
        query();
    }

    @Test
    public void insert() {
        User user = new User("b", 1);
        long id = jdbcTemplate.insert(user);
        System.out.println(id);
    }

    @Test
    public void batchQuery() throws InterruptedException {
        String sql = "select * from user ";
        Class<User> userClass = User.class;

        int threadNum = Runtime.getRuntime().availableProcessors();
        System.out.println(threadNum);
        CountDownLatch latch = new CountDownLatch(threadNum);
        List<Thread> threadList = new LinkedList<>();
        for (int i = 0; i < threadNum; i++)
            threadList.add(new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++)
                        jdbcTemplate.query(sql, userClass);

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }));

        long start = System.nanoTime();
        threadList.forEach(Thread::start);

        latch.await();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) + "ms");
    }

    public static class User {

        public String name;
        public Integer gender;


        public User() {
        }

        public User(String name, Integer gender) {
            this.name = name;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

}