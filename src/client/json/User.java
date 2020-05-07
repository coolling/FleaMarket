package client.json;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String id;
    public String password;

    public User(String id, String password) {
        JSONObject user = new JSONObject();
        //string
        JSONObject[] data = new JSONObject[2];
        user.put("data", data);
        JSONObject user1 = new JSONObject();
        user1.put("password", password);
        //int
        user1.put("id", id);
        JSONObject user2 = new JSONObject();
        user2.put("password", password);
        //int
        user2.put("id", id);
        data[0] = user1;
        data[1] = user2;

        System.out.println(user);

        System.out.println(user.getJSONArray("data"));
        List a = user.getJSONArray("data");
        System.out.println(a.size());
        System.out.println();

    }

    public static void main(String[] args) {
        User user = new User("!11", "11");
    }
}
