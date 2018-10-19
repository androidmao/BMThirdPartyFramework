package com.baimao.client.request;

import com.baimao.client.bean.TranslationResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface UserService {

    @GET()
    Observable<TranslationResult> getTranslationResult(@Url String url, @Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);

}
