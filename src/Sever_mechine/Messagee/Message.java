package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置


    public static JSONObject getChatter(String id) throws Exception {
        Connection conn = null;
        Connection con = null;
        Statement stmt = null;
        JSONObject back = new JSONObject();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        List data = new ArrayList();
        PreparedStatement ps = null;
        int flag = 1;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            String chatter[] = new String[255];
            String chatter1[] = new String[255];
            String sql;
            int amount1 = 0;
            int amount2 = 0;
            sql = "SELECT * FROM temporarymsg";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                //以下临时数据库实现搜寻联系人，顺带查重；
                if (rs.getString(1).equals(id)){
                    int table = 1;
                    for (int i=0; i<amount1; i++){
                        if (rs.getString(2).equals(chatter[i])){
                            table = 0;
                            break;
                        } else {
                            table = 1;
                        }
                    }
                    if (table==1){
                        chatter[amount1] = rs.getString(2);
                        amount1++;
                    }
                } else if (rs.getString(2).equals(id)){
                    int table = 1;
                    for (int i=0; i<amount1; i++){
                        if (chatter[i].equals(rs.getString(1))){
                            table = 0;
                            break;
                        } else {
                            table = 1;
                        }
                    }
                    if (table==1){
                        chatter[amount1] = rs.getString(1);
                        amount1++;
                    }
                }//结束。
            }
            //开始进行数据的获取；
            for (int i=0; i<amount1; i++){
                String sq = "select * from user_info";
                PreparedStatement pst = con.prepareStatement(sq,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                ResultSet rst = pst.executeQuery();
                while (rst.next()){
                    if (rst.getString(1).equals(chatter[i])) {
                        JSONObject json = new JSONObject();
                        json.put("id",chatter[i]);
                        json.put("picture",rst.getString(7));
                        json.put("webname",rst.getString(6));
                        json.put("message",1);
                        data.add(json);
                        flag=1;
                    }
                }
                rst.close();
                pst.close();
            }
            //**********************************************************
            //**********************************************************
            //**********************************************************
            sql = "SELECT * FROM historymsg";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            while (rs.next()){
                //以下历史数据库实现搜寻联系人，顺带查重；
                if (rs.getString(1).equals(id)){
                    int table1 = 1;
                    int table = 1;
                    for (int i=0; i<amount1; i++){
                        if (rs.getString(2).equals(chatter[i])){
                            table1 = 0;
                            break;
                        } else {
                            table1 = 1;
                        }
                    }
                    for (int i=0; i<amount2; i++){
                        if (rs.getString(2).equals(chatter1[i])){
                            table = 0;
                            break;
                        } else {
                            table = 1;
                        }
                    }
                    if (table==1&&table1==1){
                        chatter1[amount2] = rs.getString(2);
                        amount2++;
                    }
                } else if (rs.getString(2).equals(id)){
                    int table = 1;
                    int table1 = 1;
                    for (int i=0; i<amount1; i++){
                        if (rs.getString(1).equals(chatter[i])){
                            table1 = 0;
                            break;
                        } else {
                            table1 = 1;
                        }
                    }
                    for (int i=0; i<amount2; i++){
                        if (rs.getString(1).equals(chatter1[i])){
                            table = 0;
                            break;
                        } else {
                            table = 1;
                        }
                    }
                    if (table==1&&table1==1){
                        chatter1[amount2] = rs.getString(1);
                        amount2++;
                    }
                }//结束。
            }
            //开始进行数据的获取；
            for (int i=0; i<amount2; i++){
                String sq = "select * from user_info";
                PreparedStatement pst = con.prepareStatement(sq,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                ResultSet rst = pst.executeQuery();
                while (rst.next()){
                    if (rst.getString(1).equals(chatter1[i])) {
                        JSONObject json = new JSONObject();
                        json.put("id",chatter1[i]);
                        json.put("picture",rst.getString(7));
                        json.put("webname",rst.getString(6));
                        json.put("message",0);
                        data.add(json);
                        flag=1;
                    }
                }
                rst.close();
                pst.close();
            }
            //
            ps.close();
            con.close();
            conn.close();
            back.put("data",data);
            back.put("flag",flag);
            back.put("amount",amount1+amount2);
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
            return cuo;
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
            return cuo;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return cuo;
            }
        }
        System.out.println("Done!");
        return back;
    }

    public static int msgAddToTemporary(String sender,String accepter,String ws,String time) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        String words = new String(ws.getBytes("GBK"));
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询

            String sql;
            sql = "insert into temporarymsg(sender,accepter,words,time,flag,chatter) values(?,?,?,?,?,?)";
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, sender);
            preStmt.setString(2, accepter);
            preStmt.setString(3, words);
            preStmt.setString(4,time);
            preStmt.setInt(5,0);
            preStmt.setInt(6,0);
            preStmt.executeUpdate();
            preStmt.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
            return 1;
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return 1;
            }
        }
        System.out.println("Done!");
        return 0;
    }

    public static int msgAddToHistory(String sender,String accepter,String ws,String time) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        String words = new String(ws.getBytes("GBK"));
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println(sender + " say " + "* "+ words + " *" + " to " + accepter + " at " + time);
            String sql;
            sql = "insert into historymsg(sender,accepter,words,time,chatter) values(?,?,?,?,?)";
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, sender);
            preStmt.setString(2, accepter);
            preStmt.setString(3, words);
            preStmt.setString(4,time);
            preStmt.setInt(5,0);
            preStmt.executeUpdate();
            preStmt.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
            return 1;
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return 1;
            }
        }
        return 0;
    }

    public static JSONObject msgGet(String user,String aid){
        Connection conn = null;
        Statement stmt = null;
        JSONObject json1 = new JSONObject();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        List oldData = new ArrayList();
        List newData = new ArrayList();
        PreparedStatement ps = null;
        int flag = 1;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("searching...");
            String sql;
            int amount1 = 0;
            sql = "SELECT * FROM historymsg";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if ((rs.getString(1).equals(user)&&rs.getString(2).equals(aid))) {
                    amount1++;
                    JSONObject json = new JSONObject();
                    json.put("id",rs.getString(2));
                    json.put("iden","accepter");
                    json.put("words",rs.getString(3));
                    oldData.add(json);
                    flag = 0;
                }
                else if (rs.getString(2).equals(user)&&rs.getString(1).equals(aid)){
                    amount1++;
                    JSONObject json = new JSONObject();
                    json.put("id",rs.getString(1));
                    json.put("iden","sender");
                    json.put("words",rs.getString(3));
                    oldData.add(json);
                    flag = 0;
                }
            }// 完成后关闭
            json1.put("oldAmount",amount1);
            json1.put("old",oldData);
            int amount2 = 0;
            sql = "SELECT * FROM temporarymsg";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            while (rs.next()) {
                if ((rs.getString(1).equals(user)&&rs.getString(2).equals(aid))) {
                    amount2++;
                    JSONObject json = new JSONObject();
                    json.put("id",rs.getString(2));
                    json.put("iden","accepter");
                    json.put("words",rs.getString(3));
                    rs.updateInt(5,1);
                    rs.updateRow();
                    newData.add(json);
                    flag = 0;
                }
                else if (rs.getString(2).equals(user)&&rs.getString(1).equals(aid)){
                    amount2++;
                    JSONObject json = new JSONObject();
                    json.put("id",rs.getString(1));
                    json.put("iden","sender");
                    json.put("words",rs.getString(3));
                    rs.updateInt(5,1);
                    rs.updateRow();
                    newData.add(json);
                    flag = 0;
                }
            }// 完成后关闭
            exchange(user);
            json1.put("newAmount",amount2);
            json1.put("flag",flag);
            json1.put("new",newData);
            rs.close();
            ps.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
            return cuo;
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
            return cuo;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return cuo;
            }
        }
        System.out.println("Done!");
        return json1;
    }

    public static void exchange(String aid){
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动user
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            String sql = "select * from temporarymsg";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(5)==1){
                    String sender = rs.getString(1);
                    String accepter = rs.getString(2);
                    String words = rs.getString(3);
                    String time = rs.getString(4);
                    msgAddToHistory(sender,accepter,words,time);
                }
            }
            sql = "DELETE FROM temporarymsg where flag='1'";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            rs.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static int msgCheck(String accepter){
        int back = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动user
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            String sql = "select * from temporarymsg";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equals(accepter)){
                    back = 1;
                    break;
                }
            }
            rs.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return back;
    }
}
