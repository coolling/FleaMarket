package client.pages;

import client.base.Base;
import client.component.Head;
import client.component.RoundBorder;
import client.event.CheckCartEvevt;
import client.event.CheckProductEvents;
import client.event.CheckRecordEvent;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class CommentsShow extends JFrame {

    String id;
    String proid;
    String name;
    String[][] comments;
    int length=0;
    public CommentsShow(String id, String proid, String name) throws IOException {
        super();
        this.id = id;
        this.proid = proid;
        this.name = name;
        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中

        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket(addr, Base.showCom);
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
            joson.put("goodsId", Integer.parseInt(proid));
            //int

            String string = joson.toString();
            msg = string;
            pw.println(msg);

            String strInputstream=br.readLine();
            System.out.println("输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            System.out.println(js);
            comments=new String[length][3];
            if(js.getIntValue("length")!=0){

                length =js.getIntValue("length");

                comments=new String[length][3];
                for(int i=0;i<js.getIntValue("length");i++){
                    JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
                    String n[] =new String[3];
                    n[1]= ""+ a.getString("comments");
                    n[2]=a.getString("makerId");
                    n[0]="" +a.getString("makerWebname");
                   comments[i]=n;

                }


            }else{
                JLabel back = new JLabel("暂无评论");

                back.setForeground(new Color(108, 158, 227));
                back.setFont(new Font("华文宋体", Font.PLAIN, 37));

                back.setBounds(500, 300, 150, 50);


                add(back);
            }




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
        view();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void view() {
        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        Head head = new Head("");

        add(head);
        JLabel back = new JLabel("返回");
        CheckProductEvents checkRecordEvent = new CheckProductEvents(back,this,proid,id,name);
        back.addMouseListener(checkRecordEvent);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        back.setBounds(30, 1, 300, 70);
        head.add(back);
        Color c1 = new Color(141,131,131);
        Color c2 = new Color(157,141,175);
        Font f1= new Font("华文宋体", Font.PLAIN, 20);
        JScrollPane commentsDiv = new JScrollPane();
        JPanel commentdiv = new JPanel();
        for (int i = 0; i < comments.length; i++) {

            JLabel user = new JLabel("用户： "+comments[i][0]);
            JLabel commentText = new JLabel(comments[i][1]);

            user.setForeground(c2);
            commentText.setForeground(c1);
            user.setFont(f1);
            commentText.setFont(f1);
            JPanel commentPanel = new JPanel();
            commentPanel.setPreferredSize(new Dimension(950, 100));
            user.setPreferredSize(new Dimension(210, 30));
            commentText.setPreferredSize(new Dimension(850, 30));
            user.setBounds(20,10,900,40);
            commentText.setBounds(20,50,900,40);

            commentPanel.add(user);
            commentPanel.add(commentText);
            commentPanel.setLayout(null);

            commentPanel.setBackground(Color.white);
            //commentPanel.setBackground(new Color(250,236,240));
            commentPanel.setBorder(new RoundBorder(0,Color.black));
            commentdiv.add( commentPanel);

        }
        commentsDiv.setBorder(null);
        commentsDiv.setBounds(58, 120, 1000, 460);
        commentdiv.setBackground(Color.white);
        commentdiv.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        commentsDiv.setViewportView(commentdiv);
        commentdiv.setPreferredSize(new Dimension(950, comments.length * 110));
        //commentsDiv.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
        commentsDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //productdiv.setLayout(null);
        add(commentsDiv);
    }

    public static void main(String[] args) {
        //AddSuccess addSuccess = new AddSuccess();
    }
}

