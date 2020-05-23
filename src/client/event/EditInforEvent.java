package client.event;

import client.pages.EditSuccess;
import client.pages.Mistake;
import client.pages.ModifyingData;
import client.socket.SaveChangeSocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditInforEvent implements ActionListener {
    String userId;

   ModifyingData frame;
    public EditInforEvent(ModifyingData jFrame, String userId){
        this.userId =userId;

        this.frame=jFrame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
          String re= SaveChangeSocket.SaveChangeServe(userId,frame.getName(),frame.getNickname(),frame.getSex(),frame.getGrade(),frame.getTelephone(),frame.getMajor(),frame.getArea());
          if(re.equals("0")){
               System.out.println("修改成功");

               new EditSuccess(userId);
              frame.dispose();
          }else{
              System.out.println("修改失败");
              frame.setVisible(false);
              new Mistake(frame);
          }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
