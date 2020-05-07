package client.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    ImageIcon icon;
    Image img;
    Boolean isCircle=false;// 是否设置圆角
    Boolean isImg=false;//是否设置背景图
    int redius=0;

    //圆角Jpanel且设置背景图
    public ImagePanel(String imgUrl,Boolean isCircle,int redius) {
        isImg=true;
        this.isCircle=isCircle;
        this.redius=redius;
        icon = new ImageIcon(getClass().getResource("/client/img" + imgUrl));

        img = icon.getImage();
    }
    public ImagePanel(String imgUrl,Boolean isCircle,int redius,int type) throws IOException {
        isImg=true;
        this.isCircle=isCircle;
        this.redius=redius;
        icon = new ImageIcon(ImageIO.read(new File(imgUrl)));

        img = icon.getImage();
    }
    //圆角Jpanel
    public ImagePanel(Boolean isCircle,int redius) {

        this.isCircle=isCircle;
        this.redius=redius;

    }

    //设置背景图无圆角
    public ImagePanel(String imgUrl) {
        isImg=true;
        icon = new ImageIcon(System.getProperty("user.dir")+"/src/client/img"  + imgUrl);

        img = icon.getImage();
    }
    public void paintComponent(Graphics g) {
        if(isCircle){
           // Graphics2D g2d = (Graphics2D)g;不知为何没用
            //g2d.drawRoundRect(0, 0, getWidth(), getHeight(),getWidth(), getHeight());
            RoundRectangle2D.Double rect = new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), redius, redius);

            g.setClip(rect);
        }
        super.paintComponent(g);
        if(isImg){
            //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
            g.drawImage(img, 0, 0, this.getWidth(), (int) (this.getHeight()), this);
        }

    }

}
