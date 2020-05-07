package client.event;

import client.pages.AddSuccess;
import client.pages.Mistake;
import client.pages.ShoppingCart;
import client.socket.AddCartSocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AddToCartEvent implements ActionListener {
    Object page;
    JFrame frame;
    String id;
    String goodsName;
    float goodsPrice;
    int goodsAmount;
    String goodId;
    public AddToCartEvent(Object page, JFrame frame,String id,String goodsName, float goodsPrice, int goodsAmount, String goodId) {
        this.page = page;
        this.frame=frame;
        this.id=id;
        this.goodId=goodId;
        this.goodsAmount=goodsAmount;
        this.goodsName=goodsName;
        this.goodsPrice=goodsPrice;
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == page){
            try {
                String re = AddCartSocket.addServer(id,goodsName,goodsPrice,goodsAmount,goodId);
                if(re.equals("0")){
                    frame.dispose();
                    new AddSuccess(id,goodId,goodsName);
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


