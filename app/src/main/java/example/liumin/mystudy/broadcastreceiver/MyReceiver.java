package example.liumin.mystudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2018-10-30.
 */

public class MyReceiver extends BroadcastReceiver {
    public OnMyReceive omr;



    public void setOmr(OnMyReceive omr) {
        this.omr = omr;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent !=null){
            if(intent.getAction()!=null){
                switch (intent.getAction().toString()){
                    case Constant.STATICBROADCAST:
                        if(omr == null){
                            Toast.makeText(context,"omr is null",Toast.LENGTH_SHORT).show();
                        }else{
                            omr.onmyreceive(Constant.STATICBROADCAST);
                        }

                        break;
                    case Constant.DYNAMINCBROADCAST:
                        break;
                }

            }

        }
    }

    public interface OnMyReceive{
        void onmyreceive(String content);
    }
}
