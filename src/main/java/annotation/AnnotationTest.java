package annotation;

import org.junit.Test;
import org.springframework.stereotype.Component;
import sun.misc.Unsafe;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * {@link https://help.eclipse.org/2018-09/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftask-suppress_warnings.htm}
 *
 * @author fengcaiwen
 * @since 6/18/2019
 */
@SuppressWarnings("all")
@Component
public class AnnotationTest {
    @Test
    public void test() throws Throwable {
        boolean annotation = this.getClass().isAnnotationPresent(SuppressWarnings.class);
        Annotation[] declaredAnnotations = this.getClass().getDeclaredAnnotations();

        System.out.println(declaredAnnotations.length);
        System.out.println(annotation);


        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        Class<?> aClass = lookup.findClass("Unsafe.class");
//        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("Unsafe.class");
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        Unsafe o = (Unsafe) theUnsafe.get(null);
        System.out.println(o);

    }
}
