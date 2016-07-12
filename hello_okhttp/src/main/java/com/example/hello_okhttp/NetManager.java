package com.example.hello_okhttp;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

/**
 * @author pikachu
 * @time 2016/7/12 10:53
 * @desc
 */
public class NetManager {
    private static NetManager   mInstance;
    private final  Handler      mHandler;
    private final  OkHttpClient mOkHttpClient;
    private final Request.Builder mBuilder;

    private NetManager() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
        mBuilder = new Request.Builder();
    }

    public static NetManager getInstance() {
        if (mInstance == null) {
            synchronized (NetManager.class) {
                if (mInstance == null) {
                    mInstance = new NetManager();
                }
            }
        }
        return mInstance;
    }


    /**
     * 传输返回结果
     * @param callback 结果回调
     * @param request  当前的请求对象
     */
    private <T> void  deliveryResult(final ResultCallback<T> callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFailedStringCallback(request, e, callback);
            }
            @Override
            public void onResponse(final Response response) {
               sendSuccessResultCallback(response,callback);
            }
        });
    }



    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onError(request, e);
            }
        });
    }


    /**
     * 将成功的信息在主线程返回
     * @param response   获取的结果对象
     * @param callback 回调对象
     */
    private void sendSuccessResultCallback(final Response response, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(response);
                }
            }
        });
    }

    /**
     * 构建一个post Request
     * @param url
     * @param params
     * @return Request
     */
    private Request buildPostRequest(String url, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return mBuilder.url(url).post(requestBody).build();
    }

    /**
     * @param url
     * @param callback
     * @param params
     * @param affectModule 收影响的模块用 ","隔开
     * @param <T>
     * @dec 操作数据
     */
    private <T> void operate(String url, final ResultCallback<T> callback, Map<String, String> params, String affectModule) {
        Param[] paramsArr = NetUtils.map2Params(params);
        Request request = buildPostRequest(url, paramsArr);
        if (callback!=null) callback.setModule(affectModule);
        deliveryResult(callback, request);
    }

    /**
     *
     * @param url
     * @param callback
     * @param params
     * @param portName 接口名
     * @param <T>
     * @desc 获取数据
     */
    private <T> void select(String url, final ResultCallback<T> callback, Map<String, String> params, String portName){
        Param[] paramsArr = NetUtils.map2Params(params);
        Request request = buildPostRequest(url, paramsArr);
        if (callback!=null) callback.setPort(portName);
        deliveryResult(callback, request);
    }

    public static void operateRequest(String url,String affectModule, Map<String, String> params, ResultCallback callback) {
        getInstance().operate(url, callback, params,affectModule);
    }

    public static void  selectRequest(String url,String portName, Map<String, String> params, ResultCallback callback) {
        getInstance().select(url, callback, params,portName);
    }



}
