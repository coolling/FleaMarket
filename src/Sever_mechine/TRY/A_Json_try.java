package Sever_mechine.TRY;

import Sever_mechine.Messagee.Message;
import com.alibaba.fastjson.JSONObject;

public class A_Json_try {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/javatest?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "clx0725";//基础信息设置

    /*public class one{

    }
    public static void login_check(String id,String na) throws Exception {
        Connection conn1 = null;
        Statement stmt1 = null;
        String name = new String(na.getBytes("GBK"));
        try {
            Class.forName(JDBC_DRIVER);// 注册 JDBC 驱动
            // 打开链接
            conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting Database...");
            System.out.println("User information changing...");

            stmt1 = conn1.createStatement();
            String sql;
            sql = "SELECT * FROM user_info";
            ResultSet rs = stmt1.executeQuery(sql);
            while (rs.next()) {
                String aid_id = rs.getString(1);
                if (aid_id.equals(id)) {
                    rs.updateString(3,"sex");
                    rs.updateString(4,"address");
                    rs.updateString(5,"major");
                    rs.updateString(6,"webname");

                    System.out.println("Change done!");
                }
            }// 完成后关闭
            rs.close();
            stmt1.close();
            conn1.close();

        }// 展开结果集数据库
        catch (SQLException se) {
            se.printStackTrace();// 处理 JDBC 错误
        } catch (Exception e) {
            e.printStackTrace();// 处理 Class.forName 错误
        } finally {
            try {
                if (stmt1 != null)
                    stmt1.close();
            }// 关闭资源
            catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Search Done!");
    }*/
    public static void main(String[] args) throws Exception {
        JSONObject js = new JSONObject();
        js = Message.msg_check("201900301133");
        JSONObject json = new JSONObject();
        json.put("data",js.get("data"));
        json.put("flag",js.getIntValue("flag"));
        System.out.println(json);
    }
}
