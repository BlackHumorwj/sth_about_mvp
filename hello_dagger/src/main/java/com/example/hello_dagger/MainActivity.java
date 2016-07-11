package com.example.hello_dagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hello_dagger.dagger.SecondActivity;
import com.example.hello_dagger.mvp.MainContract;
import com.example.hello_dagger.mvp.MainPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 定义契约类 contract 管理
 *           presenter和view
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter mMainPresenter;

    @Bind(R.id.textView)
    TextView mTextView;

    @Bind(R.id.listView)
    ListView mListView;

    @Bind(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.loadData();

    }

    @Override
    public void updateUI(String data) {
        mTextView.setText(data);
    }

    @Override
    public void updateUI2(List list) {

    }

    @OnClick(R.id.button)
    void  getData(View view){
        mMainPresenter.loadData2();
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
}
