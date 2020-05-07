package client.component;

import javax.swing.*;
import java.awt.*;
//页面顶部
public class Head extends JPanel {

    public Head(String title){
        Color color1 = new Color(231, 252, 243);
        this.setLayout(null);
        this.setBackground(color1);
        this.setBounds(0, 0, 1111, 75);

        JLabel myTitle = new JLabel(title);
        myTitle.setForeground(new Color(131, 111, 111));
        myTitle.setFont(new Font("华文宋体", Font.PLAIN, 27));
        myTitle.setBounds(470, 1, 300, 70);
        this.add(myTitle);
    }
}
