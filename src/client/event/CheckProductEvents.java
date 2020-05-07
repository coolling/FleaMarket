package client.event;

import client.component.ImagePanel;
import client.pages.ProductDetails;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class CheckProductEvents extends MouseAdapter {
    public Object imgProduct = null;
    JFrame frame = null;
String id;
String proId;
    String name;
    public CheckProductEvents(Object imgProduct, JFrame frame,String proID,String id,String name) {
        this.imgProduct = imgProduct;
        this.frame = frame;
        this.id =id;
        proId =proID;
        this.name=name;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == imgProduct) {

            frame.dispose();
            try {
                new ProductDetails(proId,name,id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
