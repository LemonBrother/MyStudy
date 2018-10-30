package example.liumin.mystudy.broadcastreceiver;

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

        //绑定静态广播
        MyReceiver  mmr = new MyReceiver();

        mmr.setOmr(ReceiverActivity.this);
        getToast("setover");

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
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(Constant.DYNAMINCBROADCAST);
                registerReceiver(mr,intentFilter);
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
}
