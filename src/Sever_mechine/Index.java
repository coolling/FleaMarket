package Sever_mechine;




import client.base.Base;
import client.component.ImagePanel;
import client.event.CheckProductEvents;
import client.event.GoCenterEvent;
import client.socket.AddCartSocket;
import client.socket.SearchSocket;
import com.alibaba.fastjson.JSONObject;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Index extends JFrame {

    JScrollPane productsDiv = new JScrollPane();
    JPanel productdiv = new JPanel();
    int length=0;
    String products[][] ;
    int height ;
    String id="111";
    JFrame jFrame;
    public Index() throws IOException {
        super();
      //  this.id=id;
        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中

        Socket socket = new Socket("127.0.0.1", Base.goIndex);
        System.out.print("请求连接");
        try {
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
            //System.out.println("输入信息为："+strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            System.out.println(js);
            products=new String[length][4];
            if(js.getIntValue("length")!=0){

                length =js.getIntValue("length");
                height = length % 4 == 0 ? (int) (length) / 4 : (int) (length) / 4 + 1;
                products=new String[length][4];
                for(int i=0;i<js.getIntValue("length");i++){
                    JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
                    String n[] =new String[4];
                    n[3]= ""+ a.getString("goodsId");
                    n[0]=a.getString("goodsName");
                    n[1]="" +a.getFloatValue("goodsPrice");

                    BufferedImage image = null;
                    byte[] imageByte = null;
                    try {
                        imageByte = DatatypeConverter.parseBase64Binary(a.getString("goodsPicture"));
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                        image = ImageIO.read(new ByteArrayInputStream(imageByte));
                        bis.close();
                        File outputfile = new File(System.getProperty("user.dir")+"/src/client/img/product"+a.getString("goodsId")+".jpg");
                        ImageIO.write(image, "jpg", outputfile);
                        //System.out.println(outputfile.getPath());
                        n[2]="/product"+a.getString("goodsId")+".jpg";
                        products[i]=n;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
        jFrame=this;
    }
    private void showP(JSONObject js) throws IOException {


        length =js.getIntValue("length");
        height = length % 4 == 0 ? (int) (length) / 4 : (int) (length) / 4 + 1;
        products=new String[length][4];
        for(int i=0;i<js.getIntValue("length");i++){
            JSONObject a= (JSONObject) js.getJSONArray("data").get(i);
            String n[] =new String[4];
            n[3]= ""+ a.getString("goodsId");
            n[0]=a.getString("goodsName");
            n[1]="" +a.getFloatValue("goodsPrice");

            BufferedImage image = null;
            byte[] imageByte = null;
            try {
                imageByte = DatatypeConverter.parseBase64Binary(a.getString("goodsPicture"));
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(new ByteArrayInputStream(imageByte));
                bis.close();
                File outputfile = new File(System.getProperty("user.dir")+"/src/client/img/product"+a.getString("goodsId")+".jpg");
                ImageIO.write(image, "jpg", outputfile);
                //System.out.println(outputfile.getPath());
                n[2]="/product"+a.getString("goodsId")+".jpg";
                products[i]=n;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        productdiv.removeAll();
        showProduct();
        repaint();
        revalidate();
    }
    public void view() throws IOException {





        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        JPanel head = new JPanel();
        head.setLayout(null);
        head.setBackground(color1);
        head.setBounds(0, 0, 1111, 75);

        JLabel index = new JLabel("Welcome to flea market!");
        index.setForeground(new Color(112, 112, 112));
        index.setFont(new Font("华文宋体", Font.PLAIN, 33));
        JLabel my = new JLabel("关闭服务器");
        //GoCenterEvent goCenterEvent = new GoCenterEvent(my,this,id);

        my.setForeground(new Color(112, 112, 112));
        my.setFont(new Font("华文宋体", Font.PLAIN, 33));

        index.setBounds(50, 1, 400, 70);
        my.setBounds(900, 1, 250, 70);

        head.add(index);
        head.add(my);
        add(head);

        TextField search = new TextField(30);
        search.setBounds(200, 120, 550, 35);
        JButton searchBtu = new JButton("search");


        searchBtu.setForeground(new Color(47, 61, 70));
        searchBtu.setBounds(780, 115, 80, 45);
        searchBtu.setBackground(new Color(113, 193, 252));

        // searchBtu.setOpaque(true);
        //searchBtu.setBorderPainted(false);
        searchBtu.setForeground(new Color(47, 61, 70));

        add(searchBtu);
        add(search);
        revalidate();
        productsDiv.setBorder(null);
        productsDiv.setBounds(0, 190, 1111, 430);
        productdiv.setBackground(Color.white);
        productdiv.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
        productsDiv.setViewportView(productdiv);
        productdiv.setPreferredSize(new Dimension(1111, height * 350));
        // productsDiv.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        //productsDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //productdiv.setLayout(null);
        add(productsDiv);
        if(length!=0){
            showProduct();
        }
        class SearchEvent implements ActionListener {
            Object page;


            public SearchEvent(Object page) {
                this.page = page;


            }
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == page){
                    try {
                        System.out.println(search.getText());
                        String re = SearchSocket.searchServe(search.getText());
                        showP(JSONObject.parseObject(re));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }



            }
        }
        SearchEvent searchEvent =new SearchEvent(searchBtu);
        searchBtu.addActionListener(searchEvent);
    }

    public void showProduct() throws IOException {

        for (int i = 0; i < products.length; i++) {
            ImagePanel imgProduct = new ImagePanel(products[i][2]);

            JLabel price = new JLabel("$" + products[i][1]);
            JLabel name = new JLabel(products[i][0]);

            imgProduct.setPreferredSize(new Dimension(205, 210));
            JPanel productPanel = new JPanel();
            //CheckProductEvents m = new CheckProductEvents(productPanel, this,(products[i][3]),id,products[i][0]);
            int finalI = i;
            productPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        new ProductDetails((products[finalI][3]),id,products[finalI][0]);
                        jFrame.setVisible(false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            productPanel.setPreferredSize(new Dimension(210, 270));
            price.setPreferredSize(new Dimension(210, 30));
            name.setPreferredSize(new Dimension(210, 30));
            productPanel.add(imgProduct);
            productPanel.add(price);
            productPanel.add(name);
            productPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            productPanel.setBackground(Color.white);
            productPanel.setBorder(BorderFactory.createEtchedBorder());

            productdiv.add(productPanel);
            // imgProduct.setSize(2000,3000);
            //productdiv.add( imgProduct);
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        //Index index = new Index();
        //InetAddress ia2=InetAddress.getByName("DESKTOP-93QCALJ");
        //System.out.println(ia2.getHostAddress());
    }
}
