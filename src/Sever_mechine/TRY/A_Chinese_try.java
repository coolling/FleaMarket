package Sever_mechine.TRY;
import java.io.UnsupportedEncodingException;
import java.lang.*;

public class A_Chinese_try {
    public static void s_try() throws UnsupportedEncodingException {
        String ss = "男";
        String s2 = new String(ss.getBytes("GBK"));
        System.out.println(ss+"\n"+s2);
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
//        A_Chinese_try.s_try();

    }
}
