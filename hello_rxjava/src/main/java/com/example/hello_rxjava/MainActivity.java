package com.example.hello_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Subscription mSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button viewById = (Button) findViewById(R.id.button1);
        Button v2 = (Button) findViewById(R.id.button2);
        if (viewById != null)
            viewById.setOnClickListener(this);

        v2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                pageOneDemo();
                break;
            case R.id.button2:
                pageTwoDemo();
                break;
        }
    }

    /**
     * 第二篇
     * 多个操作符的介绍
     *  from() : 将数组或集合一个一个输出
     *  flatMap(): 将Observable的输出作为它的输入，并返回一个新的 Obserable()
     *  filter() : 输入和输出的数据不变，只是过滤掉一些不符合要求的数据
     *  take() : 指定最多输出多少个数据
     *  doOnNext():允许我们在每次输出一个元素之前做一些额外的事情，比如这里的保存数据
     */
    private void pageTwoDemo() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(i,"傻逼"+i);
        }

        Observable<String> observable = Observable.from(list);
        /**
         * 观察者 与 被观察者 之间的联系纽带
         */
        mSubscribe = observable
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                Log.e("thread-flatMap1", Thread.currentThread().getName());

                return getTitles(s);
            }
        })
                .filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                Log.e("thread-flatMap2", Thread.currentThread().getName());
                return !TextUtils.isEmpty(s);
            }
        })
                .observeOn(Schedulers.io())
                .take(3)
                .doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("thread-doOnNext", Thread.currentThread().getName());
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("thread-subscribe", Thread.currentThread().getName());
            }
        });
    }


    //这个地方怎么处理呢
    private Observable<String> getTitles(String s) {
        return Observable.just("string");
    }

    /**
     * 第一篇
     */
    private void pageOneDemo() {
        //数据源发送数据
        /**
         * 数据源的创建有多种方式
         *  just()
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello rxJava");
                subscriber.onCompleted();
            }
        });

        //处理数据
        Subscriber<String> observer = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
        //绑定
        observable.subscribe(observer);
        /**
         * Observable subscribe() 有几个重载的方法 可以针对不同的情况进行单独处理
         */


        /**############### 使用操作符 map###############*/
        /**
         * 操作符 map：可以将数据源的数据进行转换处理
         */
        Observable<String> hello_rxjava = Observable.just("hello_rxjava");
        hello_rxjava.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return integer.toString();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        /**
         总结:
         1.Observable和Subscriber可以做任何事情
             Observable可以是一个数据库查询，Subscriber用来显示查询结果；
             Observable可以是屏幕上的点击事件，Subscriber用来响应点击事件；
             Observable可以是一个网络请求，Subscriber用来显示请求结果。
         2.Observable和Subscriber是独立于中间的变换过程的。
             在Observable和Subscriber中间可以增减任何数量的map。
             整个系统是高度可组合的，操作数据是一个很简单的过程。
         */

    }


    @Override
    protected void onPause() {
        super.onPause();
        if ( mSubscribe.isUnsubscribed()){
            mSubscribe.unsubscribe();
        }

    }
}
