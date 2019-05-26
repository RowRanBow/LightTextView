package com.zjr.lighttextview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LightTextView mLtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLtv = findViewById(R.id.ltv_1);
        mLtv.setLeftImageViewOnClickListener(new LightTextView.OnLeftImageViewClickListener() {
            @Override
            public void Onclick() {
                Toast.makeText(MainActivity.this,"lalala",Toast.LENGTH_LONG).show();
            }
        }).setRightImageViewOnClickListener(new LightTextView.OnRightImageViewClickListener() {
            @Override
            public void Onclick() {
                Toast.makeText(MainActivity.this,"hehehe",Toast.LENGTH_LONG).show();
            }
        });
    }
}
