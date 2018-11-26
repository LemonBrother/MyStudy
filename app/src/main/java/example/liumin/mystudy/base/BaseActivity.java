package example.liumin.mystudy.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import example.liumin.mystudy.R;
import example.liumin.mystudy.util.CommonTools;

/**
 * Created by Administrator on 2018-10-26.
 */

public class BaseActivity extends AppCompatActivity {
    protected   final String TAG = this.getClass().getSimpleName();
    public String USETEXT="base";
    public String USEPICPATH ="";

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.base_menu_image:
                showImage("使用图解",USEPICPATH);
                break;
            case R.id.base_menu_text:

                showText("使用流程",this.USETEXT);
                break;

        }



        return true;
    }

    public void showImage(String title, String path){
        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setTitle(title);
        View v = View.inflate(this,R.layout.baseactivity_imagedialog,null);

        ImageView iv = (ImageView) v.findViewById(R.id.imagedialog_iv);

        iv.setImageBitmap(CommonTools.getImageFromAssetsFile(this, path));
        builder.setView(v);


        AlertDialog ad = builder.create();
        ad.show();

    }

    public void showText(String title,String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setTitle(title);
        View v = View.inflate(this,R.layout.baseactivity_textdialog,null);

        TextView tv = (TextView) v.findViewById(R.id.textdialog_tv);

        tv.setText(content);
        builder.setView(v);


        AlertDialog ad = builder.create();
        ad.show();

    }

}
