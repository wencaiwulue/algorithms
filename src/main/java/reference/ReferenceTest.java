package reference;

/**
 * @author fengcaiwen
 * @since 6/28/2019
 */
@SuppressWarnings("all")
public class ReferenceTest {
    public static void main(String[] args) {

        Entity e = new Entity("1",2)/*null*/;
        change(e);
//        unchange(e);
        System.out.println(e);


    }

    public static Entity change(Entity e) {
        e = new Entity("changed", 3);
//        e.setName("changed");

        return new Entity("haa", 1);
    }

    public static void unchange(Entity e) {
        e.setName("unchanged");
    }
}
