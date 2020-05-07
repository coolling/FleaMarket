package client.pages;

import client.base.Base;
import client.component.Head;
import client.component.ImagePanel;
import client.component.ProductTable;
import client.event.GoCenterEvent;
import client.json.Product;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Mygoods extends JFrame {
    Object name[] = {"商品名称", "价格", "商品库存", "商品编号", "操作"};
    Object myGoods[][] ;
    private Product[] products = {new Product("", "", 1, 1)};
    String id;
    public Mygoods(String id) throws IOException {
        super();
        this.id=id;
        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket(addr, Base.checkMyPort);
        System.out.print("请求连接");
        repaint();
        revalidate();
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
            myGoods=new String[js.getJSONArray("data").size()][5];
            if(js.getJSONArray("data").size()!=0){


                for(int i=0;i<js.getJSONArray("data").size();i++){
                    JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
                    String n[] =new String[5];
                    n[0]= ""+ a.getString("goodsName");
                    n[1]=""+a.getFloatValue("goodsPrice");
                    n[2]="" +a.getIntValue("goodsAmount");
                    n[3]=""+a.getIntValue("goodsId");
                    n[4]="下架";
                    myGoods[i]=n;
                }


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
        if (products.length == 0) {
            noneView();
        } else {
            mangView();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


    }

    private void view() {
        Head head = new Head("我的商品界面");
        add(head);
        this.getContentPane().setBackground(Color.white);
        JLabel back = new JLabel("返回个人中心");
       GoCenterEvent goCenterEvent = new GoCenterEvent(back,this,id);
        back.addMouseListener(goCenterEvent);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行

    }

    private void noneView() {
        ImagePanel noneImg = new ImagePanel("/WechatIMG12.jpeg");
        noneImg.setBounds(480, 120, 150, 150);
        this.add(noneImg);
        JLabel none = new JLabel("您暂时没有上架商品呢");
        none.setForeground(new Color(131, 111, 111));
        none.setFont(new Font("华文宋体", Font.PLAIN, 27));
        none.setBounds(420, 300, 400, 70);
        add(none);
        JLabel goUp = new JLabel("去上架");
        goUp.setForeground(new Color(108, 158, 227));
        goUp.setFont(new Font("华文宋体", Font.PLAIN, 27));
        goUp.setBounds(515, 360, 200, 70);
        add(goUp);
    }

    private void mangView() {
        ProductTable productTable = new ProductTable(myGoods, name,this,id);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 110, 1011, 430);
        add(scrollPane);
    }

    public static void main(String[] args) {
        //Mygoods mygoods = new Mygoods("111");
    }
}
