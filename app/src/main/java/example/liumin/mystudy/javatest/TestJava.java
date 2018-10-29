package example.liumin.mystudy.javatest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018-10-29.
 */

public class TestJava {

    public static void main(String args[]){
        System.out.println("helloworld");
        try {
            URL url = new URL("http://192.168.6.81:6278/itemtype");
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp="";
            String line=null;
//            while((line = br.readLine()) !=null){
//
//
//                temp+=line;
//            }


            System.out.println("br.readLine()"+br.readLine());

            //此处必须要这样写，窦泽将丢失一行数据。如果为一行，则后续获取都为空
            while ((line = br.readLine()) != null ){
                System.out.println(line);
                temp+=line;
            }


            br.close();

            System.out.println("temp "+temp);
//                Thread.sleep(20*1000);//会卡死
        } catch (Exception e) {
            e.printStackTrace();
        }







    }
}
