package client.event;

import client.pages.Index;
import client.pages.ShoppingCart;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GoIndexEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    String id;
    public GoIndexEvent(Object page, JFrame frame,String id) {
        this.id=id;
        this.page = page;
        this.frame=frame;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){
            frame.dispose();
            try {
                new Index(id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}