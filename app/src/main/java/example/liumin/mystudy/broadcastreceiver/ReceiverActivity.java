package example.liumin.mystudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by Administrator on 2018-10-30.
 */

/**
 * 注意：  1、如果只是静态广播的话，无法设置回调监听，只能通过static的方式进行监听。如果直接set接口的话，会一直set失败，因为对象不是一个
 *
 *         2、静态接收和动态接收可以写在一个receiver中，然后分别进行判断，无论动态是否注册或者解注，不影响静态接收
 *
 *         3、这里只是演示作用，所以设置了静态
 *
 *
 * */

public class ReceiverActivity extends BaseActivity implements MyReceiver.OnMyReceive{

    public Button sendstatic,senddynamic,regdynamic,unregdynamic,sendstick;
    public TextView memo;
    public MyReceiver mr;

    @Override
    public void onmyreceive(String content) {
        addline(content);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        sendstatic = $(R.id.sendstatic);
        senddynamic = $(R.id.senddynamic);
        sendstick = $(R.id.sendstick);
        regdynamic = $(R.id.regdynamic);
        unregdynamic = $(R.id.unregdynamic);
        memo = $(R.id.rec_memo);

        //绑定静态广播,设置回调，方便在主界面中显示信息
        MyReceiver.omr=this;







        sendstatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Constant.STATICBROADCAST);
                sendBroadcast(i);
            }
        });

        regdynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mr = new MyReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(Constant.DYNAMINCBROADCAST);
                ReceiverActivity.this.registerReceiver(mr,intentFilter);
                getToast("reg over");


            }
        });


        senddynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Constant.DYNAMINCBROADCAST);
                sendBroadcast(i);
            }
        });

        unregdynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unregisterReceiver(mr);
                if(mr==null){
                    getToast("mr is null");//注销之后不为null
                }
                getToast("unreg over");
            }
        });


        sendstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendStickyBroadcast();
            }
        });




    }


    public void addline(String line){
        memo.setText(memo.getText()+"\n"+line);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mr);
        } catch (Exception e) {
            e.printStackTrace();
            getToast("unreg error ，may be unreged");
        }
    }
}


