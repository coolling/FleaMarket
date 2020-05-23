package Sever_mechine;

import client.component.Head;
import client.event.CheckMyGoodsEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class DownSuccess extends JFrame {
    String id;
    String title;
    JFrame jFrame;
    public DownSuccess (String id,String title) {
        super();
        this.title=title;
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
        jFrame=this;
    }

    private void view() {
        this.getContentPane().setBackground(Color.white);
        Color color1 = new Color(231, 252, 243);
        Head head = new Head("");

        add(head);
        JLabel welcome = new JLabel("   Welcome to flea market!");
        welcome.setForeground(new Color(131, 111, 111));
        welcome.setFont(new Font("华文宋体", Font.PLAIN, 27));
        welcome.setBounds(400, 1, 450, 70);
        head.add(welcome);
        JLabel registerSuccess = new JLabel(title);
        registerSuccess.setFont(new Font("华文宋体", Font.PLAIN, 44));
        registerSuccess.setForeground(new Color(83, 75, 75));
        add(registerSuccess);
        registerSuccess.setBounds(490, 200, 280, 60);
        JLabel press = new JLabel("点击");
        JLabel back = new JLabel("返回");


       // CheckMyGoodsEvent checkMyGoodsEvent = new CheckMyGoodsEvent(back,this,id);
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //getContentPane().setVisible(false);
                try {
                    new Index();
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
        press.setForeground(new Color(131, 111, 111));
        press.setFont(new Font("华文宋体", Font.PLAIN, 27));
        back.setForeground(new Color(108, 158, 227));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        press.setBounds(480, 300, 150, 100);
        back.setBounds(535, 300, 150, 100);
        JLabel goIndex = new JLabel("主页");
        goIndex.setFont(new Font("华文宋体", Font.PLAIN, 27));
        goIndex.setForeground(new Color(131, 111, 111));
        goIndex.setBounds(590, 300, 150, 100);
        add(goIndex);
        add(press);
        add(back);
    }
    public static void main(String[] args){
        //EditSuccess registerSuccess = new EditSuccess();
    }
}


