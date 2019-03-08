package com.baimao.client.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.baimao.client.R;
import com.baimao.client.adapter.MyListAdapter;
import com.baimao.client.bean.TranslationResult;
import com.baimao.client.bean.UserInfo;
import com.baimao.client.net.NetWorkCallBack;
import com.baimao.client.net.RequestCallback;
import com.baimao.client.net.SimpleResponse;
import com.baimao.client.presenter.UserPresenter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tiet_content)
    TextInputEditText tiet_content;

    @BindView(R.id.tv_result)
    TextView tv_result;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {

        ArrayList<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(new UserInfo());
        userInfos.add(new UserInfo());
        userInfos.add(new UserInfo());

        MyListAdapter adapter = new MyListAdapter(this, userInfos);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_request)
    public void onClick() {

        String content = tiet_content.getText().toString();
        if (TextUtils.isEmpty(content)) {
            return;
        }

        UserPresenter presenter = new UserPresenter();
//        presenter.getTranslationResult(content, new RequestCallback<TranslationResult>(this) {
//
//            @Override
//            public void onNext(TranslationResult translationResult) {
//                super.onNext(translationResult);
//                tv_result.setText(translationResult.getContent().getOut());
//            }
//
//        });

        presenter.getResult(content, new NetWorkCallBack<String, String>(this, true) {

            @Override
            public void onResponse(SimpleResponse<String, String> response) {
                super.onResponse(response);
                tv_result.setText(response.succeed());
            }
        });


    }

}
