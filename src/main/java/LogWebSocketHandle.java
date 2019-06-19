/**
 * @author fengcaiwen
 * @since 6/17/2019
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 标注的是多实例 的
 * @author Administrator
 *
 */
@ServerEndpoint("/log")  // 服务点
public class LogWebSocketHandle {
    private int count = 0;
    /**
     * 新的WebSocket请求开启
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen start");
        try {

            Basic basicRemote = session.getBasicRemote();
            URI requestURI = session.getRequestURI();
            basicRemote.sendText(requestURI.getPath()+requestURI.getQuery());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("client message "+message);
        final Session session1 = session;
        try {
            session.getBasicRemote().sendText(" say count "+(++count));
            session.getBasicRemote().flushBatch();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        while(true){
                            session1.getBasicRemote().sendText("hello benny" + (++count)+"\r\n");
                            session1.getBasicRemote().flushBatch();
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * WebSocket请求关闭
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        System.out.println("onClose end");
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }


}
