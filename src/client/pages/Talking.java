package client.pages;

import client.component.CLabel;
import client.component.Head;
import client.component.ImagePanel;
import client.component.RediusTextField;
import client.event.CheckProductEvents;
import client.event.GoCenterEvent;

import javax.swing.*;
import java.awt.*;

public class Talking extends JFrame {
    JScrollPane talkDiv = new JScrollPane();
    JPanel talkdiv = new JPanel();
 String id;
 String name;
   String talkings[][] = {{"/WechatIMG10.jpeg", "JSON 独立于语言：JSON 使用 Javascript语法来描述数据对象，但是 JSON 仍然独立于语言和平台。JSON 解析器和 JSON 库支持许多不同的编程语言。 目前非常多的动态（PHP，JSP，.NET）编程语言都支持JSON。", "0"},
            {"/WechatIMG12.jpeg", "hello", "1"},
            {"/WechatIMG10.jpeg", "JSON 独立于语言：JSON 使用 Javascript语法来描述数据对象，但是 JSON 仍然独立于语言和平台。JSON 解析器和 JSON 库支持许多不同的编程语言。 目前非常多的动态（PHP，JSP，.NET）编程语言都支持JSON。", "0"},
            {"/WechatIMG12.jpeg", "hello", "1"}};
    public Talking(String id,String name) {
        super();
this.id =id;
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
        JLabel welcome = new JLabel("Welcome to flea market!");
        welcome.setForeground(new Color(131, 111, 111));
        welcome.setFont(new Font("华文宋体", Font.PLAIN, 27));
        welcome.setBounds(400, 1, 350, 70);
        head.add(welcome);
        JLabel back = new JLabel("返回");
        CheckProductEvents checkProductEvents = new CheckProductEvents(back,this,1+"",id,name);
        back.addMouseListener(checkProductEvents);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行

        talkDiv.setBounds(0, 75, 1111, 430);
        talkdiv.setBackground(Color.white);
        talkdiv.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
        talkDiv.setViewportView(talkdiv);
        talkdiv.setPreferredSize(new Dimension(755, 200*(talkings.length)));

        add(talkDiv);

        showTalking();
        talkDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        RediusTextField write = new  RediusTextField(30,0);
        write.setBounds(120,540,780,40);
        add(write);
        CLabel send = new CLabel("   发送");

        send.setForeground( Color.white);
        send.setFont(new Font("华文宋体", Font.PLAIN, 20));
        ImagePanel sends = new ImagePanel(true,25);
        sends.setLayout(new BorderLayout());
        sends.setBounds(930,540,80,40);

        sends.setBackground(new Color(108, 158, 227));
        sends.add(send);
        add(sends);
    }

    private void showTalking() {
        for (int i = 0; i < talkings.length; i++) {
            int row=1;
            String words = talkings[i][1].substring(0);
            String words2 = talkings[i][1].substring(0);
            while(words.length()>=25){
                words=words.substring(25);
                row++;
            }
            JPanel all = new JPanel();
            all.setBackground(Color.white);
            all.setLayout(null);
            JPanel sentence = new JPanel();
            sentence.setBackground(Color.white);
            all.setPreferredSize(new Dimension(1111, 80+(row-1)*30));
            ImagePanel head = new ImagePanel(talkings[i][0]);
            all.add(head);
            sentence.setBounds(105, 20, 890, 80*row);

            if (talkings[i][2].equals("0")) {

                head.setBounds(25, 15, 50, 50);
                sentence.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));



            } else if (talkings[i][2].equals("1")) {

                head.setBounds(1010, 15, 50, 50);
                sentence.setLayout(new FlowLayout(FlowLayout.RIGHT));

            }
            while(row>0){
                JLabel awords;
                if(row==1){
                    if(talkings[i][2].equals("1")){
                        awords = new JLabel(words2,JLabel.RIGHT);
                    }else {
                        awords = new JLabel(words2);
                    }





                    awords.setForeground(new Color(112, 112, 112));
                }else {

                    awords = new CLabel(words2.substring(0,25));
                    words2=words2.substring(25);
                }
                awords.setPreferredSize(new Dimension(words2.length()*50,30));
                row--;
                awords.setFont((new Font("华文宋体", Font.PLAIN, 24)));
                sentence.add(awords);

            }
            all.add(sentence);
            talkdiv.add(all);
        }
    }


    public static void main(String[] args) {
    //    Talking Connecter = new Talking();
    }
}

