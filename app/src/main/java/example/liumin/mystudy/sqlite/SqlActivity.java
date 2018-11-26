package example.liumin.mystudy.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by Administrator on 2018-10-31.
 */

public class SqlActivity extends BaseActivity {

    public Button add,del,update,select;
    public EditText et;
    public RecyclerView rv;
    public ArrayList<Item> listdata;
    public MyAdapter ma;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

         add = $(R.id.sql_add);
        del = $(R.id.sql_delete);
        update = $(R.id.sql_update);
        select = $(R.id.sql_sel);
        et = $(R.id.sql_et);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getdata();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maxid=0;
                Cursor c = MyDb.getInstance(SqlActivity.this).getReadableDatabase().rawQuery("select max(itemid) from items",null);
                if (c.moveToFirst()){
                    c.moveToFirst();
                    maxid = Integer.valueOf(c.getString(0));
                }
                c.close();
                if(maxid==0){
                    maxid = 10001;
                }
                MyDb.getSD(SqlActivity.this).execSQL("insert into items(itemid,itemname) values ('"+Integer.valueOf(maxid+1)+"','"+ et.getText().toString().trim()  +"'" + ")");
                getToast("insert over");
                getdata();

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进行判断防止不输入内容全部删除
                if(!TextUtils.isEmpty(et.getText().toString().trim())){
                    MyDb.getSD(SqlActivity.this).execSQL("delete from items where itemname like '%"+et.getText().toString().trim()+"%'");
                    getToast("delete  over");
                    getdata();
                }else{
                    getToast("intput first");
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyDb.getSD(SqlActivity.this).execSQL("update items set itemname = '"+"' where itemname = "+"'"++"'");
//                getToast("delete  over");
//                getdata();
            }
        });



        listdata = new ArrayList<>();
        rv = $(R.id.sql_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //创建直接绑定。后边通过notify进行刷新
        ma = new MyAdapter(this,listdata);
        rv.setAdapter(ma);




    }

    @Override
    protected void onResume() {
        super.onResume();
//        getdata();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getdata(){
        Item item = null;
        listdata.clear();
        Cursor cursor=  MyDb.getInstance(SqlActivity.this).getReadableDatabase().rawQuery("select * from items",null);
        while(cursor.moveToNext()){
              item = new Item(new Item.Builder().itemid(cursor.getString(0)).name(cursor.getString(1)));
            listdata.add(item);
        }
        cursor.close();
        ma.notifyDataSetChanged();
    }


}
