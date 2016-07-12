package com.example.hello_okhttp;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @author pikachu
 * @time 2016/7/12 9:57
 * @desc 数据返回的回调
 * <>p</>
 * 1、对数据进行预处理，一些系统的返回码进行处理
 */
public class ResultCallBackListener<T> implements Callback {
    private       Class<T> clazz;
    private final Gson     mGson;

    public ResultCallBackListener(Class<T> clazz) {
        this.clazz = clazz;
        mGson = new Gson();
    }

    @Override
    public void onFailure(Request request, IOException e) {
        error(request, e);
    }

    @Override
    public void onResponse(Response response) throws IOException {
        String jsonString = response.body().string();

        //  BaseData<T> baseData = new Gson().fromJson(jsonString,new TypeToken<BaseData>(){}.getType());

        BaseData<T> baseData = mGson.fromJson(jsonString, BaseData.class);
        if (baseData == null) {
            baseData = new BaseData<>();
        }
        if (!clazz.equals(String.class)) {
            baseData.setT(mGson.fromJson(jsonString, clazz));
        }
        //获取头信息中数据
        String ck_code = response.header("ck_code");
        String ck_msg = response.header("ck_msg");

        /**
         * 对于系统返回的code进行处理
         */


        if (baseData.isSuccess()) {
            success(baseData);
        } else {
            fail(baseData);
        }


    }


    public void success(BaseData<T> baseData) {
    }

    public void fail(BaseData<T> baseData) {
    }

    public void error(Request request, IOException e) {
    }


}
