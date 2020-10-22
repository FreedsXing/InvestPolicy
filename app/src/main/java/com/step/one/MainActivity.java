package com.step.one;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_to_first)
    TextView tvToFirst;
    @BindView(R.id.tv_to_second)
    TextView tvToSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToFirst.setOnClickListener(this);
        tvToSecond.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_to_first:
                intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_to_second:
                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
