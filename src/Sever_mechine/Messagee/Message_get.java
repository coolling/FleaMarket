package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Message_get {
    static final int PORT = 9998;
    ServerSocket get_server;

    public Message_get() throws IOException {
        get_server = new ServerSocket(PORT);//创建一个等待连接的ServerSocket对象调用Sersocket对象的accept()方法侦听接收客户端的连接请求
    }
    public void get_serve() throws IOException {
        Socket socket = null;//等待并取出用户连接，并创建套接字
        while (true) {
            try {
                socket = get_server.accept();
                System.out.println("A new one want message：" + socket.getLocalAddress() + "：" + socket.getLocalPort()); //客户端信息
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String me = br.readLine();
                JSONObject json = JSONObject.parseObject(me);
                JSONObject json1 = new JSONObject();
                json1 = Message.msgGet(json.getString("user"),json.getString("aid"));
                String msg = json1.toString();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷新
                pw.println(msg);
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