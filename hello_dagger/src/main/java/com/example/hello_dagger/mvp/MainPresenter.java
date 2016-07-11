package com.example.hello_dagger.mvp;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * @author pikachu
 * @time 2016/7/7 14:57
 * @desc 逻辑的处理 view 和 module 层的逻辑交互
 */
public class MainPresenter implements MainContract.Presenter {
    /**
     *  拿到View层的引用
     */
    MainContract.View mView;

    public MainPresenter( MainContract.View mView){
        this.mView = mView;
    }


    @Override
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);

                final String data = "hello MVP";

                AppCompatActivity activity = (AppCompatActivity) mView;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.updateUI(data);
                    }
                });
            }
        }).start();
    }

    @Override
    public void loadData2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);

                final String data = "hello MVP";
                final ArrayList<String> list = new ArrayList<>();
                for (int i=0;i<100;i++){
                    list.add("hello mvp"+i);
                }


                AppCompatActivity activity = (AppCompatActivity) mView;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // mView.updateUI(data);
                        mView.updateUI2(list);
                    }
                });


            }
        }).start();
    }
}
