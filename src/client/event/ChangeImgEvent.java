package client.event;

import client.base.Base;
import client.component.ImagePanel;
import client.pages.Mistake;
import client.pages.ShoppingCart;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChangeImgEvent extends MouseAdapter {
    JPanel object = null;
JFrame frame;
String id;
int height;
int width;
int type=0;
    public ChangeImgEvent(JPanel object,JFrame jFrame,String id,int height) {
        this.object = object;
        this.frame = jFrame;
        this.id=id;
        this.height=height;
        this.width=height;
    }
    public ChangeImgEvent(JPanel object,JFrame jFrame,String id,int height,int width,int type) {
        this.object = object;
        this.frame = jFrame;
        this.id=id;
        this.height=height;
        this.width=width;
        this.type=type;
    }

    public void mouseClicked(MouseEvent e) {

        JFileChooser chooser = new JFileChooser();             //设置选择器
//        chooser.setMultiSelectionEnabled(true);             //设为多选
        int returnVal = chooser.showOpenDialog((Component) object);        //是否打开文件选择框
        System.out.println("returnVal=" + returnVal);

        if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型

            String path = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径
            System.out.println(path);


            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());  //输出相对路径
            File file = new File(path);
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

            try {
                convertBase64ToImage(new BASE64Encoder().encode(buffer));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }}
    public void convertBase64ToImage(String base64Code) throws IOException {
        //System.out.println(base64Code);
        if(type==0){
            String re="1";
            InetAddress addr = InetAddress.getLocalHost();
            Socket socket = new Socket(addr, Base.changeHeadPort);
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
                joson.put("userPicture", base64Code);
                String string = joson.toString();
                msg = string;
                pw.println(msg);

                String strInputstream=br.readLine();
                System.out.println("输入信息为："+strInputstream);
                JSONObject js = JSONObject.parseObject(strInputstream);
                //System.out.println(js);

                re= js.getIntValue("flag")+"";

                //result=  js.get("flag").toString();





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
            if(!re.equals("0")){
                frame.setVisible(false);
                new Mistake(frame);
            }else{
                BufferedImage image = null;
                byte[] imageByte = null;
                try {
                    imageByte = DatatypeConverter.parseBase64Binary(base64Code);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    image = ImageIO.read(new ByteArrayInputStream(imageByte));
                    bis.close();

                    System.out.println(Base.counts);
                    File outputfile;

                        outputfile = new File(System.getProperty("user.dir")+"/src/client/img/headsource"+Base.counts+".jpg");


                    ImageIO.write(image, "jpg", outputfile);
                    System.out.println(outputfile.getPath());
                    object.removeAll();
                    ImagePanel newHead =new ImagePanel(outputfile.getPath(),false,0,0);
                    object.add(newHead);
                    newHead.setBounds(0,0,width,height);
                    frame.repaint();
                    frame.revalidate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        }else{
            BufferedImage image = null;
            byte[] imageByte = null;
            try {
                imageByte = DatatypeConverter.parseBase64Binary(base64Code);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(new ByteArrayInputStream(imageByte));
                bis.close();

                System.out.println(Base.counts);
                File outputfile;

                    outputfile = new File(System.getProperty("user.dir")+"/src/client/img/prosource.jpg");


                ImageIO.write(image, "jpg", outputfile);
                System.out.println(outputfile.getPath());
                object.removeAll();
                ImagePanel newHead =new ImagePanel(outputfile.getPath(),false,0,0);
                object.add(newHead);
                newHead.setBounds(0,0,width,height);
                frame.repaint();
                frame.revalidate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}