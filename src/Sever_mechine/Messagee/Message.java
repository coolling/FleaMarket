package Sever_mechine.Messagee;

import com.alibaba.fastjson.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Message {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static JSONObject msg_check(String id) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        JSONObject json1 = new JSONObject();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        List data = new ArrayList();
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
            sql = "SELECT * FROM message";

            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid_id = rs.getString(1);
                if ((aid_id.equals(id))) {
                    JSONObject json = new JSONObject();
                    json.put("sender",rs.getString(2));
                    json.put("words",rs.getString(3));
                    data.add(json);
                    flag = 0;
                }
            }// 完成后关闭
            json1.put("flag",flag);
            json1.put("data",data);
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
        System.out.println("Search Done!");
        return json1;
    }

    public static int msg_send(String sender,String accepter,String ws) throws Exception {
        Connection conn1 = null;
        Statement stmt = null;
        String words = new String(ws.getBytes("GBK"));
        PreparedStatement ps = null;
        int flag = 1;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            String sql;
            sql = "insert into message(accepter,sender,words) values(?,?,?)";

            PreparedStatement preStmt=conn1.prepareStatement(sql);
            preStmt.setString(1, accepter);
            preStmt.setString(2, sender);
            preStmt.setString(3, words);
            preStmt.executeUpdate();
            preStmt.close();
            ps.close();
            conn1.close();
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
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return 1;
            }
        }
        System.out.println("Done!");
        return 0;
    }

    public static JSONObject msgGet(String user,String id){
        JSONObject json = new JSONObject();
        return json;
    }
}
