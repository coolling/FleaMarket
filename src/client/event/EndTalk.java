package client.event;

import client.base.Base;
import client.pages.ModifyingData;
import client.pages.PersonalCenter;
import client.pages.ProductDetails;
import client.pages.Talking;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class EndTalk extends MouseAdapter {
    Object page;
    Talking frame;

    String aid;
    String uesr;
    int type;
    String proid;
    String name;
    Socket s;
    public EndTalk(Socket s, Object page, Talking frame, String uesr, String aid, int type) throws IOException {
        this.page = page;
        this.frame=frame;
        this.uesr=uesr;
        this.aid=aid;
        this.type=type;
        this.s=s;
    }
    public EndTalk(Socket s,Object page, Talking frame, String uesr, String aid, int type, String proid, String name) throws IOException {
        this.page = page;
        this.frame=frame;
        this.uesr=uesr;
        this.aid=aid;
        this.type=type;
        this.name=name;
        this.proid=proid;
        this.s=s;
    }
    public void mouseClicked(MouseEvent e) {

        try {
            frame.getS().close();
            frame.setS(null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (e.getSource() == page){

            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", Base.end);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.print("请求连接");

            try {
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//客户端输入流，接收服务器消息

                PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
                //接受用户信息
                String msg=null;


                JSONObject joson = new JSONObject();
                //string

                joson.put("user", uesr);
                joson.put("aid", aid);

                String string = joson.toString();
                msg = string;
                System.out.println(msg);
                pw.println(msg);







            } catch (IOException qe) {
                qe.printStackTrace();
            } finally {
                if (null != socket) {
                    try {
                        socket.close(); //断开连接
                    } catch (IOException qe) {
                        qe.printStackTrace();
                    }
                }
            }
            try {
                if(type==0){
                    new PersonalCenter(uesr);
                }else{
                    new ProductDetails(proid,name,uesr) ;}

                frame.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}

