package example.liumin.mystudy.jni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by Administrator on 2018-10-26.
 */

public class JniActivity extends BaseActivity {
    public Button getstring,getformatstring;
    public EditText et;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_jni);

        getstring = $(R.id.getstring);
        getformatstring = $(R.id.getformatstring);
        et = $(R.id.et);

        getstring.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToast(JniUtils.getString());
            }
        });

        getformatstring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp = et.getText().toString().trim();
                if(temp.length() == 10){
                    AlertDialog ad = new AlertDialog.Builder(JniActivity.this).create();

                    ad.setTitle("The text you input is "+temp+"  and the result is "+JniUtils.getFormatString(temp));

                    ad.show();
                }else{
                    getToast("text error");
                }


            }
        });

    }
}
