package client.pages;

import client.base.Base;
import client.component.CLabel;
import client.component.ImagePanel;
import client.component.RoundBorder;
import client.event.*;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class PersonalCenter extends JFrame {
    String imgUrl ;
    String nickName="哒哒哒哒玲";
    String sex = "女";
    String grade="大一";
    String name = "帅大玲" ;
    String major = "软件工程";
    String area = "软件园";
    String telenumber="1111000111";
    String id;
    public PersonalCenter(String id) throws IOException {
        super();
        this.id = id;
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
    public String getUrl(){
        return imgUrl;
    }
    private void view() throws IOException {
        Socket socket = new Socket("127.0.0.1", Base.goCenter);
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
            String string = joson.toString();
            msg = string;
            pw.println(msg);

            String strInputstream=br.readLine();
            System.out.println("输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            //System.out.println(js);
            sex= js.getString("userSex");
            area= js.getString("userAddress");
            major= js.getString("userMajor");
            nickName= js.getString("userWebname");
            name= js.getString("trueName");
            grade= js.getString("userGrade");
            telenumber= js.getString("userTel");
            //result=  js.get("flag").toString();
            if(js.getString("userPicture").length()>10){
                BufferedImage image = null;
                byte[] imageByte = null;

                imageByte = DatatypeConverter.parseBase64Binary(js.getString("userPicture"));
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(new ByteArrayInputStream(imageByte));
                bis.close();
                File outputfile = new File(System.getProperty("user.dir")+"/src/client/img/headsource"+Base.counts+".jpg");
                ImageIO.write(image, "jpg", outputfile);
                //System.out.println(outputfile.getPath());
                imgUrl="/headsource"+Base.counts+".jpg";

            }else {
                imgUrl = "/source.jpg";
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
        repaint();
        revalidate();
        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        JPanel head = new JPanel();
        head.setLayout(null);
        head.setBackground(color1);
        head.setBounds(0, 0, 1111, 75);
        add(head);
        JLabel back = new JLabel("返回首页");
        GoIndexEvent goIndexEvent = new GoIndexEvent(back,this,id);
        back.addMouseListener(goIndexEvent);
        back.setForeground(new Color(112, 112, 112));//设置JLabel颜色
        back.setFont(new Font("华文宋体", Font.PLAIN, 30));//设置JLabel样式
        JLabel center = new JLabel("个人中心");

        center.setForeground(new Color(141,131,131));
        center.setFont(new Font("华文宋体", Font.PLAIN, 27));
        JLabel modify = new JLabel("编辑个人资料");
        CLabel message = new CLabel("消息");
        head.add(message);
        message.setBounds(780,10,100,50);
        CheckMessage checkMessage = new CheckMessage(message,this,id);
        message.addMouseListener(checkMessage);

        GoEditEvent goEditEvent = new GoEditEvent(modify,this,id);
        modify.addMouseListener(goEditEvent);
        modify.setForeground(new Color(112, 112, 112));
        modify.setFont(new Font("华文宋体", Font.PLAIN, 30));
        back.setBounds(30, 1, 200, 70);
        center.setBounds(500, 1, 200, 70);
        modify.setBounds(900, 1, 200, 70);
        head.add(back);
        head.add(center);
        head.add(modify);
        JPanel bottom = new JPanel();
        bottom.setBounds(0,495,1111,110);
        bottom.setBackground(new Color(247,248,248));
        add(bottom);
        bottom.setLayout(null);
        ImagePanel car = new ImagePanel("/car.png",true,65);
        bottom.add(car);
        car.setBounds(90,13,65,65);
        ImagePanel record = new ImagePanel("/record.png");
        bottom.add(record);


        ImagePanel add = new ImagePanel("/add.png");
        bottom.add(add);
        record.setBounds(650,15,65,65);
        add.setBounds(370,17,57,57);
        ImagePanel my = new ImagePanel("/my.png");
        bottom.add(my);
        my.setBounds(930,15,65,65);
        JLabel carLable = new JLabel("购物车");

        JLabel addLable = new JLabel("发布商品");
        JLabel myLable = new JLabel("我的商品");
        JLabel recordLable = new JLabel("交易记录");

        Color c1 = new Color(141,131,131);
        carLable.setForeground(c1);
        addLable.setForeground(c1);
        myLable.setForeground(c1);
        recordLable.setForeground(c1);

        bottom.add(carLable);
        bottom.add(addLable);
        bottom.add(myLable);
        bottom.add(recordLable);
        carLable.setBounds(102,83,65,20);
        addLable.setBounds(373,83,65,20);
        recordLable.setBounds(656,83,65,20);
        myLable.setBounds(942,83,65,20);
        CheckRecordEvent checkRecordEvent2 = new CheckRecordEvent(record,this,id);
        record.addMouseListener(checkRecordEvent2);
        CheckRecordEvent checkRecordEvent = new CheckRecordEvent(recordLable,this,id);
        recordLable.addMouseListener(checkRecordEvent);
        CheckCartEvevt checkCartEvevt = new CheckCartEvevt(carLable,this,id);
        carLable.addMouseListener(checkCartEvevt);
        CheckCartEvevt checkCartEvevt2 = new CheckCartEvevt(car,this,id);
        car.addMouseListener(checkCartEvevt2);


        CheckMyGoodsEvent checkMyGoodsEvent = new CheckMyGoodsEvent(my,this,id);
        CheckMyGoodsEvent checkMyGoodsEvent2 = new CheckMyGoodsEvent(myLable,this,id);
        my.addMouseListener(checkMyGoodsEvent);
        myLable.addMouseListener(checkMyGoodsEvent2);
        GoReleaseEvent goReleaseEvent = new GoReleaseEvent(add,this,id);
        add.addMouseListener(goReleaseEvent);
        GoReleaseEvent goReleaseEvent2 = new GoReleaseEvent(addLable,this,id);
        addLable.addMouseListener(goReleaseEvent2);
        ImagePanel userHead = new ImagePanel(imgUrl);

        JPanel headDiv = new JPanel();

        headDiv.setLayout(null);
        add(headDiv);
        headDiv.add(userHead);
        ChangeImgEvent changeImgEvent = new ChangeImgEvent(headDiv,this,id,80);
        headDiv.addMouseListener(changeImgEvent);
        headDiv.setBounds(100,120,80,80);
        userHead.setBounds(0,0,80,80);

        JLabel nicknameLable = new JLabel(nickName);
        JLabel gradeLable = new JLabel("年级： "+grade);
        JLabel areaLable = new JLabel("校区： "+area);
        JLabel majorLable = new JLabel("专业： "+major);
        JLabel sexLable = new JLabel("性别： "+sex);
        JLabel nameLable = new JLabel("姓名： "+name);
        add(nicknameLable);
        add(areaLable);
        add(majorLable);
        add(sexLable);
        add(nameLable);
        add(gradeLable);
        nicknameLable.setBounds(220,150,200,50);
        Font f1= new Font("华文宋体", Font.PLAIN, 35);
        nicknameLable.setFont(f1);
        nicknameLable.setForeground(c1);
        sexLable.setFont(f1);
        nameLable.setFont(f1);
        gradeLable.setFont(f1);
        areaLable.setFont(f1);
        majorLable.setFont(f1);
        sexLable.setForeground(c1);
        nameLable.setForeground(c1);
        gradeLable.setForeground(c1);
        majorLable.setForeground(c1);
        areaLable.setForeground(c1);
        sexLable.setBounds(660,110,500,70);
        gradeLable.setBounds(660,180,500,70);
        majorLable.setBounds(660,250,500,70);
        areaLable.setBounds(660,320,500,70);
        nameLable.setBounds(660,390,500,70);
        JLabel out = new JLabel("  退出登陆");
        GoLoginEvent goLoginEvent = new GoLoginEvent(out,this);
        out.addMouseListener(goLoginEvent);
        add(out);
        out.setBounds(966,433,105,45);
        out.setForeground( new Color(99, 91, 91, 231));
        out.setFont(new Font("华文宋体", Font.PLAIN, 20));
        out.setBorder(new RoundBorder(5,Color.black));
        JLabel tele = new JLabel("call："+telenumber);
        add(tele);
        tele.setBounds(220,195,300,45);
        tele.setForeground( new Color(119, 110, 110, 156));
        tele.setFont(new Font("华文宋体", Font.PLAIN, 20));

    }

    public static void main(String[] args) throws IOException {
        PersonalCenter personalCenter = new PersonalCenter("111");
    }
}
