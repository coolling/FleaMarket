package Sever_mechine.TRY;

import Sever_mechine.User.Users;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class A_Bitch {
    public static int loginServe() throws IOException {
        int result = 1;
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket("192.168.49.1", 2000);
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
            loginJoson.put("userPass", "clx0725.");
            //int
            loginJoson.put("userId", "201900301133");
            String loginString = loginJoson.toString();
            msg = loginString;
            pw.println(msg);

            String strInputstream=br.readLine();
            System.out.println("输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            System.out.println(js.get("flag"));
            result = js.getIntValue("flag");
//            result=Integer.parseInt((String) js.get("flag"));





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
    public static void main(String[] args) throws IOException {
      //  int i = A_Bitch.loginServe();
      //  System.out.println(i);
        System.out.println(Users.infoGet("201900301133"));
    }
}
