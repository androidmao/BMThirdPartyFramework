package com.baimao.client.net;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private Retrofit retrofit;

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            //步骤4:创建Retrofit对象
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://fy.iciba.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

}
