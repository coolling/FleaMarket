package Sever_mechine.Messagee;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class ManageClientThread {

    public static HashMap hm = new HashMap<String,HashMap>();

    //向HashMap中添加一个客户端通讯线程
    public static void addHashMap (String userId){
        HashMap every = new HashMap<String, Socket>();
        hm.put(userId,every);

    }

    public static void addClientThread (String accepet, OnlineThread hh,String sender){
        HashMap every =(HashMap) hm.get(sender);
        every.put(accepet,hh);
    }

    public static OnlineThread getClientThread (String userId, String aidId){
        //某用户(用户1)userId用于获得他想要与之聊天的用户(用户2)aidId的聊天窗口打开状态；
        HashMap hhh = (HashMap) hm.get(aidId);
        if(hhh!=null){
            System.out.println("hhh!=null");
            //获得用户2的有关聊天窗口的hashMap
            OnlineThread back = (OnlineThread)hhh.get(userId);
            if (back==null) {
                System.out.println("back==null");
                return null;}
            else return back;
            //从其中寻找用户1的窗口
        }else{
          return   null;
        }

    }

    public static void closeClientThread(String user, String aid) throws IOException {
        //某用户1关掉了他与用户2聊天的窗口；
        HashMap hhhh = (HashMap) hm.get(user);
        OnlineThread back = (OnlineThread)hhhh.get(aid);
        back.s.close();
        back.s=null;
        //找到用户1的hashMap
        hhhh.remove(aid);
        //清楚与用户2的连接
    }
}
