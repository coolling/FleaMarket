package client.component;

import javax.swing.*;
import java.awt.*;

public class CLabel extends JLabel {
    public CLabel(String text){
        super(text);
        this.setFont(new Font("华文宋体", Font.PLAIN, 28));
        this.setForeground(new Color(112, 112, 112));
    }

}
