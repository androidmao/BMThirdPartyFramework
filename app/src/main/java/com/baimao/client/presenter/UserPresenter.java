package com.baimao.client.presenter;

import com.baimao.client.bean.TranslationResult;
import com.baimao.client.net.NetWorkCallBack;
import com.baimao.client.net.ReqCallBack;
import com.baimao.client.net.RequestCallback;
import com.baimao.client.request.UserService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class UserPresenter extends BasePresenter {

    public void getTranslationResult(String content, RequestCallback<TranslationResult> requestCallback) {
//        Observable<TranslationResult> observable = getManager().getService(UserService.class).getTranslationResult("ajax.php", "fy", "auto", "auto", content);
        Map<String, String> map = new HashMap<>();
        map.put("a", "fy");
        map.put("f", "auto");
        map.put("t", "auto");
        map.put("w", content);
        Observable<TranslationResult> observable = getManager().getService(UserService.class).getTranslationResult("ajax.php", map);
        getManager().request(observable, requestCallback);
    }


    public void getResult(String content, NetWorkCallBack callback) {
//        Observable<TranslationResult> observable = getManager().getService(UserService.class).getTranslationResult("ajax.php", "fy", "auto", "auto", content);
        Map<String, String> map = new HashMap<>();
        map.put("a", "fy");
        map.put("f", "auto");
        map.put("t", "auto");
        map.put("w", content);


//        map.put("os_type", "MacOS");
//        map.put("os_version", "auto");
//        map.put("model", "auto");
//        map.put("device_id", content);


//        http://api.alluxs.com/api/v1/ClientApp/AppUpgrade/Upgrade?os_type=MacOS&os_version=11222&model=213223&device_id=3212

        Call<ResponseBody> call = getManager().getService(UserService.class).getResult("ajax.php", map);

//        Call<ResponseBody> call = getService(UserService.class).getResult("api/v1/ClientApp/AppUpgrade/Upgrade", map);
        getManager().request(call, callback);
    }

}
