package demo.ORMTest;

import demo.ORMTest.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author fengcaiwen
 * @since 6/27/2019
 */
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

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
        User user = new User("z", 1);
        long id = jdbcTemplate.insert(user);
        System.out.println(id);
    }

    @Test
    public void batchQuery() throws InterruptedException {
        String sql = "select * from user ";
        Class<User> userClass = User.class;

        CountDownLatch latch = new CountDownLatch(10000);
        List<Thread> threadList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            threadList.add(new Thread(() -> {
                try {
                    jdbcTemplate.query(sql, userClass);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }));
        }
        long start = System.nanoTime();
        threadList.forEach(Thread::start);

        latch.await();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) + "ms");
    }
}