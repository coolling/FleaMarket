package client.event;

import client.pages.ReleaseProduct;
import client.pages.ShoppingCart;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoReleaseEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    String id;
    public GoReleaseEvent(Object page, JFrame frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){

            new ReleaseProduct(id);
            frame.dispose();
        }



    }
}
