package example.liumin.mystudy.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;

import java.util.ArrayList;
import java.util.Random;

import example.liumin.mystudy.R;

import example.liumin.mystudy.base.BaseActivity;
import example.liumin.mystudy.sqlite.Item;
import example.liumin.mystudy.sqlite.MyAdapter;

/**
 * Created by Administrator on 2018-11-07.
 *      1、用contentprovider和contentresolver提供统一数据库访问使用接口
 *      2、使用观察者模式监听变化
 *
 *
 *
 *
 *
 */



public class ContentProviderActivity extends BaseActivity {

    public Button query,insert,delete,update;
    public RecyclerView rv;
    public ContentResolver resolver;
    public Uri uri_user;
    public ArrayList<Item> list;
    public MyAdapter ma;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider);
        query = $(R.id.cp_sel);
        insert = $(R.id.cp_add);
        delete = $(R.id.cp_delete);
        update = $(R.id.cp_update);
        rv = $(R.id.cp_rv);

        // 获取ContentResolver
          resolver =  getContentResolver();
        // 设置URI
          uri_user = Uri.parse("content://"+MyProvider.LABEL+"/items");
          list = new ArrayList<Item>();

        ma = new MyAdapter(this,list);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(ma);

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 插入表中数据
                ContentValues values = new ContentValues();
                values.put("itemid", new Random().nextInt(100));
                values.put("itemname", "name:"+new Random().nextInt(100));
                // 通过ContentResolver 根据URI 向ContentProvider中插入数据
                resolver.insert(uri_user,values);
                getToast("insert over");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    public void getdata(){
        list.clear();
        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"itemid","itemname"}, null, null, null);
        if(cursor != null){
            while (cursor.moveToNext()){
//                    System.out.println("query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
                // 将表中数据全部输出
                list.add(new Item(cursor.getString(0),cursor.getString(1)));
            }

            cursor.close();
            // 关闭游标
            ma.notifyDataSetChanged();
        }else{
            getToast("cursor is null ");

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cp,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case  R.id.cp_image :

                break;
            case R.menu.te :

                break;
        }
        getToast("hello item");
        return true;
    }
}
