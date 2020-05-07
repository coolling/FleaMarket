package Sever_mechine.PASS;

import Sever_mechine.ShopCarr.ShopCar;

import java.io.UnsupportedEncodingException;
import java.sql.*;

public class Good_Ts {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static int mission(String user_id, int goodId, int amount) throws UnsupportedEncodingException {
        Connection conn = null;
        PreparedStatement ps = null;
        Statement stmt = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            String sql;
            sql = "SELECT * FROM goods_info";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(7);
                if (id == goodId){
                    String sql1 = "INSERT INTO t_record(buyer,seller,goods,amount,state,goodid) values(?,?,?,?,?,?)";
                    PreparedStatement preStmt=conn.prepareStatement(sql1);
                    preStmt.setString(1,user_id);
                    preStmt.setString(2,rs.getString(3));
                    preStmt.setString(3,rs.getString(1));
                    preStmt.setInt(4,amount);
                    preStmt.setInt(5,1);
                    preStmt.setInt(6,goodId);
                    ShopCar.delete(user_id,goodId);
                    preStmt.executeUpdate();
                }
            }
            rs.close();
//            stmt1.close();
           // stmt.close();
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
        return 0;
    }
}