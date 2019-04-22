package example.liumin.mystudy.geasure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import example.liumin.mystudy.R;
import example.liumin.mystudy.base.BaseActivity;

/**
 * Created by USER on 2019/04/22.
 */

public class GeasureActivity extends BaseActivity {
    public FrameLayout container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geasure);

        container = $(R.id.container);

        TestFragment testFragment = null;

        testFragment = (TestFragment) getFragmentManager().findFragmentById(R.id.container);

        if(testFragment == null){
            testFragment = TestFragment.getInstance();
            getFragmentManager().beginTransaction().add(R.id.container,testFragment,"test").addToBackStack("test").commit();
        }

    }
}
