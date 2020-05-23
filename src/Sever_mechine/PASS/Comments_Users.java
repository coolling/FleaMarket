package Sever_mechine.PASS;

import java.sql.*;

public class Comments_Users {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static String getWebname(String id){
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        String webname = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("User searching...");
            String sql;
            sql = "SELECT * FROM user_info";

            ps = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid_id = rs.getString(1);
                if (aid_id.equals(id)) {
                    webname = rs.getString(6);
                }
            }// 完成后关闭
            rs.close();
            ps.close();
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
            }// 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Search Done!");
        return webname;
    }

    public static String getGoodOwner(int id){
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        String goodOwner = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("User searching...");
            String sql;
            sql = "SELECT * FROM goods_info";

            ps = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int aid_id = rs.getInt(7);
                if (aid_id == id) {
                    goodOwner = rs.getString(3);
                }
            }// 完成后关闭
            rs.close();
            ps.close();
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
            }// 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Search Done!");
        return goodOwner;
    }
}
