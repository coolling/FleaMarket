package client.event;

import client.pages.Connecter;
import client.pages.ShoppingCart;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckMessage extends MouseAdapter {
    Object page;
    JFrame frame;
    public CheckMessage(Object page, JFrame frame) {
        this.page = page;
        this.frame=frame;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){
            frame.dispose();
            new Connecter();
        }



    }
}
