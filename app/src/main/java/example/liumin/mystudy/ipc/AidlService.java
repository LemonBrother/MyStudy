package example.liumin.mystudy.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import example.liumin.mystudy.ItemManager;

/**
 * Created by Administrator on 2018-10-27.
 */


/**
 *
 *      1、Aidl服务端，创建及绑定可以正常显示Toast，执行线程中的方法时，无法显示Toast，原因未知
 *      2、无法再主进程中访问网络，原因未知.在界面中新开线程，执行此远端方法正常
 *      3、sleep主界面也会卡死
 *
 *      2、主要应用场景未知
 *
 *
 *
 *
 * */

public class AidlService extends Service {

    public static final String TAG="AidlService";
    public ArrayList<Item> itemlist;


    public IBinder binder = new ItemManager.Stub(){

        @Override
        public List<Item> getItemList() throws RemoteException {

            try {
                URL url = new URL("http://192.168.6.81:6278/itemtype");
                URLConnection con = url.openConnection();
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String temp="";
                String line=null;
                while((line = br.readLine()) !=null){

                    temp+=line;
                }
                br.close();
                Log.v(TAG,"temp is "+temp);

//                Thread.sleep(20*1000);//会卡死
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemlist;
        }

        @Override
        public void addItem(Item item) throws RemoteException {
                itemlist.add(item);
//            getToast("add a new Item and name is "+ item.getName());
            Log.v(TAG,"add a new Item and name is "+ item.getName());

        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        getToast("AIDL SERVICE IS onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getToast("AIDL SERVICE IS onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        getToast("AIDL SERVICE IS onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        itemlist = new ArrayList<>();

        getToast("AIDL SERVICE IS STARTING");
        return binder;
    }

    public void getToast(String temp){
        Toast.makeText(this,temp,Toast.LENGTH_LONG).show();
    }
}
