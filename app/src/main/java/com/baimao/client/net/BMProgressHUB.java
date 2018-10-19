package com.baimao.client.net;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.baimao.client.R;

public class BMProgressHUB extends Dialog {


    private ImageView iv_progress;

    public BMProgressHUB(@NonNull Context context) {
        super(context, R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_progress);
        setCancelable(true);
        setCanceledOnTouchOutside(false);


        iv_progress = findViewById(R.id.iv_progress);


    }

    @Override
    public void show() {
        super.show();
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 21600f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(60000);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        iv_progress.startAnimation(rotateAnimation);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        iv_progress.clearAnimation();
    }
}
