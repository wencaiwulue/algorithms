package newfeature;

/**
 * @author fengcaiwen
 * @since 8/23/2019
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForEachTest {

    public static void main(String[] args) {
        ArrayList<Integer> ints = (ArrayList<Integer>) IntStream.range(1, 1000000).boxed().collect(Collectors.toList());
        LinkedList<Integer> linkedList = new LinkedList<>(ints);
//        foreach(integers);
        fori(ints);
        fori(linkedList);
//        iterator(linkedList);
//        iterator(ints);
    }

    private static void foreach(ArrayList<Integer> integers) {
        long start = System.nanoTime();
        for (Integer integer : integers) {
//            Integer integer1 = integer;
        }
        long end = System.nanoTime();
        System.out.println(end - start + " :ns");
    }

    private static void fori(ArrayList<Integer> integers) {
        long start = System.nanoTime();
        for (int i = 0; i < integers.size(); i++) {
            Integer integer = integers.get(i);
        }
        long end = System.nanoTime();
        System.out.println(end - start + " :ns");
    }

    private static void fori(LinkedList<Integer> integers) {
        long start = System.nanoTime();
        for (int i = 0; i < integers.size(); i++) {
            Integer integer = integers.get(i);
        }
        long end = System.nanoTime();
        System.out.println(end - start + " :ns");
    }

    private static void iterator(LinkedList<Integer> integers) {
        long start = System.nanoTime();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            /*Integer next = */iterator.next();
        }
        long end = System.nanoTime();
        System.out.println(end - start + " :ns");
    }

    private static void iterator(ArrayList<Integer> integers) {
        long start = System.nanoTime();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            /*Integer next = */iterator.next();
        }
        long end = System.nanoTime();
        System.out.println(end - start + " :ns");
    }


}
