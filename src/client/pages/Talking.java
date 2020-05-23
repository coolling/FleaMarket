package client.pages;

import client.base.Base;
import client.component.CLabel;
import client.component.Head;
import client.component.ImagePanel;
import client.component.RediusTextField;
import client.event.CheckProductEvents;
import client.event.EndTalk;
import client.event.GoCenterEvent;
import client.socket.SearchSocket;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Talking extends JFrame implements Runnable {
    JScrollPane talkDiv = new JScrollPane();
    JPanel talkdiv = new JPanel();
    String id;
    String name;
    String uid;
    String proid;
    String talkings[][] = {{"/WechatIMG10.jpeg", "JSON 独立于语言：JSON 使用 编程语言都支持JSON。", "0"},
            {"/WechatIMG12.jpeg", "hello", "1"},
            {"/WechatIMG10.jpeg", "JSON编程语言都支持JSON。", "0"},
            {"/WechatIMG12.jpeg", "hello", "1"}};
    Socket s = new Socket("127.0.0.1", Base.talk);
    int type;
    Thread t;

    public Talking(String id, String uid, int type, String proid, String name) throws IOException {
        super();
        this.id = id;
        this.uid = uid;
        this.type = type;
        this.name = name;
        this.proid = proid;
        setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获得默认的底层控件的基本功能
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - 1111) / 2;
        int y = (screen.height - 625) / 2;
        setBounds(x, y, 1111, 625);//设置窗口居中

        System.out.print("请求连接");

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//客户端输入流，接收服务器消息
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷新
            //接受用户信息
            String msg = null;


            JSONObject joson = new JSONObject();
            //string

            joson.put("userId", id);
            joson.put("aidId", uid);

            String string = joson.toString();
            msg = string;
            System.out.println(msg);
            pw.println(msg);


        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = new Socket("127.0.0.1", Base.his);
        System.out.print("请求连接");

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//客户端输入流，接收服务器消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷新
            //接受用户信息
            String msg = null;


            JSONObject joson = new JSONObject();
            //string

            joson.put("user", id);
            joson.put("aid", uid);

            String string = joson.toString();
            msg = string;
            System.out.println(msg);
            pw.println(msg);

            String strInputstream = br.readLine();
            System.out.println("输入信息为：" + strInputstream);
            JSONObject js = JSONObject.parseObject(strInputstream);
            System.out.println(js);
            shows(js);
        } catch (IOException e) {
            e.printStackTrace();

        }
        view();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public Thread getT() {
        return t;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    private void shows(JSONObject js) {
        talkings = new String[js.getIntValue("oldAmount") + js.getIntValue("newAmount")][3];

        for (int i = 0; i < js.getIntValue("oldAmount"); i++) {
            JSONObject a = (JSONObject) (js.getJSONArray("old").get(i));

            talkings[i][1] = a.getString("words");
            if (a.getString("iden").equals("accepter")) {
                talkings[i][2] = "1";
                talkings[i][0] = "/headsource" + Base.counts + ".jpg";
            } else {
                talkings[i][2] = "0";
                talkings[i][0] = "/chater" + uid + ".jpg";
            }
        }
        for (int i = js.getIntValue("oldAmount"); i < js.getIntValue("oldAmount") + js.getIntValue("newAmount"); i++) {
            JSONObject a = (JSONObject) (js.getJSONArray("new").get(i - js.getIntValue("oldAmount")));

            talkings[i][1] = a.getString("words");
            if (a.getString("iden").equals("accepter")) {
                talkings[i][2] = "1";
                talkings[i][0] = "/headsource" + Base.counts + ".jpg";
            } else {
                talkings[i][2] = "0";
                talkings[i][0] = "/chater" + uid + ".jpg";
            }


        }

    }

    private void view() throws IOException {
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
        EndTalk talk;
        if (type == 0) {
            talk = new EndTalk(s, back, this, id, uid, 0);

        } else {
            talk = new EndTalk(s, back, this, id, uid, 1, proid, name);

        }

        //CheckProductEvents checkProductEvents = new CheckProductEvents(back, this, 1 + "", id, name);
        back.addMouseListener(talk);
        back.setBounds(25, 23, 250, 30);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        head.add(back);//用add(back)不行

        talkDiv.setBounds(0, 75, 1111, 430);
        talkdiv.setBackground(Color.white);
        talkdiv.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
        talkDiv.setViewportView(talkdiv);
        talkdiv.setPreferredSize(new Dimension(755, 70 * (talkings.length)));

        add(talkDiv);

        showTalking();
        talkDiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        RediusTextField write = new RediusTextField(30, 0);
        write.setBounds(120, 540, 780, 40);
        add(write);
        CLabel send = new CLabel("   发送");

        send.setForeground(Color.white);
        send.setFont(new Font("华文宋体", Font.PLAIN, 20));
        ImagePanel sends = new ImagePanel(true, 25);
        sends.setLayout(new BorderLayout());
        sends.setBounds(930, 540, 80, 40);
        class sendEvent extends MouseAdapter {
            Object page;


            public sendEvent(Object page) {
                this.page = page;


            }

            public void mouseClicked(MouseEvent e) {

                if (e.getSource() == page) {
                    System.out.println(write.getText());


                    try {
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//客户端输入流，接收服务器消息
                        // BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷新
                        //接受用户信息
                        String msg = null;


                        JSONObject joson = new JSONObject();
                        //string

                        joson.put("sender", id);
                        joson.put("accepter", uid);
                        joson.put("words", write.getText());
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        System.out.println(df.format(new Date()));// new Date()
                        joson.put("time", df.format(new Date()));
                        String string = joson.toString();
                        msg = string;
                        System.out.println(msg);
                        pw.println(msg);

                        int row = 1;
                        String words = write.getText();
                        String words2 = write.getText();
                        while (words.length() >= 25) {
                            words = words.substring(25);
                            row++;
                        }
                        JPanel all = new JPanel();
                        all.setBackground(Color.white);
                        all.setLayout(null);
                        JPanel sentence = new JPanel();
                        sentence.setBackground(Color.white);
                        all.setPreferredSize(new Dimension(1111, 60 + (row - 1) * 30));
                        ImagePanel head = new ImagePanel("/headsource" + Base.counts + ".jpg");
                        all.add(head);
                        sentence.setBounds(105, 20, 890, 80 * row);


                        head.setBounds(1010, 15, 50, 50);
                        sentence.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        while (row > 0) {
                            JLabel awords;
                            if (row == 1) {

                                awords = new JLabel(words2, JLabel.RIGHT);


                                awords.setForeground(new Color(112, 112, 112));
                            } else {

                                awords = new CLabel(words2.substring(0, 25));
                                words2 = words2.substring(25);
                            }
                            awords.setPreferredSize(new Dimension(words2.length() * 50, 30));
                            row--;
                            awords.setFont((new Font("华文宋体", Font.PLAIN, 24)));
                            sentence.add(awords);

                        }
                        all.add(sentence);
                        talkdiv.setPreferredSize(new Dimension(755, 70 + talkdiv.getHeight()));
                        talkdiv.add(all);
                        JScrollBar jScrollBar = talkDiv.getVerticalScrollBar();
                        jScrollBar.setMaximum(talkdiv.getHeight());
                        jScrollBar.setValue(jScrollBar.getMaximum());
                        write.setText("");
                        repaint();
                        revalidate();
//                        String strInputstream = br.readLine();
//                        System.out.println("输入信息为：" + strInputstream);
//                        JSONObject js = JSONObject.parseObject(strInputstream);
//                        System.out.println(js);


                    } catch (IOException qe) {
                        qe.printStackTrace();
                    }

                }


            }
        }
        sendEvent sendEvent = new sendEvent(sends);
        sends.addMouseListener(sendEvent);
        sends.setBackground(new Color(108, 158, 227));
        sends.add(send);
        add(sends);
    }

    private void showTalking() {

        for (int i = 0; i < talkings.length; i++) {
            int row = 1;
            String words = talkings[i][1].substring(0);
            String words2 = talkings[i][1].substring(0);
            while (words.length() >= 25) {
                words = words.substring(25);
                row++;
            }
            JPanel all = new JPanel();
            all.setBackground(Color.white);
            all.setLayout(null);
            JPanel sentence = new JPanel();
            sentence.setBackground(Color.white);
            all.setPreferredSize(new Dimension(1111, 60 + (row - 1) * 30));
            ImagePanel head = new ImagePanel(talkings[i][0]);
            all.add(head);
            sentence.setBounds(105, 20, 890, 80 * row);

            if (talkings[i][2].equals("0")) {

                head.setBounds(25, 15, 50, 50);
                sentence.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));


            } else if (talkings[i][2].equals("1")) {

                head.setBounds(1010, 15, 50, 50);
                sentence.setLayout(new FlowLayout(FlowLayout.RIGHT));

            }
            while (row > 0) {
                JLabel awords;
                if (row == 1) {
                    if (talkings[i][2].equals("1")) {
                        awords = new JLabel(words2, JLabel.RIGHT);
                    } else {
                        awords = new JLabel(words2);
                    }


                    awords.setForeground(new Color(112, 112, 112));
                } else {

                    awords = new CLabel(words2.substring(0, 25));
                    words2 = words2.substring(25);
                }
                awords.setPreferredSize(new Dimension(words2.length() * 50, 30));
                row--;
                awords.setFont((new Font("华文宋体", Font.PLAIN, 24)));
                sentence.add(awords);

            }
            all.add(sentence);
            talkdiv.add(all);
            JScrollBar jScrollBar = talkDiv.getVerticalScrollBar();
            jScrollBar.setMaximum(talkdiv.getHeight());
            jScrollBar.setValue(jScrollBar.getMaximum());
        }

    }


    public static void main(String[] args) {// Talking Connecter = new Talking("1","!");
    }

    @Override
    public void run() {
        while (true) {

            if (s != null) {
                try {
                    //Thread.sleep(1000);
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    JSONObject jsonObject = (JSONObject) ois.readObject();
                    String[][] atalkings = new String[talkings.length + 1][3];
                    int row = 1;
                    String words = jsonObject.getString("words");
                    String words2 = jsonObject.getString("words");
                    while (words.length() >= 25) {
                        words = words.substring(25);
                        row++;
                    }
                    JPanel all = new JPanel();
                    all.setBackground(Color.white);
                    all.setLayout(null);
                    JPanel sentence = new JPanel();
                    sentence.setBackground(Color.white);
                    all.setPreferredSize(new Dimension(1111, 60 + (row - 1) * 30));
                    ImagePanel head = new ImagePanel("/chater" + uid + ".jpg");
                    all.add(head);
                    sentence.setBounds(105, 20, 890, 80 * row);


                    head.setBounds(25, 15, 50, 50);
                    sentence.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));


                    while (row > 0) {
                        JLabel awords;
                        if (row == 1) {

                            awords = new JLabel(words2);


                            awords.setForeground(new Color(112, 112, 112));
                        } else {

                            awords = new CLabel(words2.substring(0, 25));
                            words2 = words2.substring(25);
                        }
                        awords.setPreferredSize(new Dimension(words2.length() * 50, 30));
                        row--;
                        awords.setFont((new Font("华文宋体", Font.PLAIN, 24)));
                        sentence.add(awords);

                    }
                    all.add(sentence);
                    talkdiv.add(all);
                    talkdiv.setPreferredSize(new Dimension(755, 70 + talkdiv.getHeight()));
                    JScrollBar jScrollBar = talkDiv.getVerticalScrollBar();
                    jScrollBar.setMaximum(talkdiv.getHeight());
                    jScrollBar.setValue(jScrollBar.getMaximum());
                    repaint();
                    revalidate();
//        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        if(br.readLine()!=null){
//            String strInputstream = br.readLine();
//            System.out.println("聊天输入信息为：" + strInputstream);
//            JSONObject js = JSONObject.parseObject(strInputstream);
//            System.out.println(js);
//
//        }

                    System.out.println(jsonObject.toString());

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else {
               break;
            }
        }

    }


}

