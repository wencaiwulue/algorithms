package lock;

/**
 * @author naison
 * @since 4/29/2020 16:10
 */
public class SwitchTest {
    public static void main(String[] args) {
        int i = 2;
        int result = 0;
        switch (i) {
            case 1:
                result = result + 2 * i;
                System.out.println(1);
            case 2:
                result = result + 2 * 1;
                System.out.println(1);
            case 3:
                result = result + 2 * i;
                System.out.println(1);
                break;
        }

        System.out.println(result);
    }
}
