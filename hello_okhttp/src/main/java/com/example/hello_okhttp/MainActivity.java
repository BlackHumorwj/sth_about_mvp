package com.example.hello_okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 一般的get请求
 * 一般的post请求
 * 基于Http的文件上传
  文件下载
  加载图片
  支持请求回调，直接返回对象、对象集合
  支持session的保持
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okhttpGet();
        okhttpPost();
        okhttpUpload();
    }

    /**
     * okhttp 上传文件
     */
    private void okhttpUpload() {
        OkHttpClient okHttpClient = new OkHttpClient();

        /**
         * 构建一个类似于表单上传的请求体
         * MultipartBuilder 的使用
         */
        File file = new File(Environment.getExternalStorageDirectory(), "balabala.mp4");

        //将File装入请求体
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"username\""),
                        RequestBody.create(null, "文件"))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"mFile\"; filename=\"wjd.mp4\""), fileBody)
                .build();




        Request request = new Request.Builder().url("").post(requestBody).build();

        Call newCall = okHttpClient.newCall(request);

        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });

    }

    /**
     * post 请求添加键值对
     */
    private void okhttpPost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        /**
         * 构建一个请求体
         * FormEncodingBuilder
         * MultipartBuilder
         */
        RequestBody body = new FormEncodingBuilder()
                .add("","")
                .add("","")
                .add("","")
                .add("","")
                .build() ;
        Request request = new Request.Builder().post(body).url("").build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new MyCallBack().setCallback(new MyCallBack.ResPonseCallback() {
            @Override
            public void success() {

            }

            @Override
            public void error() {

            }

            @Override
            public void fail() {

            }
        }));


    }

    /**
     * okHttp get请求
     */
    private void okhttpGet() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("path").build();

        //添加头信息
        /**
         * addHeader(String name, String value) 一个键可以添加多个值，不会覆盖
         * header(String name, String value) 一个键只可以添加一个值，重复会覆盖
         */

        Call newCall = okHttpClient.newCall(request);

        //异步请求
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    /**############### 对返回的结果进行预处理后，再返回  start###############*/
    private static class MyCallBack implements Callback{

        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            //注意：该方法还在子线程中，如果要更新UI需要在主线程

            //将返回的数据--转为String
            String jsonString = response.body().string();

            //转为流数据--可以支持文件下载
            InputStream inputStream = response.body().byteStream();
        }

        //定义一个接口对返回的结果进行回调

        /**
         * 也可以不用回调啊
         */
        public interface ResPonseCallback{
            void success();
            void error();
            void fail();
        }

        ResPonseCallback mCallback;
        public MyCallBack setCallback(ResPonseCallback mCallback){
            this.mCallback = mCallback;
            return this;
        }

    }
    /**############### 对返回的结果进行预处理后，再返回  end ###############*/

}
