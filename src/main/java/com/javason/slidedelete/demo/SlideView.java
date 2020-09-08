package com.javason.slidedelete.demo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.javason.slidedelete.R;

import static android.widget.AdapterView.INVALID_POSITION;

public class SlideView extends LinearLayout {
    private static String TAG = "【SlideView】";
    private LinearLayout itemRoot;
    private int MAX_WIDTH;
    private int mLastX;
    private int mLastInterceptX;
    private int mLastInterceptY;
    private final LinearLayout ll;
    private int x;
    private int y;

    public SlideView(Context context) {
        super(context);
        MAX_WIDTH = dp2px(context, 100);
        LayoutInflater.from(context).inflate(R.layout.item_view, this, true);
        ll = (LinearLayout) this.getChildAt(0);
    }

    //
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: ACTION_DOWN");
//                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: ACTION_MOVE");
//                int dx = x - mLastInterceptX;
//                int dy = y - mLastInterceptY;
//                if (Math.abs(dx) < Math.abs(dy)) {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                } else {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: ACTION_UP");
            default:
                break;
        }
//        mLastInterceptX = x;
//        mLastInterceptY = y;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:

                Log.d(TAG, "onInterceptTouchEvent: ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercept = false;
//        int X = (int) ev.getX();
//        int Y = (int) ev.getY();
//        int dx = X - mLastInterceptX;
//        int dy = Y - mLastInterceptY;
//        int action = ev.getAction() & MotionEvent.ACTION_MASK;
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                System.out.println("-==>SlideView OnInterceptTouchEvent:ACTION_DOWN: ");
//                System.out.println("==>getParent: " + getParent());
//
//            case MotionEvent.ACTION_MOVE:
//                System.out.println("-==>SlideView OnInterceptTouchEvent:ACTION_MOVE: ");
//                if (Math.abs(dx) < Math.abs(dy)) {
//                    break;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercept = false;
//                break;
//        }
//        mLastInterceptY = X;
//        mLastInterceptY = Y;
//        return super.onTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        x = (int) ev.getX();
        y = (int) ev.getY();
        int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX=x;
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
              return true;

            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "onTouchEvent: ACTION_MOVE");
                int scrollX = ll.getScrollX();
                Log.d(TAG, "onTouchEvent: scrollX:" + scrollX);
                int newScrollX = scrollX + mLastX - x;
                Log.d(TAG, "onTouchEvent: mLastX:" + mLastX);
                Log.d(TAG, "onTouchEvent: x:" + x);
                Log.d(TAG, "onTouchEvent: newScrollX:" + newScrollX);
                if (newScrollX < 0) {
                    newScrollX = 0;
                } else if (newScrollX > MAX_WIDTH) {
                    newScrollX = MAX_WIDTH;
                }
                ll.scrollTo(newScrollX, 0);
                Log.d(TAG, "onTouchEvent: currentScrollX:"+ll.getScrollX());
            }
            break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                int scrollX = ll.getScrollX();
                Log.d(TAG, "onTouchEvent: scrollX:" + scrollX);
                int newScrollX = 0;
                Log.d(TAG, "onTouchEvent: newScrollX:" + newScrollX);
                if (scrollX > MAX_WIDTH / 2) {
                    newScrollX = MAX_WIDTH;
                } else {
                    newScrollX = 0;
                }
                ll.scrollTo(newScrollX, 0);
            }
            break;

        }
        Log.d(TAG, "onTouchEvent:赋值");
        mLastX = x;
        return super.onTouchEvent(ev);
    }

    public int dp2px(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
