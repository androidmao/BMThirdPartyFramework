package com.baimao.client.net;

import android.util.Log;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {


    private Retrofit retrofit;

    private OkHttpClient client;

    private Retrofit getRetrofit() {
        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();


                    Log.e("TAG", "interceptintercept----"+original.headers().toString());

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("header-key", "value1")
                            .addHeader("header-key", "value2");

                    Request request = requestBuilder.build();

                    Log.e("TAG", "interceptintercept----"+request.headers().toString());

                    return chain.proceed(request);
                }
            });

            client = builder.build();

            //步骤4:创建Retrofit对象
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://fy.iciba.com/")
//                    .baseUrl("http://api.alluxs.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public <T> T getService(Class<T> service) {
        return getRetrofit().create(service);
    }

    public <T> void request(Observable<T> observable, DisposableObserver<T> observer) {
        // 步骤5:创建 网络请求接口 的实例
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void request(Call<ResponseBody> call, final ReqCallBack callback) {

        if (callback != null) {
            callback.onStart();
        }
        if (call != null) {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (callback != null) {
                        callback.onEnd();
                        callback.onResponse(call, response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (callback != null) {
                        callback.onEnd();
                        callback.onError(call, t);
                    }
                }
            });
        }


    }


}
