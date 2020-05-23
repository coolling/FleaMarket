package client.event;

import client.pages.AddSuccess;
import client.pages.Login;
import client.pages.Talking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ConnenctEvent extends MouseAdapter implements ActionListener {
    Object page;
    JFrame frame;
    String id;
    String uid;
    String proid;String name;
    public ConnenctEvent(Object page, JFrame frame,String id,String uid,String proid,String name) {
        this.page = page;
        this.frame=frame;
        this.id=id;
        this.uid=uid;
        this.proid=proid;
        this.name=name;
        System.out.println(uid);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == page){

            try {
                Talking a=    new Talking(id,uid,1,proid,name);
                frame.dispose();
                Thread t= new Thread(a);
                t.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == page){

            try {
              Talking a=  new Talking(id,uid,0,proid,name);
                frame.dispose();
                Thread t= new Thread(a);
                t.start();
                a.setT(t);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
}

