package com.baimao.client.presenter;

import com.baimao.client.bean.TranslationResult;
import com.baimao.client.net.RequestCallback;
import com.baimao.client.request.UserService;

import io.reactivex.Observable;

public class UserPresenter extends BasePresenter {

    public void getTranslationResult(String content, RequestCallback<TranslationResult> requestCallback) {
        Observable<TranslationResult> observable = getManager().getService(UserService.class).getTranslationResult("ajax.php", "fy", "auto", "auto", content);
        getManager().request(observable, requestCallback);
    }

}
