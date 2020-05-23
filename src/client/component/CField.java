package client.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class CField extends JPasswordField implements FocusListener {
    int redius = 0;
    int type =1;
    String text = "";
    public CField(int x, int redius) {
        super(x);
        this.redius = redius;
        setFont(new Font("华文宋书", Font.PLAIN, 22));


    }
    public CField(int x, int redius, String text) {
        super(x);
        this.redius = redius;
        this.text=text;
        setFont(new Font("华文宋书", Font.PLAIN, 22));
        this.setText(text);

    }

    public CField(int x, int redius, int type, String text) {
        super(x);
        this.redius = redius;
        this.type =type;
        this.text=text;
        if (type == 0) {

            setOpaque(false); //设置背景透明

            setFont(new Font("华文宋书", Font.PLAIN, 28));
            setForeground(new Color(112, 112, 112));
        }
        this.setText(text);

    }
    public void focusGained(FocusEvent e){

        if(this.getText().equals(text)){

            this.setText("");
        }
    }
    public void focusLost(FocusEvent e){
        if(this.getText().equals("")){

            this.setText(text);
        }
    }
    public void paint(Graphics g) {
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 5, 5);
        g.setClip(rect);
        //自定义边框
        if(type==0){
            setBorder(new RoundBorder(redius,Color.WHITE));
        }else
            setBorder(new RoundBorder(redius, new Color(112, 112, 112)));
        super.paint(g);
    }
}
