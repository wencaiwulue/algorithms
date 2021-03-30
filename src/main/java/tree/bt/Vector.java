package tree.bt;

/**
 * @author fengcaiwen
 * @since 12/13/2019
 */
//todo
public class Vector<E extends Comparable<E>> extends java.util.Vector<E> {

    public Vector() {
        super();
    }

    public Vector(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public synchronized E get(int index) {
        try {
            return super.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public synchronized E elementAt(int index) {
        try {
            return super.elementAt(index);
        } catch (Exception e) {
            return null;
        }
    }

    public int search(E e) {
        int p = -1;
        for (int i = 0; i < super.size(); i++) {
            if (this.get(i) != null && this.get(i).compareTo(e) <= 0) {
                p=i;
            }
        }
        return p;
    }

}
