/*
 * 服务器与某一个客户端的通讯线程
 */
package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
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
            if(s!=null){
                //这里该线程就可以接收客户端的信息；
                try{
                    sleep(100);
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String msg = br.readLine();
                    JSONObject json = JSONObject.parseObject(msg);
                    System.out.println(msg);
                    OnlineThread ol = ManageClientThread.getClientThread(json.getString("sender"),json.getString("accepter"));
                    //取得接收人的通讯线程

                    if (ol==null){
                        Message.msgAddToTemporary(json.getString("sender"),json.getString("accepter"),json.getString("words"),json.getString("time"));
                        //将信息存到临时聊天记录数据库中;.
                    }
                    else{
                        Message.msgAddToHistory(json.getString("sender"),json.getString("accepter"),json.getString("words"),json.getString("time"));
                        //将信息存到历史聊天记录数据库中;.
                        ObjectOutputStream oos= new ObjectOutputStream(ol.s.getOutputStream());
                        oos.writeObject(JSONObject.parse(msg));
//                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ol.s.getOutputStream()));
//                    PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
//                    pw.println(msg);
                        System.out.println("转发"+msg);
                        //完成转发
                    }
            } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }else {
                return;
            }
        }
    }
}
