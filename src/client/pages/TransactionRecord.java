package client.pages;

import client.base.Base;
import client.component.Head;
import client.component.RecordTable;
import client.event.GoCenterEvent;
import client.event.RegisterEvent;
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

public class TransactionRecord extends JFrame {
    Object name[] = {"买卖", "商品名称", "商品数量","商品编号", "操作", "评价"};
    Object myGoods[][] ;
    String id;

    public TransactionRecord(String id) throws IOException {
        super();
        this.id = id;
        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket(addr, Base.checkRePort);
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
            myGoods=new String[js.getJSONArray("data").size()][6];
            if(js.getJSONArray("data").size()!=0){


                for(int i=0;i<js.getJSONArray("data").size();i++){
                    JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
                    String n[] =new String[6];
                    n[0]=a.getString("iden").equals("buyer")?"买入":"卖出";
                    n[2]=""+a.getIntValue("amount");
                    n[1]="" +a.getString("goods");
                    n[3]=a.getString("goodsId");;
                    n[4]=a.getString("iden").equals("buyer")?(a.getIntValue("state")==0?"已收到":"确认收货"):"";
                    n[5]=a.getString("iden").equals("buyer")?(a.getIntValue("state")==0?"去评价":""):"";

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void view() {
        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        Head head = new Head("");

        add(head);
        JLabel record = new JLabel("交易记录页面");
        record.setForeground(new Color(131, 111, 111));
        record.setFont(new Font("华文宋体", Font.PLAIN, 27));
        record.setBounds(480, 1, 350, 70);
        head.add(record);
        JLabel back = new JLabel("返回个人中心");
        GoCenterEvent goCenterEvent = new GoCenterEvent(back,this,id);
        back.addMouseListener(goCenterEvent);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行
        if (myGoods == null) {
            showNone();
        } else {
            showMany();
        }
    }

    private void showNone() {

    }

    private void showMany() {
        RecordTable recordTable = new RecordTable(myGoods, name,this,id);
        JScrollPane jScrollPane = new JScrollPane(recordTable);
        add(jScrollPane);
        jScrollPane.setBounds(50, 110, 1011, 430);
    }

    public static void main(String[] args) {
        //TransactionRecord transactionRecord = new TransactionRecord();
    }
}
