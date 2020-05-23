package Sever_mechine.TR;

import com.alibaba.fastjson.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRecord {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static int add(String buyer, String seller,String goods,int amount,int id) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        int flag = 0;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            String sql="insert into t_record(buyer,seller,goods,amount,state,goodid,time) values(?,?,?,?,?,?,?)";//会抛出异常
            PreparedStatement preStmt=conn.prepareStatement(sql);
            preStmt.setString(1, buyer);
            preStmt.setString(2, seller);
            preStmt.setString(3, goods);
            preStmt.setInt(4, amount);
            preStmt.setInt(5,1);
            preStmt.setInt(6,id);
            preStmt.setString(7,time);
            preStmt.executeUpdate();
            // 完成后关闭
            preStmt.close();
            ps.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
            return 1;
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
            return 1;
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
        return flag;
    }

    public static JSONObject see(String id) {
        JSONObject json = new JSONObject();
        json.put("flag",0);
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        List data = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        int i = 0;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("searching...");

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM t_record";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                if (id.equals(rs.getString(1))) {
                    JSONObject json1 = new JSONObject();
                    json1.put("iden","buyer");
                    json1.put("goods",rs.getString(3));
                    json1.put("amount",rs.getInt(4));
                    json1.put("state",rs.getInt(5));
                    json1.put("goodsId",rs.getInt(6));
                  //  json1.put("time",rs.getString(7));
                    data.add(json1);
                    i++;
                }
                if (id.equals(rs.getString(2))) {
                    JSONObject json1 = new JSONObject();
                    json1.put("iden","seller");
                    json1.put("goods",rs.getString(3));
                    json1.put("amount",rs.getInt(4));
                    json1.put("state",rs.getInt(5));
                    json1.put("goodsId",rs.getInt(6));
                 //   json1.put("goodId",rs.getInt(6));
                    data.add(json1);
                    i++;
                }
            }// 完成后关闭
            json.put("data",data);
            json.put("length",i);
            rs.close();
            stmt.close();
            conn.close();
        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
            return cuo;
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
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
            }
        }
        return json;
    }

    public static int receive(String user, int id){
        int flag = 0;
        Connection conn1 = null;
        Statement stmt1 = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("finding...");
            String sql;
            sql = "SELECT * FROM t_record";
            stmt = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String aid_user = rs.getString(1);
                int aid_id = rs.getInt(6);
                if (aid_user.equals(user)&&aid_id==id) {
                    System.out.println("find it");
                    rs.updateInt(5,0);
                    rs.updateRow();
                }
            }
            rs.close();
            stmt.close();
            conn1.close();
        }// 展开结果集数据库
        catch (SQLException se)
        {
            se.printStackTrace();// 处理 JDBC 错误
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();// 处理 Class.forName 错误
            return 1;
        }
        finally
        {
            try
            {
                if (stmt1 != null)
                    stmt1.close();
            }// 关闭资源
            catch (SQLException se2)
            {
            }
            try
            {
                if (conn1 != null)
                    conn1.close();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
        System.out.println("Done!");
        return flag;
    }
}
