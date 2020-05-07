package client.event;

import client.pages.AddSuccess;
import client.pages.BuySuccess;
import client.pages.DeleteSuccess;
import client.pages.Mistake;
import client.socket.AddCartSocket;
import client.socket.BuySocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BuyEvent implements ActionListener {
    Object page;
    JFrame frame;
    String id;

    int goodsAmount;
    String goodId;
    String goodName;
    public BuyEvent(Object page, JFrame frame,String id, int goodsAmount, String goodId,String goodName) {
        this.page = page;
        this.frame=frame;
        this.id=id;
        this.goodId=goodId;
        this.goodsAmount=goodsAmount;
         this.goodName=goodName;
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == page){
            try {
                String re = BuySocket.buyServe(Integer.parseInt(goodId), id, goodsAmount);
                if (re.equals("0")) {
                    frame.dispose();
                    new BuySuccess(id,goodId,goodName);
                } else {
                    frame.setVisible(false);
                    new Mistake(frame);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }



    }
}



