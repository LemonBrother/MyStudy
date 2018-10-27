package example.liumin.mystudy.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import example.liumin.mystudy.ItemManager;
import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by Administrator on 2018-10-26.
 */


// TODO: 2018-10-27 注意：使用messenger也必须要序列化对象

public class IpcActivity extends BaseActivity {
    public Button messenger,unbind,aidl,unaidl;
    public  TextView tv;
    //以下是messenger用到的成员变量
    private Messenger mMessenger;//实际上这是service中的messenger对象
    public boolean isbind;


    //以下是AIDL用到的成员变量
    public ItemManager aidlitemmanager;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MessengerService.MSG_FROMCLIENT:

                    int i = msg.getData().getInt("key");
//                    ("client receive message -------" + msg.getData().get("rep")+"and key is "+i);
                    getToast("client receive message -------" + msg.getData().get("rep")+"and key is "+i);
                    if (i == 1){
                        //继续给service发消息，发送的是对象



                        //得到服务端传来的Messenger对象

                        Message mme=Message.obtain(null,MessengerService.OBJECT_FROMCLIENT);
                        Bundle bundle = new Bundle();
                        Item item = new Item("lemonbro");
                        bundle.putParcelable("obj",item);

                        mme.setData(bundle);
//                        IpcActivity.addline("this is client ，i will send item to service");
                        getToast("this is client ，i will send item to service");



                        try {
                            mMessenger.send(mme);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }



                    }
                    break;
            }
        }
    };




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_ipc);
        messenger = $(R.id.ipc_messenger);
        unbind = $(R.id.ipc_unmessenger);
        aidl = $(R.id.ipc_aidl);
        unaidl = $(R.id.ipc_unaidl);
        tv =$(R.id.ipc_tv);

        messenger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //启动带有单独进程的Service
                Intent intent=new Intent(IpcActivity.this,MessengerService.class);
                isbind = bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                unbindService(mServiceConnection);
            }
        });

        aidl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(getApplicationContext(), AidlService.class);
                bindService(intent1, AidlConnection, BIND_AUTO_CREATE);
            }
        });






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mServiceConnection!=null  || isbind){
            unbindService(mServiceConnection);
        }


        if(tv != null ){
            tv = null;
        }

    }

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //这个应该是获取的服务端的messenger对象
            mMessenger=new Messenger(service);
            Message mMessage= Message.obtain(null,MessengerService.MSG_FROMCLIENT);
            Bundle mBundle=new Bundle();
            mBundle.putString("msg", "this is client，do you copy ?service?the key is 0");
            mBundle.putInt("key",0);
            mMessage.setData(mBundle);
            //将Messenger传递给服务端
            mMessage.replyTo=new Messenger(mHandler);
            try {
                mMessenger.send(mMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    private ServiceConnection AidlConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            aidlitemmanager = ItemManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            aidlitemmanager = null;
        }
    };

    @Override
    public void getToast(String temp) {
        Toast.makeText(this,temp,Toast.LENGTH_LONG).show();

    }
}
