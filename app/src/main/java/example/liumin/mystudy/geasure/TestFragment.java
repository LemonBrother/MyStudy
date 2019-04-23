package example.liumin.mystudy.geasure;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import example.liumin.mystudy.R;

/**
 * Created by USER on 2019/04/22.
 */

public class TestFragment extends Fragment {
    public GestureDetector mGestureDetector;
    public ScaleGestureDetector mScaleGestureDetector;
    public static TestFragment mInstance;

    public static TestFragment getInstance(){
        if(mInstance ==null){
            synchronized (TestFragment.class){
                if(mInstance == null){
                    mInstance = new TestFragment();
                }
            }
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geasure,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGetsure();
        initScale();
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean b = event.getPointerCount() == 1?mGestureDetector.onTouchEvent(event): mScaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        Toast.makeText(getActivity(),"onViewCreated",Toast.LENGTH_SHORT).show();

    }

    public void initGetsure(){
        mGestureDetector = new GestureDetector(getActivity()     ,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                Toast.makeText(getActivity(),"onSingleTapUp",Toast.LENGTH_SHORT).show();
                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Toast.makeText(getActivity(),"onLongPress",Toast.LENGTH_SHORT).show();
                super.onLongPress(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Toast.makeText(getActivity(),"onScroll",Toast.LENGTH_SHORT).show();
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Toast.makeText(getActivity(),"onFling",Toast.LENGTH_SHORT).show();
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Toast.makeText(getActivity(),"onShowPress",Toast.LENGTH_SHORT).show();
                super.onShowPress(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                Toast.makeText(getActivity(),"onDown",Toast.LENGTH_SHORT).show();
                return super.onDown(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getActivity(),"onDoubleTap",Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Toast.makeText(getActivity(),"onDoubleTapEvent",Toast.LENGTH_SHORT).show();
                return super.onDoubleTapEvent(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Toast.makeText(getActivity(),"onSingleTapConfirmed",Toast.LENGTH_SHORT).show();
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onContextClick(MotionEvent e) {
                Toast.makeText(getActivity(),"onContextClick",Toast.LENGTH_SHORT).show();
                return super.onContextClick(e);
            }
        });
    }

    public void initScale(){
        mScaleGestureDetector = new ScaleGestureDetector(getActivity(), new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                Toast.makeText(getActivity(),"onScaleBegin",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });
    }


}
