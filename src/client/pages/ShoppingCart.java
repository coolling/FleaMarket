package client.pages;

import client.base.Base;
import client.component.CarTable;
import client.component.Head;
import client.component.ProductTable;
import client.event.GoCenterEvent;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ShoppingCart  extends JFrame {
    String name[] = {"商品名称","商品件数","商品价格","商品编号","总计"," ","   "};
    Object myGoods[][];
    String id;

    public ShoppingCart(String id) throws IOException {
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
      //  InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket("127.0.0.1", Base.checkCarPort);
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
            myGoods=new String[js.getJSONArray("data").size()][7];
            if(js.getJSONArray("data").size()!=0){

                int count =js.getJSONArray("data").size();
                for(int i=0;i<js.getJSONArray("data").size();i++){
                    JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
                    String n[] =new String[7];



                    n[0]= ""+ a.getString("goodsName");
                    n[2]=""+a.getFloatValue("goodsPrice");
                    n[1]="" +a.getIntValue("goodsAmount");
                    n[3]=a.getString("goodsId");
                    n[4]=""+a.getFloatValue("goodsPrice")*a.getIntValue("goodsAmount");
                    n[5]="delete";
                    n[6]="buy";
                    myGoods[i]=n;
                }
                int b=0;
                String goodss[][]=new String[js.getJSONArray("data").size()][7];
                for(int i=0;i<count;i++){
                    boolean flag = false;
                    for(int j=0;j<i;j++){
                        if(myGoods[i][3].equals(myGoods[j][3])){
                            myGoods[j][1]=""+(Integer.parseInt((String) myGoods[j][1])+1);
                            myGoods[j][4]=(Integer.parseInt((String) myGoods[j][1]))*Float.parseFloat((String) myGoods[j][2])+"";
                            flag=true;
                        }
                    }
                    if(!flag){
                        goodss[b]= (String[]) myGoods[i];
                        b++;
                    }

                }
                String agood[][]=new String[b][7];
                for(int i=0;i<b;i++){
                    agood[i]=goodss[i];
                }
                myGoods=agood;
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
        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        Head head = new Head("");

        add(head);
        JLabel car = new JLabel("购物车页面");
        car.setForeground(new Color(131, 111, 111));
        car.setFont(new Font("华文宋体", Font.PLAIN, 27));
        car.setBounds(480, 1, 350, 70);
        head.add(car);
        JLabel back = new JLabel("返回个人中心");
        GoCenterEvent goCenterEvent = new GoCenterEvent(back,this,id);
      back.addMouseListener(goCenterEvent);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行
        if(myGoods==null){
            showNone();
        }else {
            showMany();
        }
    }
    private void showNone(){

    }
    private void showMany(){
        CarTable productTable = new CarTable(myGoods,name,this,id);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 110, 1011, 430);
        add(scrollPane);
    }
    public static void main(String[] args){
        //ShoppingCart shoppingCart = new ShoppingCart();
    }
}

