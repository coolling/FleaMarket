package Sever_mechine.Comment;

import Sever_mechine.PASS.Comments_Users;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Comments_add {
    static final int PORT = 6601;
    ServerSocket Comments_add_server;

    public Comments_add() throws IOException {
        Comments_add_server = new ServerSocket(PORT);//创建一个等待连接的ServerSocket对象调用Sersocket对象的accept()方法侦听接收客户端的连接请求
    }
    public void add_serve() throws IOException {
        Socket socket =null;//等待并取出用户连接，并创建套接字
        while (true){
            try {
                socket = Comments_add_server.accept();
                System.out.println("A new one want to add comments：" + socket.getLocalAddress() + "：" + socket.getLocalPort()); //客户端信息
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String me = br.readLine();
                JSONObject json = JSONObject.parseObject(me);
                int id = json.getIntValue("goodId");
                String maker = json.getString("makerId");
                String comments = json.getString("comment");
                String goodUser = Comments_Users.getGoodOwner(id);
                String makerName = Comments_Users.getWebname(maker);
                int flag = Comments.add(id,maker,comments,makerName,goodUser);
                JSONObject json1 = new JSONObject();
                json1.put("flag",flag);
                String msg = json1.toString();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
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
    public static void main() throws IOException {
        new Sever_mechine.Comment.Comments_add().add_serve();
    }
}