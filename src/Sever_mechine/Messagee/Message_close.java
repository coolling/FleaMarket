package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Message_close {
    static final int PORT = 9988;
    ServerSocket close_server;

    public Message_close() throws IOException {
        close_server = new ServerSocket(PORT);//创建一个等待连接的ServerSocket对象调用Sersocket对象的accept()方法侦听接收客户端的连接请求
    }
    public void close_serve() throws IOException {
        Socket socket = null;//等待并取出用户连接，并创建套接字
        while (true) {
            try {
                socket = close_server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String me = br.readLine();
                JSONObject json = JSONObject.parseObject(me);
                JSONObject json1 = new JSONObject();
                ManageClientThread.closeClientThread(json.getString("user"),json.getString("aid"));
            } //如果客户端断开连接，则应捕获该异常，但不应中断整个while循环，使得服务器能继续与其他客户端通信
            catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != socket) {
                    try {
                        socket.close(); //断开连接
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}