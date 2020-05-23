package Sever_mechine.PASS;

import Sever_mechine.ShopCarr.ShopCar;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class ShopCar_Ts {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FleaMarket?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "901190Aa";//基础信息设置

    public static int mission(String user_id) throws UnsupportedEncodingException {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        Statement stmt = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("Connecting Database...");
            System.out.println("Car searching...");

            String sql;
            sql = "SELECT * FROM shopping_car";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String aid_id = rs.getString(1);
                if (aid_id.equals(user_id)){
                    String sql1 = "insert into t_record(buyer,seller,goods,amount,state,goodid) values(?,?,?,?,?,?)";
                    PreparedStatement preStmt=conn.prepareStatement(sql1);
                    preStmt.setString(1, user_id);
                    String sql2 = "select * from goods_info";
                    ps1 = conn.prepareStatement(sql2,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs1 = ps.executeQuery();
                    while (rs1.next()){
                        int goodid = rs1.getInt(7);
                        if (rs.getInt(5)==goodid){
                            preStmt.setString(2,rs1.getString(3));
                        }
                    }
                    preStmt.setString(3, rs.getString(2));
                    preStmt.setInt(4, rs.getInt(4));
                    preStmt.setInt(5, 1);
                    preStmt.setInt(6,rs.getInt(5));
                    preStmt.executeUpdate();
                    preStmt.close();
                    rs1.close();
                    ps1.close();
                }
            }
            ShopCar.deleteAll(user_id);
            rs.close();
            stmt1.close();
            stmt.close();
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
