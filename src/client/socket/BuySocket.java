package client.socket;

import client.base.Base;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class BuySocket {
    public static String buyServe(int goodsId,String id,int amount) throws IOException {
        String re="";
       // InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket("127.0.0.1", Base.buy);
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

            joson.put("userId", id);
            joson.put("goodsId", goodsId);
            joson.put("amount", amount);
            String string = joson.toString();
            msg = string;
            System.out.println(msg+"111");
            pw.println(msg);

            String strInputstream=br.readLine();
            System.out.println("buy输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            //System.out.println(js);
            re=js.getIntValue("flag")+"";




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
        return re;
    }
}
