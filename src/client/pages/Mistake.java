package client.pages;

import client.component.Head;
import client.event.GoBackEvent;
import client.event.GoLoginEvent;

import javax.swing.*;
import java.awt.*;

public class Mistake extends JFrame {
    JFrame frame =null;
    public Mistake(JFrame frame) {
        super();
        this.frame = frame;
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

        Head head = new Head("404 Not Found");
        add(head);
        JLabel mistakeWords = new JLabel("<html>抱歉，小编的服务器可能去吃炸鸡了，暂时找不到您要的界面，</br>请尝试联系工作人员：QQ：983923340 再次表示诚挚的歉意！</html>");
        mistakeWords.setFont(new Font("华文宋体", Font.PLAIN, 34));
        mistakeWords.setForeground(new Color(83, 75, 75));
        add(mistakeWords);
        mistakeWords.setBounds(100, 120, 900, 150);
        JLabel determine = new JLabel("确定");
        GoBackEvent goBackEvent =new GoBackEvent(determine,this,frame);
        determine.addMouseListener(goBackEvent);
        determine.setForeground(new Color(108, 158, 227));
        determine.setFont(new Font("华文宋体", Font.PLAIN, 27));

        determine.setBounds(520, 300, 150, 100);

        add(determine);

    }}
