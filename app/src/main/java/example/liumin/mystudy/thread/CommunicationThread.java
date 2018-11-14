package example.liumin.mystudy.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by Administrator on 2018-11-06.
 */

public class CommunicationThread extends Thread {
    public    Handler cthandler ;
    public Handler desHandler;

    public CommunicationThread(Handler desHandler){
        this.desHandler = desHandler;
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        cthandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.v("CommunicationThread","CommunicationThread"+msg.what);        //接收一条消息打印一条记录
                if(msg.what == 456){
                    interrupt();
                }

            }
        };


        //Handler不带參数的默认构造函数：new Handler()。实际上是通过Looper.myLooper()来获取当前线程中的消息循环,
//        Looper l =Looper.myLooper();
//        Handler h = new Handler(l);
        //以上两句不知道和直接newhandler有什么区别

        Log.v("CommunicationThread","CommunicationThread+++++++++");        //接收一条消息打印一条记录

        Message m = new Message();
        m.what = 0;
        desHandler.sendMessage(m);
//        desHandler.obtainMessage(0).sendToTarget();//网友建议用这个方法

        Looper.loop();

    }







}
