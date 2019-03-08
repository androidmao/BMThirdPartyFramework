package com.baimao.client.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public interface ReqCallBack {

    void onStart();

    void onEnd();

    void onResponse(Call<ResponseBody> call, Response<ResponseBody> response);

    void onError(Call<ResponseBody> call, Throwable t);

}
