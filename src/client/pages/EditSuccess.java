package client.pages;

import client.component.Head;
import client.event.GoCenterEvent;
import client.event.RegisterEvent;

import javax.swing.*;
import java.awt.*;

public class EditSuccess extends JFrame {
    String id;
    public EditSuccess(String id) {
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
        JLabel welcome = new JLabel("   Welcome to flea market!");
        welcome.setForeground(new Color(131, 111, 111));
        welcome.setFont(new Font("华文宋体", Font.PLAIN, 27));
        welcome.setBounds(400, 1, 450, 70);
        head.add(welcome);
        JLabel registerSuccess = new JLabel("修改成功！");
        registerSuccess.setFont(new Font("华文宋体", Font.PLAIN, 44));
        registerSuccess.setForeground(new Color(83, 75, 75));
        add(registerSuccess);
        registerSuccess.setBounds(490, 200, 280, 60);
        JLabel press = new JLabel("点击");
        JLabel back = new JLabel("返回");
        GoCenterEvent goCenterEvent =new GoCenterEvent(back,this,id);
        back.addMouseListener(goCenterEvent);
        press.setForeground(new Color(131, 111, 111));
        press.setFont(new Font("华文宋体", Font.PLAIN, 27));
        back.setForeground(new Color(108, 158, 227));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        press.setBounds(480, 300, 150, 100);
        back.setBounds(535, 300, 150, 100);
        JLabel goIndex = new JLabel("个人中心");
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
