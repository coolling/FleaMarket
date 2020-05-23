package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class OnlineServer {
    static final int PORT = 9999;
    ServerSocket OLServer;
    public OnlineServer() throws IOException {
        OLServer = new ServerSocket(PORT);//创建一个等待连接的ServerSocket对象调用Serversocket对象的accept()方法侦听接收客户端的连接请求
    }
    public void open() throws IOException {
        Socket socket =null;//等待并取出用户连接，并创建套接字
        while (true){
            try {
                socket =OLServer.accept();
                JSONObject send = new JSONObject();
                String msg = null;

                System.out.println("Chat：" + socket.getLocalAddress() + "：" + socket.getLocalPort());
                //打印客户端信息

                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String me = br.readLine();
                JSONObject json = JSONObject.parseObject(me);

                OnlineThread olThread = new OnlineThread(socket);
                ManageClientThread.addHashMap(json.getString("userId"));
                ManageClientThread.addClientThread(json.getString("aidId"),olThread,json.getString("userId"));

                //此处用于实现获取客户端的用户名；
                olThread.start();
                //单开一个线程，并将其启动，使其与客户端保持通讯;


            } //如果客户端断开连接，则应捕获该异常，但不应中断整个while循环，使得服务器能继续与其他客户端通信
            catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
//            } finally {
//                if (null != socket) {
//                    try {
//                        socket.close(); //断开连接
//                        System.out.println("Connection closed！");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    }
}
