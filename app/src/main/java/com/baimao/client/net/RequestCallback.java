package com.baimao.client.net;

import android.content.Context;
import android.util.Log;

import io.reactivex.observers.DisposableObserver;

public class RequestCallback<T> extends DisposableObserver<T> {


    private Context context;

    public RequestCallback(Context context) {
        this.context = context;
    }

    private BMProgressHUB hub;

    private void showProgressDialog() {
        if (hub == null) {
            hub = new BMProgressHUB(context);
            if (!hub.isShowing()) {
                hub.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (hub != null) {
            hub.dismiss();
            hub = null;
        }
    }

    @Override
    protected void onStart() {
        showProgressDialog();
        Log.i("RXJAVA2", "onStart");
    }

    @Override
    public void onNext(T t) {
        Log.i("RXJAVA2", "onNext");
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.i("RXJAVA2", "onComplete");
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Log.i("RXJAVA2", "onError");
    }
}
