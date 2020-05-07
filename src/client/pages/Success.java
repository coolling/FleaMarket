package client.pages;

import javax.swing.*;
import java.awt.*;

public class Success extends JFrame {
    String title;
    String successText;
    public Success(String title,String successText) {
        super();
        this.title=title;
        this.successText=successText;
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
        JPanel head = new JPanel();
        head.setLayout(null);
        head.setBackground(color1);
        head.setBounds(0, 0, 1111, 75);
        add(head);
        JLabel modify = new JLabel(title);
        modify.setForeground(new Color(131, 111, 111));
        modify.setFont(new Font("华文宋体", Font.PLAIN, 27));
        modify.setBounds(490, 1, 200, 70);
        head.add(modify);
        JLabel success = new JLabel(successText);
        success.setFont(new Font("华文宋体", Font.PLAIN, 44));
        success.setForeground(new Color(83, 75, 75));
        add(success);
        success.setBounds(490, 200, 200, 60);
        JLabel press = new JLabel("点击");
        JLabel back = new JLabel("返回");
        press.setForeground(new Color(131, 111, 111));
        press.setFont(new Font("华文宋体", Font.PLAIN, 27));
        back.setForeground(new Color(108, 158, 227));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        press.setBounds(520, 300,150,100);
        back.setBounds(575, 300,150,100);
        add(press);
        add(back);
    }

    public static void main(String[] args){Success modifySuccess = new Success("修改个人资料","修改成功");
    }
}
