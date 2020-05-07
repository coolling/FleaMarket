package Sever_mechine.TRY;

import com.alibaba.fastjson.JSONObject;
import java.lang.*;
import java.net.*;
import java.io.*;

public class A_Sender {
    static final int PORT = 2000; // 连接端口
    static final String HOST = "127.0.0.1"; // 连接地址
    Socket socket;

    public A_Sender() throws UnknownHostException, IOException {
        socket = new Socket(HOST, PORT); // 创建客户端套接字
    }

    // send()方法

    public void send() {
        try {
            DataOutputStream outputStream = null;
            DataInputStream inputStream = null;

            JSONObject json = new JSONObject();
            json.put("userId","201900301133");
            json.put("userPass","clx0725.");
            String json1String = json.toString();
            outputStream = new DataOutputStream(new BufferedOutputStream (socket.getOutputStream()));
            outputStream.writeUTF(json1String);
            outputStream.flush();


            //	if (br.read()==0) System.out.println("success");
/*			while ((msg = in.next()) != null) {
				pw.println(msg); // 发送给服务器端
				System.out.println(br.readLine()); // 输出服务器返回的消息
				if (msg.equals("quit")) {
					break; // 退出
				}
			}*/

            String strInputstream ="";
            inputStream =new DataInputStream(socket.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] by = new byte[2048];
            int n;
            while((n=inputStream.read(by))!=-1){
                baos.write(by,0,n);
            }
            strInputstream = new String(baos.toByteArray());
            JSONObject json1 = new JSONObject(Boolean.parseBoolean(strInputstream));
            socket.shutdownInput();
            baos.close();
            outputStream.close();
            System.out.println(json1.getIntValue("flag"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close(); // 断开连接
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException,
            IOException {
        new A_Sender().send();
    }
}
