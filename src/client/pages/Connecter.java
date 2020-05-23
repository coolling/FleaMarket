package client.pages;

import client.base.Base;
import client.component.*;
import client.event.ConnenctEvent;
import client.event.GoCenterEvent;
import client.event.RegisterEvent;
import client.socket.SearchSocket;
import client.socket.TalkingSocket;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connecter extends JFrame {
    JScrollPane connectorDiv = new JScrollPane();
    JPanel connectordiv = new JPanel();
    String[][] connectors=new String[0][4] ;

    String id;
    public Connecter(String id) throws IOException {
        super();
        this.id=id;
        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中

       view();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void view() throws IOException {
        Socket socket = new Socket("127.0.0.1", Base.getchater);
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

            joson.put("user", id);

            String string = joson.toString();
            msg = string;
            pw.println(msg);

            String strInputstream=br.readLine();
            System.out.println("输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
          //  System.out.println(js.getJSONArray("data").get(0));


            if(js.getIntValue("amount")!=0){
                connectors=new String[js.getIntValue("amount")][4];
                for(int i=0;i<js.getIntValue("amount");i++){
                    JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
                    String s[]=new String[4];


                        BufferedImage image = null;
                        byte[] imageByte = null;
                        try {
                            imageByte = DatatypeConverter.parseBase64Binary(a.getString("picture"));
                            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                            image = ImageIO.read(new ByteArrayInputStream(imageByte));
                            bis.close();
                            File outputfile = new File(System.getProperty("user.dir")+"/src/client/img/chater"+a.getString("id")+".jpg");
                            ImageIO.write(image, "jpg", outputfile);
                            //System.out.println(outputfile.getPath());
                            s[0]="/chater"+a.getString("id")+".jpg";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        s[1]=a.getString("webname");
                        s[2]=a.getString("id");
                        s[3]=""+a.getIntValue("message");
                         connectors[i]=s;


                }

            }





        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getContentPane().setBackground(Color.white);

        Head head = new Head("");

        add(head);
        JLabel welcome = new JLabel("Welcome to flea market!");
        welcome.setForeground(new Color(131, 111, 111));
        welcome.setFont(new Font("华文宋体", Font.PLAIN, 27));
        welcome.setBounds(400, 1, 350, 70);
        head.add(welcome);
        JLabel back = new JLabel("返回个人中心");
       GoCenterEvent goCenterEvent = new GoCenterEvent(back, this,id);
       back.addMouseListener(goCenterEvent);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行

        connectorDiv.setBounds(0, 75, 350, 530);
        connectordiv.setBackground(Color.white);
        connectordiv.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
        connectorDiv.setViewportView(connectordiv);
        connectordiv.setPreferredSize(new Dimension(345, 80 * (connectors.length)));

        add(connectorDiv);


        showConnector();
       // showTalking();

        connectorDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        CLabel we = new CLabel("哒哒哒小跳蚤祝您购物愉快");
        add(we);
        we.setBounds(450,240,500,40);
      //  Color color1 = new Color(180, 252, 244);
        //we.setForeground(color1);
        we.setFont(new Font("华文宋体", Font.PLAIN, 38));
    }


    private void showConnector() {
        for (int i = 0; i < connectors.length; i++) {

            JPanel aConnectoer = new JPanel();
            aConnectoer.setLayout(null);
            ImagePanel head = new ImagePanel(connectors[i][0]);


            CLabel name = new CLabel(connectors[i][1]);
            aConnectoer.add(head);
            aConnectoer.add(name);
            if(connectors[i][3].equals("1")){
                name.setForeground(Color.red);
            }
            connectordiv.add(aConnectoer);
            aConnectoer.setPreferredSize(new Dimension(345, 80));
            name.setBounds(105, 27, 200, 30);
            head.setBounds(15, 15, 50, 50);
            aConnectoer.setBackground(new Color(169, 79, 249, 14));
            ConnenctEvent connenctEvent = new ConnenctEvent(aConnectoer,this,id,connectors[i][2],null,null);
            aConnectoer.addMouseListener(connenctEvent);
        }
    }

    public static void main(String[] args) {
        //Connecter Connecter = new Connecter();
    }
}

