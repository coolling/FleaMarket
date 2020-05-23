package Sever_mechine.Good;

import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Goods {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?spring.datasource.druid.test-while-idle=true&spring.datasource.druid.test-on-borrow=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置
    public static int add(String n, float price, String user, String picture, int amount,String info) throws Exception
    {
        Connection conn1 = null;
        int max = 0;
        Statement stmt1 = null;
        String name = new String(n.getBytes("GBK"));
        String information = new String(info.getBytes("GBK"));

        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("Good adding...");
            String sql="insert into goods_info(goods_name,goods_price,goods_user,goods_picture,goods_amount,goods_infomation,goods_id) values(?,?,?,?,?,?,?)";//会抛出异常
            PreparedStatement preStmt=conn1.prepareStatement(sql);
            preStmt.setString(1, name);
            preStmt.setFloat(2, price);
            preStmt.setString(3, user);
            preStmt.setString(4, picture);
            String sql1;
            sql1 = "SELECT * FROM goods_info";
            PreparedStatement ps = conn1.prepareStatement(sql1,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                if (rs.getInt(7)>=max) max = rs.getInt(7);
            }
            preStmt.setInt(7,max+1);
            preStmt.setInt(5, amount);
            preStmt.setString(6,information);
            preStmt.executeUpdate();
            rs.close();
            preStmt.close();
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
            }// 什么都不做
            try
            {
                if (conn1 != null)
                    conn1.close();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                return 1;
            }
        }
        System.out.println("Add Done!");
        return 0;
    }

    public static JSONObject infoGet(int id) throws Exception
    {
        Connection conn1 = null;
        Statement stmt1 = null;
        PreparedStatement ps = null;
        JSONObject json = new JSONObject();
        JSONObject cuo =new JSONObject();
        json.put("flag",0);
        cuo.put("flag",1);
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("Good finding...");
            String sql;
            String userId = "null";
            sql = "SELECT * FROM goods_info";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();//会抛出异常
            while (rs.next()) {
                int aid_id = rs.getInt(7);
                String aid_user = rs.getString(3);
                if (aid_id==id) {
                    userId = rs.getString(3);
                    json.put("goodsPrice",rs.getFloat(2));
                    json.put("goodsPicture",rs.getString(4));
                    json.put("goodsInfo",rs.getString(6));
                    json.put("goodsAmount",rs.getInt(5));
                    json.put("goodsId",rs.getInt(7));
                    json.put("goodsUser",rs.getString(3));
                    System.out.println("Send done!");
                }
            }
            sql = "SELECT * FROM user_info";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();//会抛出异常
            while (rs.next()){
                if (rs.getString(1).equals(userId)) {
                    json.put("userPicture",rs.getString(7));
                    break;
                }
            }
            rs.close();
            ps.close();
            conn1.close();
        }// 展开结果集数据库
        catch (SQLException se)
        {
            se.printStackTrace();// 处理 JDBC 错误
            return cuo;
        }
        catch (Exception e)
        {
            e.printStackTrace();// 处理 Class.forName 错误
            return cuo;
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
            }// 什么都不做
            try
            {
                if (conn1 != null)
                    conn1.close();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                return cuo;
            }
        }
        System.out.println("Mission Done!");
        return json;
    }

    public static int off(String user, int id) throws Exception
    {
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
            sql = "SELECT * FROM goods_info";
            stmt = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String aid_user = rs.getString(3);
                int aid_id = rs.getInt(7);
                if (aid_user.equals(user)&&aid_id==id) {
                    System.out.println("find it");
                    rs.updateString(3,"delete");
                    rs.updateRow();
                }
            }
            sql = "DELETE FROM goods_info where goods_user='delete'";
            PreparedStatement pstmt=conn1.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
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
                return 1;
            }
        }
        System.out.println("Add Done!");
        return 0;
    }

    public static JSONObject get() {
        JSONObject json = new JSONObject();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        json.put("flag",0);
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
            sql = "SELECT * FROM goods_info";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()&&i<12) {
                JSONObject json1 = new JSONObject();
                json1.put("goodsName",rs.getString(1));
                json1.put("goodsPrice",rs.getFloat(2));
                json1.put("goodsPicture",rs.getString(4));
                json1.put("goodsId",rs.getString(7));
                data.add(json1);
                i++;
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
            return cuo;
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
                return cuo;
            }
        }
        return json;
    }

    public static JSONObject see(String user) {
        JSONObject json = new JSONObject();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        json.put("flag",0);
        List data = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
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
            sql = "SELECT * FROM goods_info";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid_id = rs.getString(3);
                if (aid_id.equals(user)){
                    JSONObject json1 = new JSONObject();
                    json1.put("goodsName",rs.getString(1));
                    json1.put("goodsPrice",rs.getFloat(2));
                    json1.put("goodsAmount",rs.getInt(5));
                    json1.put("goodsId",rs.getInt(7));
                    json1.put("goodsUser",rs.getString(3));
                    data.add(json1);
                    i++;
                }
            }// 完成后关闭
            json.put("data",data);
            json.put("length",i);
            ps.close();
            rs.close();
            stmt.close();
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
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return cuo;
            }
        }
        return json;
    }

    public static JSONObject search (String key){
        JSONObject json = new JSONObject();
        json.put("flag",1);
        JSONObject cuo = new JSONObject();
        cuo.put("flag",0);
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
            sql = "SELECT * FROM goods_info WHERE goods_name LIKE '%"+key+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                JSONObject json1 = new JSONObject();
                json1.put("goodsName",rs.getString(1));
                json1.put("goodsPrice",rs.getFloat(2));
                json1.put("goodsPicture",rs.getString(4));
                json1.put("goodsId",rs.getString(7));
                data.add(json1);
                i++;
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
}