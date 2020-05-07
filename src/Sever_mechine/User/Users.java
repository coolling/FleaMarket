package Sever_mechine.User;

import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class Users {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static int login_check(String id, String pass) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        int flag = 1;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("User searching...");
            String sql;
            sql = "SELECT * FROM user_info";

            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid_id = rs.getString(1);
                String aid_pass = rs.getString(2);
                if ((aid_id.equals(id))&&(aid_pass.equals(pass))) {
                    System.out.println("Search Done!");
                    flag = 0;
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
        return flag;
    }

    public static int register(String id, String pass) throws Exception
    {
        Connection conn1 = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("User registering...");

            String check = "select * from user_info";
            PreparedStatement pst = conn1.prepareStatement(check);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                System.out.println("checking...");
                String aid_id = rs.getString(1);
                if (aid_id.equals(id)){
                    return 1;
                }
            }

            String sql="insert into user_info(User_id,User_pass,User_sex,User_address,User_major,User_webname,User_picture,trueName,grade,tel) values(?,?,?,?,?,?,?,?,?,?)";//会抛出异常
            PreparedStatement preStmt=conn1.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.setString(2, pass);
            preStmt.setString(3, "null");
            preStmt.setString(4, "null");
            preStmt.setString(5, "nill");
            preStmt.setString(6, "null");
            preStmt.setString(7,"null");
            preStmt.setString(8,"null");
            preStmt.setInt(9,0);
            preStmt.setString(10,"null");
            preStmt.executeUpdate();
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
        System.out.println("Register Done!");
        return 0;
    }

    public static int info_change(String id, String sex, String add, String maj, String webname, String truename, String tel, int grade) throws Exception
    {
        Connection conn1 = null;
        Statement stmt1 = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("User information changing...");


            String sql;
            sql = "SELECT * FROM user_info";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid_id = rs.getString(1);
                if (aid_id.equals(id)){
                    rs.updateString(3,sex);
                    rs.updateString(4,add);
                    rs.updateString(5,maj);
                    rs.updateString(6,webname);
                    rs.updateString(8,truename);
                    rs.updateInt(9,grade);
                    rs.updateString(10,tel);
                    rs.updateRow();
                }
            }
            rs.close();
            ps.close();
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
        return 0;
    }

    public static int pass_change(String id, String oldpass, String newpass) {
        Connection conn1 = null;
        Statement stmt1 = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");

            String sql;
            sql = "SELECT * FROM user_info";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String aid_id = rs.getString(1);
                String aid_pass = rs.getString(2);
                if (aid_id.equals(id)) {
                    System.out.println("Safe checking...");
                    if (aid_pass.equals(oldpass)){
                        rs.updateString(2,newpass);
                        rs.updateRow();
                    }
                    System.out.println("Change done!");
                }
            }// 完成后关闭
            rs.close();
            ps.close();
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
        System.out.println("Change Done!");
        return 0;
    }

    public static int picture_change(String id, String data) throws Exception
    {
        Connection conn1 = null;
        Statement stmt1 = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("User picture changing...");
            String sql;
            sql = "SELECT * FROM user_info";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid_id = rs.getString(1);
                if (aid_id.equals(id)) {
                    rs.updateString(7,data);
                    rs.updateRow();
                    System.out.println("Change done!");
                }
            }// 完成后关闭
            rs.close();
            ps.close();
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
        return 0;
    }

    public static JSONObject infoGet(String user) {
        Connection conn1 = null;
        Statement stmt1 = null;
        PreparedStatement ps = null;
        JSONObject json = new JSONObject();
        JSONObject cuo = new JSONObject();
        cuo.put("flag",1);
        json.put("flag",0);

        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("finding...");
            String sql;
            sql = "SELECT * FROM user_info";
            ps = conn1.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();//会抛出异常
            while (rs.next()) {
                String aid_user = rs.getString(1);
                if (aid_user.equals(user)) {
                    json.put("userSex",rs.getString(3));
                    json.put("userAddress",rs.getString(4));
                    json.put("userMajor",rs.getString(5));
                    json.put("userWebname",rs.getString(6));
                    json.put("userPicture",rs.getString(7));
                    json.put("trueName",rs.getString(8));
                    json.put("userGrade",rs.getInt(9));
                    json.put("userTel",rs.getString(10));
                }
            }// 完成后关闭
            ps.close();
            rs.close();
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
}
