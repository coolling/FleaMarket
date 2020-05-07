package client.pages;

import client.base.Base;
import client.component.CLabel;
import client.component.Head;
import client.component.ImagePanel;
import client.component.RediusTextField;
import client.event.*;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ModifyingData extends JFrame {

    String headUrl ;
    String nickname;
    String sex;
    String grade;
    String name ;
    String major ;
    String area;
    String telephone;
    String id;
    public ModifyingData( String id) throws IOException {
        super();
        this.id=id;

        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中
        InetAddress addr = InetAddress.getLocalHost();
        Socket socket = new Socket(addr, Base.goCenter);
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
            sex= js.getString("userSex");
            area= js.getString("userAddress");
            major= js.getString("userMajor");
            nickname= js.getString("userWebname");
            name= js.getString("trueName");
            grade= js.getString("userGrade");
            telephone= js.getString("userTel");
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
                headUrl="/headsource"+Base.counts+".jpg";

            }else {
                headUrl = "/source.jpg";
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
        JLabel edit = new JLabel("修改个人资料");
        edit.setForeground(new Color(131, 111, 111));
        edit.setFont(new Font("华文宋体", Font.PLAIN, 27));
        edit.setBounds(480, 1, 350, 70);
        head.add(edit);
        JLabel back = new JLabel("返回个人中心");
        GoCenterEvent goCenterEvent = new GoCenterEvent(back, this,id);
        back.addMouseListener(goCenterEvent);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行
        showData();
    }

    private void showData() {
        CLabel teleLable = new CLabel("联系方式:" );
        CLabel nameLabel = new CLabel("真实姓名：");
        CLabel nickLable = new CLabel("昵称:" );
        CLabel sexLable = new CLabel("性别:" );
        CLabel areaLable = new CLabel("校区:" );
        CLabel gradeLable = new CLabel("年级：" );
        CLabel majorLable = new CLabel("专业：");
        add(teleLable);
        add(nameLabel);
        add(nickLable);
        add(sexLable);
        add(areaLable);
        add(gradeLable);
        add(majorLable);
        teleLable.setBounds(450, 120, 200, 50);
        sexLable.setBounds(230, 180, 150, 50);
        nickLable.setBounds(700, 180, 150, 50);
        areaLable.setBounds(160, 300, 150, 50);
        nameLabel.setBounds(800, 300, 250, 50);
        gradeLable.setBounds(320, 470, 150, 50);
        majorLable.setBounds(600, 480, 150, 50);
        RediusTextField teleField0 = new RediusTextField(20,0,0,"");
        //teleField0.setEnabled(false);
        RediusTextField teleField = new RediusTextField(20,0,0,telephone);
        RediusTextField nameField = new RediusTextField(10,0,0,name);
        RediusTextField nickField = new RediusTextField(10,0,0,nickname);
        RediusTextField sexField = new RediusTextField(3,0,0,sex);
        RediusTextField gradeField = new RediusTextField(10,0,0,grade);
        RediusTextField majorField = new RediusTextField(10,0,0,major);
        RediusTextField areaField = new RediusTextField(10,0,0,area);
        add(teleField0);
        add(teleField);
        add(nameField);
        add(nickField);
        add(sexField);
        add(gradeField);
        add(majorField);
        add(areaField);
        teleField0.setBounds(500, 10, 1, 1);
        teleField.setBounds(580, 120, 200, 50);
        sexField.setBounds(310, 180, 100, 50);
        nickField.setBounds(780, 180, 200, 50);
        areaField.setBounds(240, 300, 200, 50);
        nameField.setBounds(930, 300, 200, 50);
        gradeField.setBounds(400, 470, 200, 50);
        majorField.setBounds(680, 480, 200, 50);
        CLabel head = new CLabel("头像:");
        add(head);
        head.setBounds(530,210,150,50);
        ImagePanel headPanel = new ImagePanel(headUrl);
        JPanel headDiv = new JPanel();
        headDiv.add(headPanel);
        headDiv.setLayout(null);
        add(headDiv);
        ChangeImgEvent changeImgEvent = new ChangeImgEvent(headDiv,this,id,120);
        headDiv.addMouseListener(changeImgEvent);
        headDiv.setBounds(500,265,120,120);
        headPanel.setBounds(0,0,120,120);
        JButton save = new JButton("save");

        add(save);
        save.setBounds(920,520,80,50);
        save.setForeground(new Color(131, 111, 111));
        save.setFont(new Font("华文宋体", Font.PLAIN, 22));

        teleField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                        @Override
                                                        public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                            telephone = teleField.getText().trim();

                                                        }

                                                        public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                            telephone = teleField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                        }


                                                        public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                            telephone = teleField.getText().trim();

                                                        }
                                                    }
        );
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
        nickField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                        @Override
                                                        public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                            nickname = nickField.getText().trim();

                                                        }

                                                        public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                            nickname = nickField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                        }


                                                        public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                            nickname = nickField.getText().trim();

                                                        }
                                                    }
        );
        sexField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                       @Override
                                                       public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                           sex = sexField.getText().trim();

                                                       }

                                                       public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                           sex = sexField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                       }


                                                       public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                           sex = sexField.getText().trim();

                                                       }
                                                   }
        );
        gradeField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                         @Override
                                                         public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                             grade = gradeField.getText().trim();

                                                         }

                                                         public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                             grade = gradeField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                         }


                                                         public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                             grade = gradeField.getText().trim();

                                                         }
                                                     }
        );
        majorField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                         @Override
                                                         public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                             major = majorField.getText().trim();

                                                         }

                                                         public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                             major = majorField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                         }


                                                         public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                             major = majorField.getText().trim();

                                                         }
                                                     }
        );
        areaField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                        @Override
                                                        public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                            area = areaField.getText().trim();

                                                        }

                                                        public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                            area = areaField.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                        }


                                                        public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                            area = areaField.getText().trim();

                                                        }
                                                    }
        );
        EditInforEvent editInforEvent = new EditInforEvent(this,id);
        save.addActionListener(editInforEvent);
    }
     public String getNickname(){
        return nickname;
}
    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public String getGrade(){
        return grade;
    }
    public String getTelephone(){
        return telephone;
    }
    public String getMajor(){
        return major;
    }
    public String getArea(){
        return area;
    }
    public static void main(String[] args) {
       // ModifyingData modifyingData = new ModifyingData();
    }
}
