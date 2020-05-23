package client.event;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoBackEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    JFrame frame2;
    public GoBackEvent(Object page, JFrame frame,JFrame frame2) {
        this.page = page;
        this.frame=frame;
        this.frame2=frame2;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){

            frame2.setVisible(true);
            frame.dispose();
        }



    }
}

