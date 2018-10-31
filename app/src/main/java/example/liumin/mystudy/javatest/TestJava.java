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
        System.out.println("helloworld"+getres(0,1,2));




    }

    public static boolean getres(int x,int y,int z){
        if(x==0){
            if(y==1){
                if(z==2){
                    return true;
                }
            }
        }
        return false;

    }



}
