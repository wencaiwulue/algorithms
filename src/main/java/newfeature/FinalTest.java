package newfeature;

//import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.Unsafe;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

/**
 * @author fengcaiwen
 * @since 8/29/2019
 */
public class FinalTest {

    public static void main(String[] args) throws Exception {

//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        User user = new User();
        System.out.println(user.i);
        Field value = user.i.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(user.i, "666".getBytes());
        System.out.println(user.i);

        Field[] fields = user.getClass().getFields();
        fields[0].setAccessible(true);
        fields[0].set(user, "777");
//        System.out.println(new ObjectMapper().writeValueAsString(user));
        System.out.println(user);

//        Field[] fields = user.getClass().getFields();
//        long l = unsafe.objectFieldOffset(fields[0]);
//        unsafe.getAndSetObject(user, l, "123");
//        fields[0].setAccessible(true);
//        AnnotatedType annotatedType = fields[0].getAnnotatedType();
////        System.out.println(address);
//
//
//        System.out.println(user.i);
//        unsafe.getAndSetObject(user, l, "321");


//        System.out.println(user.i);
        System.out.println(user.i);
//        Field[] fields = user.getClass().getFields();
//        fields[0].setAccessible(true);
//        fields[0].set(user, 1123);
//
//
//        System.out.println(user.i);
////        Field[] fields1 = user.getClass().getFields();
////        fields1[0].setAccessible(true);
////        fields1[0].set(user, 2);
//        fields[0].set(user, 2);
//        System.out.println(user.i);
//
//        long l = unsafe.objectFieldOffset(fields[0]);
//        System.out.println(l);
//        unsafe.getAndSetObject(user, l, 3);
//        System.out.println(user.i);
//        unsafe.getAndSetObject(user, l, 4);
//        System.out.println(user.i);
////        unsafe.setMemory(user, l, 1, Byte.parseByte("5"));
//        unsafe.putAddress(l, 1);
//        System.out.println(user.i);
    }

    public static class User {
        public final String i = "555";

        public User() {
        }

        @Override
        public String toString() {
            return "User{" +
                    "i='" + i + '\'' +
                    '}';
        }
    }
}
