package client.event;

import client.base.Base;
import client.pages.*;
import client.socket.LoginSocket;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LoginEvent extends MouseAdapter {
    Object login;
    Login frame;



    public LoginEvent(Object login, Login frame) {
        this.login = login;
        this.frame = frame;

    }



    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == login) {
            System.out.println("mima="+frame.getPassWord());
            System.out.println("id="+frame.getUserId());
                try {
                    String result =LoginSocket.loginServe(frame.getPassWord(),frame.getUserId());
                    if (result .equals("0")) {

                        frame.dispose();
                        Base.counts++;
                        new Index(frame.getUserId());
                    } else {
                       frame.setVisible(false);
                        new LoginMiss(frame);
                    }} catch (Exception ex) {
                    ex.printStackTrace();
                }



        } else {
            frame.dispose();
            new Register();
        }


    }
}

