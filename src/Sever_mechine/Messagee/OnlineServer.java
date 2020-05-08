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

                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                PrintWriter pw=new PrintWriter(bw,true);
                //建立输出流并装饰输出流，及时刷新

                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String me = br.readLine();
                JSONObject json = JSONObject.parseObject(me);
                //建立输入流，并读取客户端发送的信息；

                System.out.println("Chat：" + socket.getLocalAddress() + "：" + socket.getLocalPort());
                //打印客户端信息

                OnlineThread olThread = new OnlineThread(socket);
                ManageClientThread.addClientThread(json.getString("userId"),olThread);
                olThread.start();
                //单开一个线程，并将其启动，使其与客户端保持通讯;
                send.put("flag",0);
                msg = send.toString();
                pw.println(msg);
                //给客户端返回 “ 成功 ” 的信息

            } //如果客户端断开连接，则应捕获该异常，但不应中断整个while循环，使得服务器能继续与其他客户端通信
            catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != socket) {
                    try {
                        socket.close(); //断开连接
                        System.out.println("Connection closed！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
