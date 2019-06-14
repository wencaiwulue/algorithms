import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("all")
public class BasicTypeReferenceTest {

    public static void main(String[] args) {
        int a = 1;
        int b = a;
        b = 0;
        System.out.println(a + "_" + b);//output:1_0

        Integer aa = 1;
        Integer bb = aa;
        bb = 0;
        System.out.println(aa + "_" + bb);//output:1_0

        String s1 = new String("abc");
        String s2 = s1;
        s2 = "abcd";
        System.out.println(s1 + "_" + s2);//output:abc_abcd

        Double d = 1.12D;
        Double dd = d;
        d = 2.22D;
        System.out.println(d + "_" + dd);//output:2.22_1.12


        Entity entity = new Entity("zhangsan", 1);
        Entity entity1 = entity;
        entity1.setName("lisi");
        entity1.setId(2);

        System.out.println(entity.toString() + "_" + entity1.toString());//output:Entity{name='lisi', id=2}_Entity{name='lisi', id=2}

        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecimal1 = bigDecimal;
        bigDecimal = bigDecimal.add(BigDecimal.ONE);
        System.out.println(bigDecimal + "_" + bigDecimal1);//output:2_1

        String str = Joiner.on("','").appendTo(new StringBuilder("('"), Lists.newArrayList("a", "b", "c")).append("')").toString();
        System.out.println(str);//output:('a','b','c')

        List<String> list = Lists.newArrayList("aa", "bb");
        Map<String, List<String>> temp = new HashMap<>();
        temp.put("key", list);
        list.add("cc");
        System.out.println(temp);//output:{key=[aa, bb, cc]}

        Optional.ofNullable("1").orElse(abc());//output:123


        Optional.ofNullable("1").orElseGet(() -> abc());//output:123
        String s = 1 != 1 ? "" : abc();//output:

        Optional.ofNullable(null).orElseGet(() -> new Entity("", 1));

        List<Entity> entityList = new ArrayList<>(Arrays.asList(new Entity("l", 0), new Entity("Z", 1)));
        List<Entity> entityList0 = entityList;
        entityList.add(new Entity("3", 3));
        System.out.println(entityList0.size());


    }

    public static String abc() {
        System.out.println(123);
        return "123";
    }
}

@SuppressWarnings("all")
class Entity {
    private String name;
    private Integer id;

    public Entity(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
