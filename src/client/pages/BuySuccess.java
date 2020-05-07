package client.pages;

import client.component.Head;
import client.event.CheckCartEvevt;
import client.event.CheckProductEvents;

import javax.swing.*;
import java.awt.*;

public class BuySuccess extends JFrame {
    String id;
    String proid;
    String name;
    String title="购买成功！";
    public BuySuccess(String id, String proid, String name) {
        super();
        this.id = id;
        this.proid = proid;
        this.name = name;
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
        JLabel welcome = new JLabel("购买商品！");
        welcome.setForeground(new Color(131, 111, 111));
        welcome.setFont(new Font("华文宋体", Font.PLAIN, 27));
        welcome.setBounds(510, 1, 350, 70);
        head.add(welcome);
        JLabel addSuccess = new JLabel(title);
        addSuccess.setFont(new Font("华文宋体", Font.PLAIN, 44));
        addSuccess.setForeground(new Color(83, 75, 75));
        add(addSuccess);
        addSuccess.setBounds(490, 200, 280, 60);

        JLabel back = new JLabel("返回");
        CheckProductEvents checkProductEvents = new CheckProductEvents(back, this, proid, id, name);
        back.addMouseListener(checkProductEvents);

        back.setForeground(new Color(108, 158, 227));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));

        back.setBounds(550, 300, 150, 50);


        add(back);
    }

    public static void main(String[] args) {
        //AddSuccess addSuccess = new AddSuccess();
    }
}


