package annotation;

import org.junit.Test;
import org.springframework.stereotype.Component;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * @author fengcaiwen
 * @since 6/18/2019
 */
@SuppressWarnings({"ReflectionForUnavailableAnnotation"})
@Component
public class AnnotationTest {
    @Test
    public void test() throws Throwable {
        boolean annotation = this.getClass().isAnnotationPresent(SuppressWarnings.class);

        System.out.println(annotation);


        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        Class<?> aClass = lookup.findClass("Unsafe.class");
//        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("Unsafe.class");
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        Unsafe o = (Unsafe)theUnsafe.get(Object.class);
        System.out.println(o);

    }
}
