package example.liumin.mystudy.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 *      3、用asynctask并行执行任务，不加synchronized字段，成员变量也不会错乱。怀疑asynctask内部虽然已经设置了并行，但还是依序执行，执行顺序可能会改变。
 *      4、用thread，直接就是并行的方式执行，不加不加synchronized字段，成员变量数值错乱，加上即可解决
 *
 *          Asynctask publishProgress方法，是使用线程通讯进行的，handler方式
 *
 *      5、一个线程有一个handler，对应一个looper，对应一个messagequeue。looper会一直循环读取消息，直到退出
 *
 *      6、TODO   调用interput之后，还是能接收到消息，不知道如何彻底关闭进程
 *             tips：关于终端，没有办法让无用线程立即终断，官方API说明书，说明任何情况下都是允许线程可以不直接中断的。
 *
 *
 *
 *
 */

public class AsyncTaskActivity extends BaseActivity {

    public   ProgressBar pb1,pb2,pb3,pb4,pb5,pb6;
    public Button begin,begin_parallel,thread,sendmessage,stop,isalive;
    public int test=0;                 //用于测试线程同步

    public CommunicationThread ct;
    public Handler mhandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getToast("receive message");


        }
    };




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
        sendmessage = $(R.id.asynctask_sendmessage);
        stop = $(R.id.asynctask_stop);
        isalive = $(R.id.asynctask_isalive);






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

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ct == null){
                    ct  = new CommunicationThread(mhandler);
                    ct .start();
                    getToast("已启动");
                    ct .cthandler.sendEmptyMessage(123);
                }else{
                    ct.cthandler.obtainMessage(456).sendToTarget();
                }






            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ct.stop();
            }
        });

        isalive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToast(ct.isAlive()+"");
            }
        });


    }

    public     void update(Integer... values){

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



    class MyThread extends Thread {
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
