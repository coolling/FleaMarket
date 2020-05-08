package client.pages;

import client.base.Base;
import client.component.ImagePanel;
import client.component.RoundBorder;
import client.event.*;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ProductDetails extends JFrame {
    String productName="sky";
    double productPrice=9999;
    String productDetails="炒鸡美腻！！！！！！炒鸡美腻！！！！！！炒鸡美腻！！！！！！炒鸡美腻！！！！！！";
    String productUrl="/WechatIMG10.jpeg";
    String[][] comments={{"丛丛丛","不错不错"},{"丛丛丛","不错不错"},{"丛丛丛","不错不错"},{"丛丛丛","不错不错"}};
    String id;
    int productAmount;
    String proid;
    String name;
    public ProductDetails(String proid,String name,String id) throws IOException {

        super();
        this.id =id;
        this.name=name;
        this.proid=proid;
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
        //InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket("127.0.0.1", Base.checkPro);
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

            productPrice=js.getFloatValue("goodsPrice");
            productName=name;
            productDetails=js.getString("goodsInfo");
            productUrl="/product"+proid+".jpg";
            productAmount=js.getIntValue("goodsAmount");



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
        this.getContentPane().setBackground(Color.white);//用this.setBackground(Color.white);不行
        Color color1 = new Color(231, 252, 243);
        JPanel head = new JPanel();
        head.setLayout(null);
        head.setBackground(color1);
        head.setBounds(0, 0, 1111, 75);
        add(head);
        JLabel detail = new JLabel("商品详情界面");
        detail.setForeground(new Color(131, 111, 111));
        detail.setFont(new Font("华文宋体", Font.PLAIN, 27));
        detail.setBounds(470, 1, 300, 70);

        JLabel back = new JLabel("返回首页");
        GoIndexEvent goIndexEvent = new GoIndexEvent(back,this,id);
        back.addMouseListener(goIndexEvent);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        back.setBounds(30, 1, 300, 70);
        head.add(back);
        head.add(detail);
        ImagePanel productImg = new ImagePanel(productUrl);
        productImg.setBounds(120,150,230,285);
        add(productImg);
        JLabel aproductName = new JLabel("名称    "+productName);
        JLabel aproductPrice = new JLabel("价格    ¥"+productPrice);
        JLabel aproductDetail = new JLabel("介绍    ");
        Color c1 = new Color(141,131,131);
        aproductName.setForeground(c1);
        aproductPrice.setForeground(c1);
        aproductDetail.setForeground(c1);
        Font f1= new Font("华文宋体", Font.PLAIN, 32);
        aproductName.setFont(f1);
        aproductPrice.setFont(f1);
        aproductDetail.setFont(f1);
        aproductName.setBounds(520,150,400,50);
        aproductPrice.setBounds(520,220,400,50);
        aproductDetail.setBounds(520,290,400,50);
        add(aproductName);
        add(aproductDetail);
        add(aproductPrice);
        JTextArea detailArea=new JTextArea(productDetails,4,30);
        detailArea.setLineWrap(true);    //设置文本域中的文本为自动换行
        detailArea.setForeground(c1);    //设置字体颜色
        detailArea.setFont(new Font("楷体",Font.BOLD,20));    //修改字体样式
        detailArea.setBackground(new Color(247,248,248));    //设置背景色
        detailArea.setEnabled(false);
        JScrollPane detailScoll=new JScrollPane(detailArea);    //将文本域放入滚动窗口
        add(detailScoll);
        detailScoll.setBounds(620,300,400,80);
        showComment();

        JButton add = new JButton("加入购物车");
        JButton check = new JButton("查看评论");
        JFrame a=this;
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    a.dispose();
                    new  CommentsShow(id,proid,productName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton buy = new JButton("立即下单!");
        BuyEvent buyEvent = new BuyEvent(buy,this,id,productAmount,proid,productName);
        buy.addActionListener(buyEvent);
        AddToCartEvent addToCartEvent = new AddToCartEvent(add,this,id,productName, (float) productPrice,productAmount,proid);
        add.addActionListener(addToCartEvent);
        JButton connect = new JButton("联系卖家");
        ConnenctEvent connenctEvent = new ConnenctEvent(connect,this,id);
        connect.addActionListener(connenctEvent);
        add(check);
        add(add);
        add(connect);
        add(buy);
        buy.setBounds(900,505,110,50);
        buy.setForeground( new Color(141,131,131));
        check.setBounds(535,405,130,50);
       check.setForeground( new Color(141,131,131));
        add.setBounds(710,405,130,50);
        connect.setBounds(885,405,130,50);
        add.setForeground( new Color(141,131,131));
        connect.setForeground( new Color(141,131,131));
    }
    private void showComment(){
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
        commentsDiv.setBounds(58, 480, 1000, 140);
        commentdiv.setBackground(Color.white);
        commentdiv.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        commentsDiv.setViewportView(commentdiv);
        commentdiv.setPreferredSize(new Dimension(950, comments.length * 110));
        //commentsDiv.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
        commentsDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //productdiv.setLayout(null);
        //add(commentsDiv);

    }
    public static void main(String[] args) throws IOException {
      //  ProductDetails productDetails = new ProductDetails("2");
    }
}
