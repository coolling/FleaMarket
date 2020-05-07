package client.event;

import client.pages.Mygoods;
import client.pages.PersonalCenter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CheckMyGoodsEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    String id;
    public CheckMyGoodsEvent(Object page, JFrame frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){
            frame.dispose();
            try {
                new Mygoods(id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}

