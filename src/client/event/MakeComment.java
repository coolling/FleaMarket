package client.event;

import client.pages.*;
import client.socket.AddCartSocket;
import client.socket.ComSocket;
import client.socket.RegisterSocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MakeComment implements ActionListener {
    Object page;
    Evaluation frame;
    String id;
    String proid;

    public MakeComment(Object page, Evaluation frame,String id,String proid) {
        this.page = page;
        this.frame=frame;
        this.id=id;
        this.proid=proid;
      //  this.comments=frame.getComments();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == page){
            try {
                String re = ComSocket.comServer(id,proid,frame.getComments());
                if(re.equals("0")){
                    frame.dispose();
                   // System.out.println(comments);
                    new SureSuccess(id,"评论成功！");
                }else {
                    frame.setVisible(false);
                    new Mistake(frame);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
