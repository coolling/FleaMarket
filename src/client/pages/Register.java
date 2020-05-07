package client.pages;

import client.component.Head;
import client.component.RediusTextField;
import client.event.RegisterEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.MouseListener;

public class Register extends JFrame {
    String userId;
    String passWord;
    public String getUserId(){
        return userId;
    }
    public String getPassWord(){
        return passWord;
    }
    public Register() {
        super();
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

    public void paint(Graphics g) {
        super.paint(g);
        Color color = new Color(21, 15, 15);
        Font f1 = new Font("宋书", Font.PLAIN, 28);
        g.setFont(f1);
        g.setColor(color);
        g.drawString("Welcome to flea market!", 365, 70);
        //g.drawString("每一天，都在购物。",370,330);
        Font f2 = new Font("宋书", Font.PLAIN, 40);
        g.setFont(f2);
        g.drawString("欢迎注册小跳蚤！", 30, 160);
        Color c2 = new Color(112, 112, 112);
        g.setColor(c2);
        g.setFont(f1);
        g.drawString("每一天，都在购物。", 30, 210);
        g.setColor(Color.white);
        g.drawString("立即注册", 484, 457);
    }

    private void view() {
        Head head = new Head("");
        add(head);
        this.getContentPane().setBackground(Color.white);
        RediusTextField teleField0 = new RediusTextField(20, 0, 0, "");
        add(teleField0);
        teleField0.setBounds(500, 10, 1, 1);
        RediusTextField user = new RediusTextField(20, 0,"  学号");
        RediusTextField password = new RediusTextField(30, 0,"  密码");
        user.setBounds(345, 240, 400, 50);
        password.setBounds(345, 315, 400, 50);
        add(user);
        add(password);
        user.addFocusListener(user);
        password.addFocusListener(password);

        JPanel register = new JPanel();
        register.setBounds(460, 400, 160, 50);
        register.setBackground(new Color(136, 198, 244));
        RegisterEvent registerEvent = new RegisterEvent(register, this);
        register.addMouseListener(registerEvent);
        add(register);
        password.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                       @Override
                                                       public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                           passWord = password.getText().trim();

                                                       }

                                                       public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                           passWord = password.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                       }


                                                       public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                           passWord = password.getText().trim();

                                                       }
                                                   }
        );
        user.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                   @Override
                                                   public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                       userId = user.getText().trim();

                                                   }

                                                   public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                       userId = user.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                   }


                                                   public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                       userId = user.getText().trim();

                                                   }
                                               }
        );
    }

    public static void main(String[] args) {
        Register register = new Register();
    }
}
