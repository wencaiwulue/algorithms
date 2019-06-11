package thread;

/**
 * @author fengcaiwen
 * @since 6/6/2019
 */
public class CooperatePrintTest {
    public static void main(String[] args) {

        new Thread(() -> {
            int i = 0;
            int[] a = new int[]{1, 2, 3, 4, 5};
            for (int i1 : a) {
                System.out.println(i1);
                i++;
            }
        });
    }
}
