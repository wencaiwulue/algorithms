import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple());
//        print(list);
        List<String> stringList = Lists.newArrayList("a", "b", "c", "d", "e");
        Iterator<String> iterator = stringList.iterator();
        int size = stringList.size();
//        for (int i = size-1; i >=0; i--) {
//            if ("e".equals(stringList.get(i))){
//                stringList.remove(i);
//            }
//        }
//        System.out.println("&&&&&&");
//        System.out.println(stringList.size());
//        System.out.println(stringList);
        while (iterator.hasNext()){
            String str = iterator.next();
            if ("c".equals(str)){
                iterator.remove();
            }
        }
        System.out.println(stringList);

    }

    interface Fruit {
    }

    ;

    static class Apple implements Fruit {
    }

    static class Orange implements Fruit {
    }

    public static void print(List<? extends Fruit> list) {
        list.forEach(System.out::println);
    }
}
