package com.example.administrator.expressuserclient.weight.back;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MirrorSwipeBack {

  public static MirrorSwipeBackLayout attach(Activity activity, MirrorSwipeBackLayout.OnSwipeBackListener fun, @LayoutRes int layoutId) {
    MirrorSwipeBackLayout layout = (MirrorSwipeBackLayout) LayoutInflater.from(activity).inflate(layoutId,null);
    new Helper(layout).attachSwipeBack(activity,fun);
    return layout;
  }
  public static MirrorSwipeBackLayout attach(Activity activity,@LayoutRes int layoutId) {
    MirrorSwipeBackLayout layout = (MirrorSwipeBackLayout) LayoutInflater.from(activity).inflate(layoutId,null);
    new Helper(layout).attachSwipeBack(activity,null);
    return layout;
  }

  static class Helper {

    final MirrorSwipeBackLayout mSwipeBackLayout;

    Helper(MirrorSwipeBackLayout mirrorSwipeBackLayout) {
      mSwipeBackLayout = mirrorSwipeBackLayout;
    }

    void attachSwipeBack(final Activity activity, MirrorSwipeBackLayout.OnSwipeBackListener fun) {
      if (fun == null) {
        mSwipeBackLayout.setSwipeBackListener(new MirrorSwipeBackLayout.OnSwipeBackListener() {
          @Override
          public void completeSwipeBack() {
            activity.finish();
          }
        });
      } else {
        mSwipeBackLayout.setSwipeBackListener(fun);
      }

      if (activity == null || activity.getWindow() == null || !(activity.getWindow().getDecorView() instanceof ViewGroup)) {
        return;
      }

      ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
      if(decorView != null) {
        if(decorView.getChildCount() > 0) {
          View child = decorView.getChildAt(0);
          decorView.removeView(child);
          mSwipeBackLayout.addView(child);
        }

        decorView.addView(mSwipeBackLayout);
      }
    }
  }
}