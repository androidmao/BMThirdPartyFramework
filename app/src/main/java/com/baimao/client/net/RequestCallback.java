package com.baimao.client.net;

import android.content.Context;

import io.reactivex.observers.DisposableObserver;

/**
 * @author Cris
 */
public abstract class RequestCallback<T> extends DisposableObserver<T> {


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
        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
    }


}
