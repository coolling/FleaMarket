package client.pages;

import client.component.CLabel;
import client.component.Head;
import client.component.ImagePanel;
import client.component.RediusTextField;
import client.event.ChangeImgEvent;
import client.event.GoCenterEvent;
import client.event.ReleaseEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;

public class ReleaseProduct extends JFrame {

    String name;
    String amount;
    String detail;
    String price;
    String id;
    public static int acount=0;
    String productImage=ReleaseProduct.acount==0?null:"/product.jpg";
    public ReleaseProduct(String id) {
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
    private void view() {
        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        Head head = new Head("");

        add(head);
        JLabel record = new JLabel("商品发布页面");
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

            JPanel div = new JPanel();
            add(div);
            div.setBounds(130,130,245,320);
            div.setLayout(new BorderLayout());
        ImagePanel waitAdd=null;
        if(productImage!=null){
           waitAdd = new ImagePanel(productImage);
        }else{
            waitAdd = new ImagePanel("/waitAdd.jpg");
        }
        ChangeImgEvent changeImgEvent =new ChangeImgEvent(div,this,id,320,245,1);
        div.addMouseListener(changeImgEvent);
        div.add(waitAdd);


        CLabel proName = new CLabel("商品名称：");
        CLabel proPrice = new CLabel("商品价格：");
        CLabel proDetail = new CLabel("商品详情：");
        CLabel proAmount = new CLabel("商品库存：");
        add(proName);
        add(proAmount);
        add(proDetail);
        add(proPrice);
        proName.setBounds(500,120,250,50);
        proPrice.setBounds(500,190,250,50);
        proDetail.setBounds(500,330,250,50);
        proAmount.setBounds(500,260,250,50);
        RediusTextField nameField = new RediusTextField(15,5);
        RediusTextField priceField = new RediusTextField(15,5);
        RediusTextField amoutField = new RediusTextField(15,5);
        add(nameField);
        add(priceField);
        add(amoutField);
        nameField.setBounds(630,125,250,40);
        priceField.setBounds(630,195,250,40);
        amoutField.setBounds(630,265,250,40);
        JTextArea detailArea=new JTextArea(6,30);
        detailArea.setLineWrap(true);    //设置文本域中的文本为自动换行
        Color c1 = new Color(141,131,131);
        detailArea.setForeground(c1);    //设置字体颜色
        detailArea.setFont(new Font("楷体",Font.BOLD,20));    //修改字体样式
        detailArea.setBackground(Color.white);    //设置背景色
      nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                      @Override
                                                      public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                          name = nameField.getText().trim();

                                                      }

                                                      public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                          name = nameField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                      }


                                                      public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                          name = nameField.getText().trim();

                                                      }
                                                  }
      );
        priceField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                        @Override
                                                        public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                            price = priceField.getText().trim();

                                                        }

                                                        public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                            price = priceField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                        }


                                                        public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                            price = priceField.getText().trim();

                                                        }
                                                    }
        );
        amoutField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                        @Override
                                                        public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                            amount = amoutField.getText().trim();

                                                        }

                                                        public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                            amount = amoutField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                        }


                                                        public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                            amount = amoutField.getText().trim();

                                                        }
                                                    }
        );
        detailArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                        @Override
                                                        public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                            detail =  detailArea.getText().trim();

                                                        }

                                                        public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                            detail =  detailArea.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                        }


                                                        public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                            detail =  detailArea.getText().trim();

                                                        }
                                                    }
        );
        JScrollPane detailScoll=new JScrollPane(detailArea);    //将文本域放入滚动窗口
        add(detailScoll);
        detailScoll.setBounds(630,335,250,170);
        nameField.setForeground(c1);
        priceField.setForeground(c1);
        amoutField.setForeground(c1);priceField.setForeground(c1);
        ImagePanel release = new ImagePanel(true,0);
        add(release);
        release.setBounds(930,530,100,50);
        release.setBackground(new Color(19, 175, 244, 44));
        CLabel go = new CLabel("发布！");
        ReleaseEvent releaseEvent =new ReleaseEvent(go,this,id);
        go.addMouseListener(releaseEvent);
        go.setBounds(947,530,100,50);
        add(go);
    }
    public  String getAmount(){
        return amount;
    }
    public String getDetail(){
        return detail;
    }
    public String getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public static void main(String[] args){
        //ReleaseProduct releaseProduct = new ReleaseProduct();
    }
}

