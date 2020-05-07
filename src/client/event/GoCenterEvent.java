package client.event;

import client.pages.Index;
import client.pages.PersonalCenter;
import client.pages.Register;
import client.socket.PerCenterSocket;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GoCenterEvent extends MouseAdapter {
    Object page;
    JFrame frame;
    String id;
    public GoCenterEvent(Object page, JFrame frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){
            frame.dispose();
            try {
               PerCenterSocket.goCenterServe(id);
                new PersonalCenter(id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}

