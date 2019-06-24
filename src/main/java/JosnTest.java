import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 6/24/2019
 */
public class JosnTest {
    @Autowired
    private ObjectMapper objectMapper;


    public void test() throws IOException {
        List<TestObject> list = new ArrayList<>();
        list.add(new TestObject(1, "zhangsan"));
        list.add(new TestObject(2, "lisi"));
        System.out.println(list.get(0));
        String s = objectMapper.writeValueAsString(list.get(0));
        TestObject testObject = objectMapper.readValue(s, TestObject.class);
        System.out.println(testObject);

    }

    public static void main(String[] args) throws IOException {
        new JosnTest().test();
    }

    public static class TestObject {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TestObject(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
