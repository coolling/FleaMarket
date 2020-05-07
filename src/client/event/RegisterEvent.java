package client.event;

import client.pages.*;
import client.socket.LoginSocket;
import client.socket.RegisterSocket;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class RegisterEvent extends MouseAdapter {
    Object register;
    Register frame;
    RegisterSuccess Sframe;
    public RegisterEvent(Object register, Register frame) {
        this.register = register;
        this.frame=frame;
    }
    public RegisterEvent(Object register, RegisterSuccess Sframe) {
        this.register = register;
        this.Sframe=Sframe;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == register){
            System.out.println("mima="+frame.getPassWord());
            System.out.println("id="+frame.getUserId());
            try {
                String result = RegisterSocket.registerServe(frame.getPassWord(),frame.getUserId());
                if (result .equals("0")) {
                    frame.dispose();
                    new RegisterSuccess();
                } else {
                    frame.setVisible(false);
                    new Mistake(frame);
                }} catch (Exception ex) {
                ex.printStackTrace();
            }

        }else{
            Sframe.dispose();
            new Login();
        }


    }
}
