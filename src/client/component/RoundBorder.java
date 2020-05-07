package client.component;

import javax.swing.border.Border;
import java.awt.*;

//设置圆角边框 可以自定义边框的颜色
public class RoundBorder implements Border {
    private Color color;
    int redius;


    public RoundBorder(int redius,Color color) {
        this.color = color;

        this.redius = redius;
    }

    //接口中的方法 必须实现
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    //接口中的方法 必须实现
    public boolean isBorderOpaque() {
        return false;
    }

    // 实现Border（父类）方法
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
                            int height) {
        g.setColor(color);
        g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, redius, redius);

    }
}
