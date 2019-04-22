package example.liumin.mystudy.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import example.liumin.mystudy.R;
import example.liumin.mystudy.activity.TestActivity;
import example.liumin.mystudy.contentprovider.ContentProviderActivity;
import example.liumin.mystudy.geasure.GeasureActivity;
import example.liumin.mystudy.thread.AsyncTaskActivity;
import example.liumin.mystudy.broadcastreceiver.ReceiverActivity;
import example.liumin.mystudy.fragment.MyFragmentActivity;
import example.liumin.mystudy.ipc.IpcActivity;
import example.liumin.mystudy.jni.JniActivity;
import example.liumin.mystudy.sqlite.SqlActivity;

/**
 * Created by Administrator on 2018-10-26.
 */

public class MainActivity extends BaseActivity {
    public Button jni,ipc,activity,broadcast,sqlite,fragment,asynctask,cp,geasure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jni=$(R.id.jni);
        ipc= $(R.id.ipc);
        activity = $(R.id.activity);
        broadcast =$(R.id.broadcast);
        sqlite =$(R.id.sqlite);
        fragment = $(R.id.fragment);
        asynctask = $(R.id.asynctask);
        cp =$(R.id.contentprovider);
        geasure = $(R.id.geasure);

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

        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(SqlActivity.class);
            }
        });

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(MyFragmentActivity.class);
            }
        });

        asynctask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(AsyncTaskActivity.class);
            }
        });

        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ContentProviderActivity.class);
            }
        });

        geasure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gotoActivity(GeasureActivity.class);
            }
        });


    }
}
