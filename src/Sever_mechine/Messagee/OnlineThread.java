/*
* 服务器与某一个客户端的通讯线程
*/
package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;

import java.net.*;
import java.io.*;

public class OnlineThread extends Thread{

    Socket s;

    public OnlineThread (Socket s){
        //把服务器与该客户端的连接赋给s;
        this.s = s;
    }

    public void run() {
        while (true){
            //这里该线程就可以接收客户端的信息；
            try{
                s.setSoTimeout(500);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String msg = br.readLine();
                JSONObject json = JSONObject.parseObject(msg);
                JSONObject back;
                if (json.getString("type").equals("chat"))
                {
                    Message.msgAddToHistory(json.getString("sender"),json.getString("accepter"),json.getString("words"),json.getString("time"));
                    //将信息存到数据库中;.
                    OnlineThread ol = ManageClientThread.getClientThread(json.getString("accepter"));
                    //取得接收人的通讯线程
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ol.s.getOutputStream()));
                    PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
                    pw.println(msg);
                    //完成转发
                   // pw.close();

                }
                if (json.getString("type").equals("getChatter")){
                    back = Message.getChatter(json.getString("user"));
                    msg = back.toString();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    PrintWriter pw = new PrintWriter(bw,true); //装饰输出流，及时刷新
                    pw.println(msg);
                    //pw.close();
                    break;
                }
                else {
                    back = Message.msgGet(json.getString("user"),json.getString("aid"));
                    msg = back.toString();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    PrintWriter pw = new PrintWriter(bw,true); //装饰输出流，及时刷新
                    pw.println(msg);
                    //pw.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
