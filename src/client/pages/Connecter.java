package client.pages;

import client.component.*;
import client.event.GoCenterEvent;
import client.event.RegisterEvent;

import javax.swing.*;
import java.awt.*;

public class Connecter extends JFrame {
    JScrollPane talkDiv = new JScrollPane();
    JPanel talkdiv = new JPanel();
    JScrollPane connectorDiv = new JScrollPane();
    JPanel connectordiv = new JPanel();
    String[][] connectors = {{"/WechatIMG10.jpeg", "happy"}, {"/WechatIMG10.jpeg", "sad"}, {"/WechatIMG10.jpeg", "cry"}, {"/WechatIMG10.jpeg", "happy"}, {"/WechatIMG10.jpeg", "sad"}, {"/WechatIMG10.jpeg", "cry"}, {"/WechatIMG10.jpeg", "happy"}, {"/WechatIMG10.jpeg", "sad"}, {"/WechatIMG10.jpeg", "cry"}};
    String talkings[][] = {{"/WechatIMG10.jpeg", "JSON 独立于语言：JSON 使用 Javascript语法来描述数据对象，但是 JSON 仍然独立于语言和平台。JSON 解析器和 JSON 库支持许多不同的编程语言。 目前非常多的动态（PHP，JSP，.NET）编程语言都支持JSON。", "0"},
            {"/WechatIMG12.jpeg", "hello", "1"},
            {"/WechatIMG10.jpeg", "JSON 独立于语言：JSON 使用 Javascript语法来描述数据对象，但是 JSON 仍然独立于语言和平台。JSON 解析器和 JSON 库支持许多不同的编程语言。 目前非常多的动态（PHP，JSP，.NET）编程语言都支持JSON。", "0"},
            {"/WechatIMG12.jpeg", "hello", "1"}};
    public Connecter() {
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
        JLabel back = new JLabel("返回个人中心");
       // GoCenterEvent goCenterEvent = new GoCenterEvent(back, this);
       // back.addMouseListener(goCenterEvent);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行

        connectorDiv.setBounds(0, 75, 350, 530);
        connectordiv.setBackground(Color.white);
        connectordiv.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
        connectorDiv.setViewportView(connectordiv);
        connectordiv.setPreferredSize(new Dimension(345, 80 * (connectors.length)));

        add(connectorDiv);


        talkDiv.setBounds(350, 75, 761, 430);
        talkdiv.setBackground(Color.white);
        talkdiv.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
        talkDiv.setViewportView(talkdiv);
        talkdiv.setPreferredSize(new Dimension(755, 200*(talkings.length)));

        add(talkDiv);
        showConnector();
        showTalking();
        talkDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        connectorDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        RediusTextField write = new  RediusTextField(30,0);
        write.setBounds(400,540,500,40);
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
            while(words.length()>=22){
                words=words.substring(22);
                row++;
            }
            JPanel all = new JPanel();
            all.setBackground(Color.white);
            all.setLayout(null);
            JPanel sentence = new JPanel();
            sentence.setBackground(Color.white);
            all.setPreferredSize(new Dimension(755, 80+(row-1)*30));
            ImagePanel head = new ImagePanel(talkings[i][0]);
            all.add(head);
            sentence.setBounds(80, 20, 595, 80*row);

            if (talkings[i][2].equals("0")) {

                head.setBounds(15, 15, 50, 50);
                sentence.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));



            } else if (talkings[i][2].equals("1")) {

                head.setBounds(690, 15, 50, 50);
                sentence.setLayout(new FlowLayout(FlowLayout.RIGHT));

            }
            while(row>0){
                CLabel awords;
                if(row==1){
                    awords = new CLabel(words2);
                }else {

                    awords = new CLabel(words2.substring(0,22));
                    words2=words2.substring(22);
                }
                awords.setPreferredSize(new Dimension(words2.length()*24,30));
                row--;
                awords.setFont((new Font("华文宋体", Font.PLAIN, 24)));
                sentence.add(awords);

            }
            all.add(sentence);
            talkdiv.add(all);
        }
    }

    private void showConnector() {
        for (int i = 0; i < connectors.length; i++) {

            JPanel aConnectoer = new JPanel();
            aConnectoer.setLayout(null);
            ImagePanel head = new ImagePanel(connectors[i][0]);


            CLabel name = new CLabel(connectors[i][1]);
            aConnectoer.add(head);
            aConnectoer.add(name);

            connectordiv.add(aConnectoer);
            aConnectoer.setPreferredSize(new Dimension(345, 80));
            name.setBounds(105, 27, 200, 30);
            head.setBounds(15, 15, 50, 50);
            aConnectoer.setBackground(new Color(169, 79, 249, 14));
        }
    }

    public static void main(String[] args) {
        Connecter Connecter = new Connecter();
    }
}

