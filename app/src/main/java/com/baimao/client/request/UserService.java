package com.baimao.client.request;

import com.baimao.client.bean.TranslationResult;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UserService {

    @GET()
    Observable<TranslationResult> getTranslationResult(@Url String url, @Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);


    @GET()
    Observable<TranslationResult> getTranslationResult(@Url String url, @QueryMap Map<String, String> map);

    @GET()
    Call<ResponseBody> getResult(@Url String url, @QueryMap Map<String, String> map);


}
