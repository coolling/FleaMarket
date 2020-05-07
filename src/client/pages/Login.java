package client.pages;

import client.component.ImagePanel;
import client.component.RediusTextField;
import client.component.RoundBorder;
import client.event.LoginEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class Login extends JFrame  {

    String userId = "";
    String passWord = "";
    RediusTextField user = new RediusTextField(40, 28);
    RediusTextField password = new RediusTextField(40, 28);

    public Login() {
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

    public String getUserId() {
        return userId;
    }

    public String getPassWord() {
        return passWord;
    }



    public void paint(Graphics g) {
        super.paint(g);
        Color color = new Color(21, 15, 15);
        Font f1 = new Font("华文宋书", Font.PLAIN, 28);
        g.setFont(f1);
        g.setColor(color);
        g.drawString("登陆", 526, 474);
        g.drawString("账号", 370, 330);
        g.drawString("密码", 370, 390);
    }

    public void view() {

        this.getContentPane().setBackground(Color.white);
        Color welBac = new Color(231, 252, 243);
        JPanel welcome = new JPanel();
        welcome.setBackground(welBac);
        welcome.setBounds(0, 0, 1111, 150);
        ImagePanel xiaoHui = new ImagePanel("/xiaohui.png", true, 120);
        xiaoHui.setBounds(495, 90, 120, 120);
        add(xiaoHui);
        add(welcome);
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

        user.setBounds(440, 280, 250, 40);
        user.setHorizontalAlignment(SwingConstants.CENTER);
        add(user);
        user.setMargin(new Insets(0, 10, 0, 0));

        password.setBounds(440, 340, 250, 40);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        add(password);
        ImagePanel login = new ImagePanel(true, 30);
        login.setBounds(500, 420, 110, 45);
        login.setBackground(new Color(126, 192, 248));
        login.setBorder(new RoundBorder(30, new Color(112, 112, 112)));
        LoginEvent loginEvent = new LoginEvent(login, this);
        login.addMouseListener(loginEvent);
        add(login);
        JLabel register = new JLabel("没有账号？点我注册！");
        register.addMouseListener(loginEvent);
        register.setFont(new Font("华文宋书", Font.PLAIN, 18));
        register.setBounds(475, 480, 300, 50);
        add(register);
    }

    public static void main(String[] args) {
        Login login = new Login();

    }
}
