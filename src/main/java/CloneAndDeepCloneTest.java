import java.util.Collections;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 6/5/2019
 */
public class CloneAndDeepCloneTest {
    public static void main(String[] args) {
        List<User> users = Collections.singletonList(new User(1, "a", new User(3, "c")));
        User clone = (User) users.get(0).clone();
        clone.setName("c");
        clone.getSon().setName("d");
        System.out.println(users.get(0).son == clone.son);
        System.out.println(users.get(0).getSon().name + "_clone:" + clone.getSon().getName());
        System.out.println(users.get(0).getName());
        System.out.println(clone.getName());

    }

    public static class User implements Cloneable {
        private Integer id;
        private String name;

        private User son;

        public User() {
        }

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public User(Integer id, String name, User son) {
            this.id = id;
            this.name = name;
            this.son = son;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User getSon() {
            return son;
        }

        public void setSon(User son) {
            this.son = son;
        }

        @Override
        protected Object clone() {
            User user1 = new User();
            user1.setId(this.id);
            user1.setName(this.name);
            // todo 其实这里是关键, 其实从这里可以推出来，如果要Object的clone方法是field-to-field赋值的，
            // todo 也就是如果是基本数据类型，其实也没问题，但是如果是我们diy的对象，那么这样子直接把引用赋值了过去
            // todo 如果这里是diy的嵌套diy的对象，那么就要不停的条用clone，直到最后一个对象只包含基本数据类型的，就可以了
            if (this.son != null) {
                user1.setSon((User) this.son.clone());
            }
            return user1;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class Son implements Cloneable {
        public String name;

        public Son() {
        }

        public Son(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected Son clone() throws CloneNotSupportedException {
            return (Son) super.clone();
        }
    }
}
