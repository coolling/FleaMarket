package client.socket;

import client.base.Base;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SaveChangeSocket {
    public static String SaveChangeServe(String userId,String name,String nickname,String sex,String grade,String tele,String major,String area) throws IOException {
        String result = "1";
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket(addr, Base.editPort);
        System.out.print("请求连接");
        try {
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//客户端输入流，接收服务器消息
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
            //接受用户信息
            String msg=null;


            JSONObject joson = new JSONObject();
            //string
            joson.put("userId", userId);
            //int
            joson.put("userSex", sex);
            joson.put("userAddress", area);
            joson.put("userMajor", major);
            joson.put("trueName", name);
            joson.put("userTel", tele);
            joson.put("userWebname", nickname);
            joson.put("userGrade", grade);
            String jsonString = joson.toString();
            msg = jsonString;
            System.out.println(msg);
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

