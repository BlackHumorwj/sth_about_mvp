package com.example.hello_okhttp;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pikachu
 * @time 2016/7/12 11:43
 * @desc
 */
public  class ResultCallback<T> {
    private Class<T> clazz;
    Type mType;
    private  Gson mGson;
    /**
     * 受影响的模块名
     */
    private String mModule;

    /**
     * 需要缓存的接口名
     */
    private String mPort;

    public ResultCallback(Class<T> clazz) {
        mType = getSuperclassTypeParameter(getClass());
        if (mGson==null){
            mGson = new Gson();
        }
        this.clazz = clazz;
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public void onError(Request request, Exception e) {
        error(request, e);
    }

    public void onResponse(Response response) {
        String jsonString = null;
        try {
            jsonString = response.body().string();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void success(BaseData<T> baseData){}

    public void fail(BaseData<T> baseData) {}

    public void error(Request request, Exception e) {

    }

    /**
     *
     * @param module 设置受影响的模块名
     */
    public void setModule(String module) {
        mModule = module;
    }

    public void setPort(String port) {
        mPort = port;
    }
}
