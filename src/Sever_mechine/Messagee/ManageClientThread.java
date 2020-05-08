package Sever_mechine.Messagee;

import java.util.*;

public class ManageClientThread {

    public static HashMap hm = new HashMap<String,OnlineThread>();

    //向HashMap中添加一个客户端通讯线程
    public static void addClientThread (String userId, OnlineThread ot){
        hm.put(userId,ot);
    }

    public static OnlineThread getClientThread (String userId){
        return (OnlineThread)hm.get(userId);
    }
}
