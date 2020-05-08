package client.event;

import client.pages.AddSuccess;
import client.pages.Talking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnenctEvent implements ActionListener {
    Object page;
    JFrame frame;
    String id;
    public ConnenctEvent(Object page, JFrame frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == page){
            frame.dispose();new Talking();
        }



    }
}

