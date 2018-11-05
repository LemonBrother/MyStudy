package example.liumin.mystudy.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by Administrator on 2018-11-05.
 *      1、测试使用同步线程池，最多同时执行4个任务
 *      2、两个线程同时更新一个progressbar时，会出现错乱。家关键字无效，猜测可能和更新进度条有关
 *
 *
 *
 */

public class AsyncTaskActivity extends BaseActivity {

    public   ProgressBar pb1,pb2,pb3,pb4,pb5,pb6;
    public Button begin,begin_parallel,thread;
    public int test=0;                 //用于测试线程同步


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        begin =$(R.id.asynctask_begin);
        begin_parallel=$(R.id.asynctask_begin_parallel);
        pb1 =$(R.id.asynctask_processbar1);
        pb2 =$(R.id.asynctask_processbar2);
        pb3 =$(R.id.asynctask_processbar3);
        pb4 =$(R.id.asynctask_processbar4);
        pb5 =$(R.id.asynctask_processbar5);
        pb6 =$(R.id.asynctask_processbar6);
        thread = $(R.id.thread_begin);

        begin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for(int i = 1;i<5;i++){
                    MyasyncTask mt = new MyasyncTask();
                    mt.execute(i);
                }
                getToast("已开始全部任务");

            }
        });
        begin_parallel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 1;i<7;i++){
                    MyasyncTask mt = new MyasyncTask();
                    mt.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,i) ;
                }
                getToast("已开始全部任务");


            }
        });

        thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 1;i<7;i++){
                    new MyThread(i).start();
                }
                getToast("已开始全部任务");
            }
        });


    }

    public   void update(Integer... values){

        test++;
        Log.v(TAG,"线程"+values[0]+": "+test);
        switch (values[0]){
            case 1:
                pb1.setProgress(values[1]);
                break;
            case 2:
                pb2.setProgress(values[1]);
                break;
            case 3:
                pb3.setProgress(values[1]);
                break;
            case 4:
                pb4.setProgress(values[1]);
                break;
            case 5:
                pb5.setProgress(values[1]);
                break;
            case 6:
                pb6.setProgress(values[1]);
                break;

        }
    }


    class MyasyncTask extends AsyncTask<Integer,Integer,Void>{

        public MyasyncTask() {
            super();
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


            update(values);






        }



        @Override
        protected Void doInBackground(Integer... integers) {


                for(int a = 1;a<11;a++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(integers[0],a*10);

                }

            return null;
        }
    }



    class MyThread extends Thread{
        public int aa;

        public MyThread(int aa){
            this.aa = aa;
        }

        @Override
        public void run() {
            super.run();
            for(int a = 1;a<11;a++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                update(aa,a*10);


            }



        }
    }



}
