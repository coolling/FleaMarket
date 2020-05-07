package client.event;

import client.pages.*;
import client.socket.RegisterSocket;
import client.socket.ReleaseSocket;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReleaseEvent extends MouseAdapter {
    Object page;
    ReleaseProduct frame;
    String id;
    public ReleaseEvent(Object page, ReleaseProduct frame,String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){

            try {
                String result = ReleaseSocket.ReleaseServer(id,frame.getName(),frame.getPrice(),frame.getDetail(),frame.getAmount());
                if (result .equals("0")) {
                    frame.dispose();
                    new ReleaseSuccess(id);
                } else {
                    frame.setVisible(false);
                    new Mistake(frame);
                }} catch (Exception ex) {
                ex.printStackTrace();
            }

        }




    }
}

