package example.liumin.mystudy.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import example.liumin.mystudy.R;
import example.liumin.mystudy.activity.TestActivity;
import example.liumin.mystudy.base.BaseActivity;
import example.liumin.mystudy.broadcastreceiver.ReceiverActivity;
import example.liumin.mystudy.ipc.IpcActivity;
import example.liumin.mystudy.jni.JniActivity;

/**
 * Created by Administrator on 2018-10-26.
 */

public class MainActivity extends BaseActivity {
    public Button jni,ipc,activity,broadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jni=$(R.id.jni);
        ipc= $(R.id.ipc);
        activity = $(R.id.activity);
        broadcast =$(R.id.broadcast);

        jni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gotoActivity(JniActivity.class);
            }
        });

        ipc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gotoActivity(IpcActivity.class);
            }
        });

        activity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gotoActivity(TestActivity.class);
            }
        });

        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(ReceiverActivity.class);
            }
        });


    }
}
