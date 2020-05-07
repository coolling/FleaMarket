package Sever_mechine.Comment;

import com.alibaba.fastjson.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Comments {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static int add(int id, String maker, String comment,String makerWebname,String goodUser) throws Exception
    {
        Connection conn1 = null;
        Statement stmt1 = null;
        //       String comment = new String(com.getBytes("GBK"));

        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("Comments adding...");
            String sql="insert into goods_comments(goods_id, owner, maker,makerWebname,comments) values(?,?,?,?,?)";//会抛出异常
            PreparedStatement preStmt=conn1.prepareStatement(sql);
            preStmt.setInt(1, id);
            preStmt.setString(2, goodUser);
            preStmt.setString(3, maker);
            preStmt.setString(4,makerWebname);
            preStmt.setString(5, comment);
            preStmt.executeUpdate();
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

    public static JSONObject see (int id) {
        Connection conn1 = null;
        Statement stmt1 = null;
        List data = new ArrayList();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        JSONObject json1 = new JSONObject();
        json1.put("flag",1);
        PreparedStatement ps = null;
        int i=0;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("Comment finding...");
            String sql;
            sql = "SELECT * FROM goods_comments";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();//会抛出异常
            while (rs.next()) {
                int aid_id = rs.getInt(1);
                if (aid_id==id) {
                    i++;
                    JSONObject json = new JSONObject();
                    json.put("makerId",rs.getString(3));
                    json.put("makerWebname",rs.getString(4));
                    json.put("comments",rs.getString(5));
                    data.add(json);
                }
            }// 完成后关闭
            ps.close();
            json1.put("data",data);
            json1.put("length",i);
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
        return json1;
    }
}

