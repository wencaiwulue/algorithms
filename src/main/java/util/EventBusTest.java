package util;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@SuppressWarnings("UnstableApiUsage")
public class EventBusTest {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        bus.register(new Registry1());
        bus.post("hi");
    }


}

class Registry1 {
    @Subscribe
    public void test(String s) {
        System.out.println("test");
    }

    @Subscribe
    public void test1(long s) {
        System.out.println(s);
        System.out.println("test1");
    }

    @Subscribe
    public void test(int s) {
        System.out.println(s);
        System.out.println("test1");
    }

}
