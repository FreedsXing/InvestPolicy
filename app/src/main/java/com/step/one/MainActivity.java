package com.step.one;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_to_first)
    TextView tvToFirst;
    @BindView(R.id.tv_to_second)
    TextView tvToSecond;

    @BindView(R.id.iv_star)
    ImageView ivStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToFirst.setOnClickListener(this);
        tvToSecond.setOnClickListener(this);
        ivStar.setOnClickListener(this::onClick);




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
            case R.id.iv_star:
                Log.e("TAG", TAG + "---------isSelected=" + ivStar.isSelected());
                Log.e("TAG", TAG + "---------isClickable=" + ivStar.isClickable());
                Log.e("TAG", TAG + "---------hasFocus=" + ivStar.hasFocus());

                if (ivStar.isSelected()){
                    ivStar.setSelected(false);
                }else {
                    ivStar.setSelected(true);
                }
                break;
            default:
                break;
        }
    }
}
