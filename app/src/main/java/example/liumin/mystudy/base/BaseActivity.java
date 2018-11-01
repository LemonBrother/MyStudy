package example.liumin.mystudy.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2018-10-26.
 */

public class BaseActivity extends AppCompatActivity {
    protected   final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void getToast(String temp){
        Toast.makeText(this,temp,Toast.LENGTH_SHORT).show();
    }

    protected <T extends View> T $(int viewid){
        return  (T)super.findViewById(viewid);
    }

    public void gotoActivity(Class<?> cls){
        Intent i = new Intent(this,cls);
        startActivity(i);
    }

}
