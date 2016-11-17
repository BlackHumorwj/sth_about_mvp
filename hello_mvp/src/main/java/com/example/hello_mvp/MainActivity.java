package com.example.hello_mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import C.MessageContract;
import P.MessagePresenter;

public class MainActivity extends AppCompatActivity implements MessageContract.MessageView{

    private MessagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //view 中拿到Presenter 对象
        mPresenter = new MessagePresenter(this);

        mPresenter.start();


    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {

    }
}
