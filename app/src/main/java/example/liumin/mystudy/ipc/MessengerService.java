package example.liumin.mystudy.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018-10-26.
 */


//这个相当于服务端
/*
*   1、先创建service类，重写onbind方法，通过bindservice后，会一次执行，oncreate，onbind方法，onbind方法会返回binder对象
*   2、在activity中创建serviceconnection，在onServiceConnected方法中，用binder创建messenger，这个messenger即代表的是service中的messenger
*       然后新建message等信息，然后用上边的messen.send方法发送消息
*
*   3、在service中声明hangler，重写handlemessage方法，进行msg.what的判断，然后依序进行处理，如果还需要进行回复的话，则用msg.reply获取来消息的messenger对象。
*       这样即拥有了客户端的messenger对象
*
*   4、客户端再次发送消息的时候，无法用msg.reply获取messenger对象了，获取为空，不知原因
*
*   5、如果需要传输Object,只需要将object进行序列化即可，序列化只是几个固定方法实现即可
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* */

// TODO: 2018-10-27 新建service类，重写onbind方法，


public class MessengerService extends Service {


    public static final String TAG = "MessengerService";
    public static final int MSG_FROMCLIENT=1000;
    public static final int OBJECT_FROMCLIENT=2000;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROMCLIENT:
                    getToast("service receive message-------"+msg.getData().get("msg"));
                    int i =Integer.valueOf( msg.getData().getInt("key"));

//                    IpcActivity.addline("service receive message-------"+msg.getData().get("msg")+"and  receive the key is " + i+ " and i will add 1 on it ");

                    Log.v(TAG,"service receive message-------"+msg.getData().get("msg")+"and  receive the key is " + i+ " and i will add 1 on it ");
                    i++;

                    //得到客户端传来的Messenger对象
                    Messenger mMessenger=msg.replyTo;
                    Message mMessage=Message.obtain(null,MessengerService.MSG_FROMCLIENT);
                    Bundle mBundle=new Bundle();
                    mBundle.putString("rep","this is service ，i receive the message");
//                    IpcActivity.addline("this is service ，i receive the message,now return to  you");
                    Log.v(TAG,"this is service ，i receive the message,now return to  you");
                    getToast("this is service ，i receive the message,now return to  you");
                    mBundle.putInt("key",i);

                    mMessage.setData(mBundle);
                    try {
                        mMessenger.send(mMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

                case OBJECT_FROMCLIENT:
                    Bundle b = msg.getData();
                    b.setClassLoader(getClass().getClassLoader());
                    Object object = b.get("obj");
                    Item item = null;
                    if(object instanceof Item){
                        item = (Item)object;
                    }
//                    IpcActivity.addline("service receive item and itemname is "+item.getName());
                    Log.v(TAG,"service receive item and itemname is "+item.getName());
                    getToast("service receive item and itemname is "+item.getName());
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent,   int flags, int startId) {

        Log.v(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(TAG,"onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Messenger mMessenger=new Messenger(mHandler);
//        IpcActivity.addline("the messenger service onbind");
        getToast("the messenger service onbind ");
        Log.v(TAG,"onBind");
//        IpcActivity.addline("the messenger service onbind ");

        return mMessenger.getBinder();
    }


    public void getToast(String temp){
        Toast.makeText(this,temp,Toast.LENGTH_LONG).show();
    }

}
