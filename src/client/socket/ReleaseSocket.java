package client.socket;

import client.base.Base;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ReleaseSocket {
    public static String  ReleaseServer(String userId,String name,String price,String detail,String amout) throws IOException {
        String result="1";
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket("127.0.0.1", Base.releasePort);
        System.out.print("请求连接");
        String base64;
        try {
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//客户端输入流，接收服务器消息
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，及时刷新
            //接受用户信息
            String msg=null;


            JSONObject joson = new JSONObject();
            //string
            File file = new File(System.getProperty("user.dir")+"/src/client/img/prosource.jpg");
            FileInputStream inputFile = null;
            try {
                inputFile = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            byte[] buffer = new byte[(int) file.length()];
            try {
                inputFile.read(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                inputFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            base64=(new BASE64Encoder().encode(buffer));

            joson.put("userId", userId);
            joson.put("goodsName", name);
            joson.put("goodsPrice",Float.parseFloat(price) );
            joson.put("goodsPicture", base64);
            joson.put("goodsAmount",Integer.parseInt(amout));
           joson.put("goodsInformation", detail);
            String string = joson.toString();
            msg = string;
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