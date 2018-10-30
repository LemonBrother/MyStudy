package example.liumin.mystudy.activity;

import android.content.Intent;
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

public class DesActivity extends BaseActivity {
    public TextView memo;
    public Button add;
    String temp;
    int oldi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);
        memo = $(R.id.des_memo);
        add =$(R.id.des_add);


        //判断Intent是否为空
        if(getIntent() != null){
            if(getIntent().getExtras() != null ){
                int type = getIntent().getIntExtra("type",-1);

                if(type ==0 ){
                    //  Transdata
                    temp = getIntent().getExtras().getString("key");
                    Bundle b = getIntent().getExtras();
                    temp = temp + "\n"+b.getString("bs");
                }

                if(type ==1){
                    //TransObject
                    Item item = getIntent().getParcelableExtra("item");
                    temp = item.getName();
                }

                if(type == 2){
                    oldi = getIntent().getIntExtra("oldi",-1);
                    temp = oldi+"";
                    add.setEnabled(true);
                }



            }
        }

        memo.setText(""+temp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", oldi*2);
                //设置返回数据
                DesActivity.this.setResult(TestActivity.RESULTCODE, intent);
                //关闭Activity
                DesActivity.this.finish();
            }
        });
    }
}
