package Sever_mechine.ShopCarr;

import com.alibaba.fastjson.JSONObject;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopCar {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置
    public static JSONObject see_car(String id) {
        JSONObject json = new JSONObject();
        JSONObject cuo = new JSONObject();
        json.put("flag",0);
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
            System.out.println("Car searching...");

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM shopping_car";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String aid_id = rs.getString(1);
                if (aid_id.equals(id)) {
                    JSONObject json1 = new JSONObject();
                    json1.put("goodsName",rs.getString(2));
                    json1.put("goodsPrice",rs.getFloat(3));
                    json1.put("goodsAmount",rs.getInt(4));
                    json1.put("goodsId",rs.getString(5));
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
            return cuo;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            }// 关闭资源
            catch (SQLException se2) {
                return cuo;
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

    public static int add(String user_id,String na,float price,int amount,int id) throws UnsupportedEncodingException {
        Connection conn = null;
        Statement stmt = null;
        String name = new String(na.getBytes("GBK"));
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("Car searching...");

            String sql;
            sql = "insert into shopping_car(user_id,goods_name,goods_price,goods_amount,id) values(?,?,?,?,?)";
           // ResultSet rs = stmt.executeQuery(sql);
            PreparedStatement preStmt=conn.prepareStatement(sql);
            preStmt.setString(1, user_id);
            preStmt.setString(2, name);
            preStmt.setFloat(3, price);
            preStmt.setInt(4, amount);
            preStmt.setInt(5,id);
            preStmt.executeUpdate();
          //  rs.close();
           // stmt.close();
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
                return 1;
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

    public static int delete(String user,int id) throws UnsupportedEncodingException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动user
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("Deleting...");

            String sql;
            sql = "SELECT * FROM shopping_car";
            stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String aid_user = rs.getString(1);
                int aid_id = rs.getInt(5);
                if (aid_user.equals(user)&&aid_id==id) {
                    System.out.println("find it");
                    rs.updateString(1,"delete");
                    rs.updateRow();
                }
            }
            sql = "DELETE FROM shopping_car where user_id='delete'";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            rs.close();
            stmt.close();
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
                return 1;
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return 1;
            }
        }
        System.out.println("Delete over!");
        return 0;
    }

    public static int deleteAll(String user) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动user
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("Deleting...");

            String sql;
            sql = "SELECT * FROM shopping_car";
            stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String aid_user = rs.getString(1);
                if (aid_user.equals(user)) {
                    System.out.println("find it");
                    rs.updateString(1,"delete");
                    rs.updateRow();
                }
            }
            sql = "DELETE FROM shopping_car where user_id='delete'";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            rs.close();
            stmt.close();
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
            }// 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return 1;
            }
        }
        System.out.println("Delete over!");
        return flag;
    }

}
