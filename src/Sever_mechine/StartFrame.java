package Sever_mechine;

import client.component.ImagePanel;
import client.component.RoundBorder;
import client.event.LoginEvent;
import client.pages.Login;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JFrame{
    JFrame jFrame;
    public StartFrame() {
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
        jFrame=this;
    }





    public void paint(Graphics g) {
        super.paint(g);
        Color color = new Color(21, 15, 15);
        Font f1 = new Font("宋书", Font.PLAIN, 28);
        g.setFont(f1);
        g.setColor(color);

        //g.drawString("每一天，都在购物。",370,330);
        Font f2 = new Font("宋书", Font.PLAIN, 40);
        g.setFont(f2);
       // g.drawString("Welcome to flea market!", 30, 260);
        Color c2 = new Color(112, 112, 112);
        g.setColor(c2);
        g.setFont(f1);
        g.drawString("点击进入管理员平台", 430, 330);
        g.setColor(Color.white);

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
        JButton start=new JButton("start");
        add(start);
        start.setBounds(500, 420, 110, 45);
        start.setBackground(new Color(126, 192, 248));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new start();

                try {
                    new Index();
                    jFrame.setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        StartFrame login = new StartFrame();
        // Login login2 = new Login();
    }
}
