package client.socket;

import client.base.Base;
import client.pages.Index;
import client.pages.Mistake;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class RegisterSocket {
    public static String  registerServe(String userId,String pass) throws IOException {
        String result="1";
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket("127.0.0.1", Base.registerPort);
        System.out.print("请求连接");
        try {
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//客户端输入流，接收服务器消息
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
            //接受用户信息
            String msg=null;


            JSONObject loginJoson = new JSONObject();
            //string
            loginJoson.put("userPass", userId);
            //int
            loginJoson.put("userId", pass);
            String loginString = loginJoson.toString();
            msg = loginString;
            pw.println(msg);

            String strInputstream=br.readLine();
            System.out.println("输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            System.out.println(js);

            result=  js.get("flag").toString();





        } catch (IOException e) {
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
        return result;
    }

}
