package example.liumin.mystudy.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import example.liumin.mystudy.ItemManager;

/**
 * Created by Administrator on 2018-10-27.
 */

public class AidlService extends Service {

    public ArrayList<Item> itemlist;


    public IBinder binder = new ItemManager.Stub(){

        @Override
        public List<Item> getItemList() throws RemoteException {

            return itemlist;
        }

        @Override
        public void addItem(Item item) throws RemoteException {
                itemlist.add(item);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
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
        return binder;
    }
}
