package clone;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

/**
 * a useful tools to avoid shadow clone occur error
 *
 * @author fengcaiwen
 * @since 6/24/2019
 */
public class JsonDeepCloneTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    public void test() throws IOException {
        User zhangsan = new User(1, "zhangsan");
        System.out.println(zhangsan);
        String s = objectMapper.writeValueAsString(zhangsan);
        User clone = objectMapper.readValue(s, User.class);
        System.out.println(clone);
        System.out.println(clone == zhangsan);
        System.out.println(clone.equals(zhangsan));

    }

    public static void main(String[] args) throws IOException {
        new JsonDeepCloneTest().test();
    }

    public static class User {
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

        public User() {
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        // todo check196 without this method, check it out
//        @Override
//        public int hashCode() {
//            return Objects.hash(id, name);
//        }
    }
}
