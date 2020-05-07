package client.event;

import client.pages.ShoppingCart;
import client.pages.TransactionRecord;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CheckRecordEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    String id;
    public CheckRecordEvent(Object page, JFrame frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){
            frame.dispose();
            try {
                new TransactionRecord(id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}
