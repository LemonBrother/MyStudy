package example.liumin.mystudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by Administrator on 2018-10-30.
 *
 *
 *
 *      2、add  layout-land,layout-port folder to layout,will use when screen turn arround.syste, will auto use the layout
 *
 *
 *
 */

public class TestActivity extends BaseActivity {
    public static final String TAG="TestActivity";




    public static final int REQUESTCODE=1000;
    public static final int RESULTCODE = 2000;
    public TextView memo;
    public Button transdata,transobject,transadd;
    public EditText et_data,et_object,et_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //add useinfo in here.used be in activity showuseinfo
        super.USETEXT = "TEST";
        super.USEPICPATH="";    // pic name in ASSETS  folder




        setContentView(R.layout.activity_test);
        memo = $(R.id.memo);
        transdata = $(R.id.transdata);
        transobject = $(R.id.transobject);
        transadd = $(R.id.transadd);
        et_data =$(R.id.et_transdata);
        et_object =$(R.id.et_transobject);
        et_add =$(R.id.et_transadd);

        getToast("onCreate");

        transdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //two way in transdata

                //bundle likes hashmap，can save many key --  values
                Intent i = new Intent(TestActivity.this,DesActivity.class);
                i.putExtra("type",0);
                i.putExtra("key",et_data.getText().toString().trim()+"from intent");
                Bundle bundle = new Bundle();
                bundle.putString("bs",et_data.getText().toString().trim()+"from bundle");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        transobject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TestActivity.this,DesActivity.class);
                i.putExtra("type",1);
                i.putExtra("item",new Item(et_object.getText().toString().trim()+" from object"));
                startActivity(i);
            }
        });

        transadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(et_add.getText().toString().trim())){
                    Intent i = new Intent(TestActivity.this,DesActivity.class);
                    i.putExtra("type",2);
                    i.putExtra("oldi",Integer.valueOf(et_add.getText().toString().trim()));
                    startActivityForResult(i,REQUESTCODE);
                }else{
                    getToast("please input num");
                }

            }
        });

        transadd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                memo.setText("");
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getToast("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        getToast("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getToast("onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getToast("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        getToast("onPause");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUESTCODE && resultCode == RESULTCODE){
            addlin(data.getIntExtra("result",-1)+"");
        }
    }

    @Override
    protected <T extends View> T $(int viewid) {
        return super.$(viewid);
    }


    @Override
    public void getToast(String temp) {
        super.getToast(TAG+"   "+temp);
        addlin(temp);
    }

    public void addlin(String line){
        memo.setText(memo.getText()+"\n"+TAG+"   "+line);
    }

}
