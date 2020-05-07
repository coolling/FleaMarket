package client.event;

import client.pages.Login;
import client.pages.Mygoods;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoLoginEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    JFrame frame2;
    public GoLoginEvent(Object page, JFrame frame) {
        this.page = page;
        this.frame=frame;

    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){
            frame.dispose();
            new Login();
        }



    }
}
