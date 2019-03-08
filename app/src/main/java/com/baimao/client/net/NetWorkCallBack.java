package com.baimao.client.net;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public abstract class NetWorkCallBack<S, F> implements ReqCallBack {

    private static final Gson gson = new Gson();

    private Activity activity;

    private boolean isShowgressDialog;

    public NetWorkCallBack(Activity activity, boolean isShowgressDialog) {
        this.activity = activity;
        this.isShowgressDialog = isShowgressDialog;
    }

    private BMProgressHUB hub;

    private void showProgressDialog() {
        if (hub == null) {
            hub = new BMProgressHUB(activity);
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
    public void onStart() {
        Log.e("TAG", "onStart()" + Thread.currentThread().getName());
        if (isShowgressDialog) {
            showProgressDialog();
        }
    }

    @Override
    public void onEnd() {
        if (isShowgressDialog) {
            dismissProgressDialog();
        }
        Log.e("TAG", "onEnd()" + Thread.currentThread().getName());
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


        S succeedData = null;
        F failedData = null;
        int code = response.code();
        String serverJson;


        if (response.body() != null) {

            try {

                Type succeed = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Type failed = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

                serverJson = response.body().string();

                Log.e("HttpRequest", "ResponseBody:" + serverJson);

                if (code >= 200 && code < 300) {

                    if (succeed.equals(String.class)) {
                        succeedData = (S) serverJson;
                    } else {
                        succeedData = gson.fromJson(serverJson, succeed);
                    }

                } else if (code >= 400 && code < 500) {
                    failedData = gson.fromJson(serverJson, failed);

                    if (code == 403) {
                        failedData = gson.fromJson("{\"error_code\": \"999999998\",\"error_message\": \"没有访问权限\"}", failed);
                    } else if (code == 404) {
                        failedData = gson.fromJson("{\"error_code\": \"999999998\",\"error_message\": \"未找到指定路径\"}", failed);
                    }

                    if (failedData == null) {
                        failedData = gson.fromJson("{\"error_code\": \"999999998\",\"error_message\": \"未知异常\"}", failed);
                    }

                } else if (code >= 500) {
                    failedData = gson.fromJson("{\"error_code\": \"999999998\",\"error_message\": \"服务器异常\"}", failed);
                }

            } catch (IOException e) {
                onError(null, e);
            }
        }


        onResponse(SimpleResponse.<S, F>newBuilder()
                .code(response.code())
                .succeed(succeedData)
                .failed(failedData)
                .build());

    }


    public void onResponse(SimpleResponse<S, F> response) {

    }

    @Override
    public void onError(Call<ResponseBody> call, Throwable t) {

    }


}
