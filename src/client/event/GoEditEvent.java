package client.event;

import client.base.Base;
import client.pages.ModifyingData;
import client.pages.PersonalCenter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GoEditEvent extends MouseAdapter {
    Object page;
    PersonalCenter frame;

    String id;
    public GoEditEvent(Object page, PersonalCenter frame, String id) {
        this.page = page;
        this.frame=frame;
        this.id=id;

    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == page){

            try {
                new ModifyingData(id);
                frame.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}
