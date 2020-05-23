package client.event;

import client.pages.PersonalCenter;
import client.pages.ShoppingCart;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CheckCartEvevt extends MouseAdapter {
    Object page;
    JFrame frame;
    String id;
    public CheckCartEvevt(Object page, JFrame frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){

            try {
                new ShoppingCart(id);
                frame.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}
