package example.liumin.mystudy.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018-11-01.
 *
 *      1、静态添加fragment，在布局文件中标签直接用fragment，android：name=对应的fragment类就行了
 *
 *      2、带标签页的fragment直接用FragmentTabHost，方便快捷，但是不能左右滑动
 *
 *
 *
 *
 */

public class MyFragmentActivity extends BaseActivity {

    public Button add,replace,remove;
    public LinearLayout left,right;
    public FragmentManager fm;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        add=$(R.id.fragment_add);
        replace=$(R.id.fragment_replace);
        remove=$(R.id.fragment_remove);
        left = $(R.id.fragment_left);
        right = $(R.id.fragment_right);

        fm = getSupportFragmentManager();

        //直接把第一个fragment添加进去

        fm.beginTransaction().add(R.id.fragment_left,new StaticFragment()).commit();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //之前重复点击，会重复进行添加。进行判断，防止重复添加
                Fragment dynamic = fm.findFragmentById(R.id.fragment_right);
                if(dynamic == null ){
                    fm.beginTransaction().add(R.id.fragment_right,new DynamicFragment()).commit();
                    getToast("add over");
                }else{
                    getToast("added");
                }


            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment dynamic = fm.findFragmentById(R.id.fragment_right);

                if(dynamic !=null){
                    fm.beginTransaction().replace(R.id.fragment_right,new StaticFragment()).commit();
                    getToast("replace over");

                }else{
                    getToast("right is null,please add");
                }



            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment dynamic = fm.findFragmentById(R.id.fragment_right);

                if(dynamic !=null){
                    fm.beginTransaction().remove(dynamic).commit();
                    getToast("remove over");

                }else{
                    getToast("right is null,please add");
                }
            }
        });






        //用FragmentTabHost 实现页，但无法滑动
        FragmentTabHost mTabhost = (FragmentTabHost) this.findViewById(R.id.myid);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.fragment_ll);
        mTabhost.addTab(mTabhost.newTabSpec("M").setIndicator("M"),StaticFragment.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("D").setIndicator("D"),DynamicFragment.class, null);


        //用viewpager实现页
        MyAdapter ma = new MyAdapter(getSupportFragmentManager() ,new String[]{"M","D"} );
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(ma);
        TabLayout tabLayout  = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);




    }



    class MyAdapter extends FragmentPagerAdapter{
        public String[] titles;

        public MyAdapter(FragmentManager fm,String[] titles) {
            super(fm);
            this.titles=titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new StaticFragment();

                case 1:

                    return new DynamicFragment();
            }
            return null;
        }





    }



}
