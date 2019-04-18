import org.springframework.web.reactive.socket.WebSocketHandler;

public class BasicTest {
    public static int x = 1;
    public BasicTest(){
        this.x = 2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        BasicTest.x = x;
    }

    public static void main(String[] args) {
        System.out.println(BasicTest.x);
        BasicTest t1 = new BasicTest();
        BasicTest t2 = new BasicTest();
        t1.setX(3);
        t2.setX(4);
        System.out.println(t1.getX());
        System.out.println(t2.getX());

        Integer i = null;
        System.out.println("asdf"+i);

        WebSocketHandler webSocketHandler;
    }
}
