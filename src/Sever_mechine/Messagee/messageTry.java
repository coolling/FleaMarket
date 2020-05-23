//package Sever_mechine.Messagee;
//
//import com.alibaba.fastjson.JSONObject;
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class messageTry {
//    static final int PORT = 9999;
//    ServerSocket OLServer;
//    public messageTry() throws IOException {
//        OLServer = new ServerSocket(PORT);//创建一个等待连接的ServerSocket对象调用Serversocket对象的accept()方法侦听接收客户端的连接请求
//    }
//    public void open() throws IOException {
//        JSONObject json = new JSONObject();
//        Socket socket =null;//等待并取出用户连接，并创建套接字
//        while (true){
//            try {
//                socket =OLServer.accept();
//                JSONObject send = new JSONObject();
//                String msg = null;
//
//                System.out.println("Chat：" + socket.getLocalAddress() + "：" + socket.getLocalPort());
//                //打印客户端信息
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String me = br.readLine();
//                json = JSONObject.parseObject(me);
//
//                ManageClientThread.addClientThread(json.getString("accepter"),socket);
//                ManageClientThread.addHashMap(json.getString("sender"));
//                //此处用于实现获取客户端的用户名；
//                Socket ol = ManageClientThread.getClientThread(json.getString("sender"),json.getString("accepter"));
//                //取得接收人的通讯线程
//
//                Message.msgAddToHistory(json.getString("sender"),json.getString("accepter"),json.getString("words"),json.getString("time"));
//                //将信息存到历史聊天记录数据库中;.
//                if (ol==null){
//                    Message.msgAddToTemporary(json.getString("sender"),json.getString("accepter"),json.getString("words"),json.getString("time"));
//                    //将信息存到临时聊天记录数据库中;.
//                }
//                else{
//                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ol.getOutputStream()));
//                    PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
//                    pw.println(me);
//                    //完成转发
//                }
//                //单开一个线程，并将其启动，使其与客户端保持通讯;
//
//
//            } //如果客户端断开连接，则应捕获该异常，但不应中断整个while循环，使得服务器能继续与其他客户端通信
//            catch (IOException e) {
//                e.printStackTrace();
//                if (socket==null){
//                    ManageClientThread.closeClientThread(json.getString("userId"),json.getString("aidId"));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                if (socket==null){
//                    ManageClientThread.closeClientThread(json.getString("userId"),json.getString("aidId"));
//                }
//            } /*finally {
//                if (null != socket) {
//                    try {
//                        socket.close(); //断开连接
//                        System.out.println("Connection closed！");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }*/
//        }
//    }
//}
