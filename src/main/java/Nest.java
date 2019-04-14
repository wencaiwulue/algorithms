import java.lang.reflect.Field;

public class Nest {
    public static class X {
        void test() throws Exception {
            Y y = new Y();
            y.y = 1;
            Field field = Y.class.getDeclaredField("y");
            field.setAccessible(true);
            field.setInt(y, 2);
            System.out.println(y.y);
        }
    }

    private static class Y {
        private int y;
    }

    public static void main(String[] args) throws Exception {
        new X().test();
    }
}
