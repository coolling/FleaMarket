package client.pages;

import client.component.Head;
import client.event.CheckRecordEvent;
import client.event.GoCenterEvent;
import client.event.GoIndexEvent;
import client.event.MakeComment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionListener;

public class Evaluation extends JFrame {
    String id;
    String proid;
    String comments;
    public Evaluation(String id,String proid) {
        super();
        this.id=id;
        setLayout(null);
        this.proid=proid;
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
        JLabel back = new JLabel("返回");
        CheckRecordEvent checkRecordEvent = new CheckRecordEvent(back,this,id);
        back.addMouseListener(checkRecordEvent);
        back.setForeground(new Color(131, 111, 111));
        back.setFont(new Font("华文宋体", Font.PLAIN, 27));
        back.setBounds(30, 1, 300, 70);
        head.add(back);
        JLabel welcome = new JLabel("请写下对该商品的评价");
        welcome.setForeground(new Color(131, 111, 111));
        welcome.setFont(new Font("华文宋体", Font.PLAIN, 27));
        welcome.setBounds(400, 1, 450, 70);
        head.add(welcome);
        JTextArea detailArea=new JTextArea(40,80);
        detailArea.setLineWrap(true);    //设置文本域中的文本为自动换行
        Color c1 = new Color(141,131,131);
        detailArea.setForeground(c1);    //设置字体颜色
        detailArea.setFont(new Font("楷体",Font.BOLD,20));    //修改字体样式
        detailArea.setBackground(new Color(247,248,248));    //设置背景色
        //detailArea.setEnabled(false);
        JScrollPane detailScoll=new JScrollPane(detailArea);    //将文本域放入滚动窗口
        add(detailScoll);
        detailArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                                                         @Override
                                                         public void insertUpdate(DocumentEvent e) {//这是插入操作的处理
                                                             comments = detailArea.getText().trim();
                                                             System.out.println(comments);

                                                         }

                                                         public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                                                             comments = detailArea.getText().trim();//trim()方法用于去掉你可能误输入的空格号

                                                         }


                                                         public void removeUpdate(DocumentEvent e) {//这是删除操作的处理
                                                             comments = detailArea.getText().trim();

                                                         }
                                                     }
        );
        detailScoll.setBounds(120,120,860,350);
        JButton up = new JButton("发布！");
        MakeComment makeComment = new MakeComment(up,this,id,proid);
        up.addActionListener( makeComment);
        up.setBounds(900,505,110,50);
        up.setForeground( new Color(141,131,131));
        add(up);
    }

    public String getComments() {
        return comments;
    }

    public static void main(String[] args){
       //Evaluation evaluation = new  Evaluation("111");
    }
}
