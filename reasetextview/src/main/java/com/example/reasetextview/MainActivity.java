package com.example.reasetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    private RiseNumberTextView mTextViewAll;
    private RiseNumberTextView mTextViewIn;
    private RiseNumberTextView mTextViewOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewAll=(RiseNumberTextView)findViewById(R.id.text);
        mTextViewIn=(RiseNumberTextView)findViewById(R.id.in);
        mTextViewOut=(RiseNumberTextView)findViewById(R.id.out);

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                init();

            }
        });

        //init();

    }
    private void init(){
        mTextViewAll.withNumber(46000).fromNumber(-9000).start();
        mTextViewIn.withNumber(-489.15f).fromNumber(100.09f).start();
        mTextViewOut.withNumber(-367.24f).fromNumber(0.11f).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
