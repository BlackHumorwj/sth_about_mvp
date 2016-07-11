package com.example.hello_dagger.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.hello_dagger.R;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author pikachu
 * @time 2016/7/8 14:38
 * @desc
 */
public class SecondActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView mTextView;

    @Inject
    String hello;

    @Inject
    @Named("hi")
    String hi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        DaggerAppComponent.builder().appMoodule(new AppMoodule()).build().inject(this);
    }

    @OnClick(R.id.button)
    void getData(View view) {
        mTextView.setText(hello + "--" + hi);
    }
}
