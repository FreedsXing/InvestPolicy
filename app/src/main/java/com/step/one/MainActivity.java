package com.step.one;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_to_first)
    TextView tvToFirst;
    @BindView(R.id.tv_to_second)
    TextView tvToSecond;

    @BindView(R.id.iv_star)
    ImageView ivStar;

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToFirst.setOnClickListener(this);
        tvToSecond.setOnClickListener(this);
        ivStar.setOnClickListener(this::onClick);


        File file = new File("/android");

        Log.i("TAG", TAG + "--------------" + getExternalCacheDir());
        String str = "12.33";
        String [] first = str.split(",");
        for (int i = 0; i < first.length; i++) {
            Log.i("TAG", TAG + "------------------first=" + first[i]);
        }

        String str2 = "12.33,";
        String [] first2 = str2.split(",");
        for (int i = 0; i < first2.length; i++) {
            Log.i("TAG", TAG + "------------------first2=" + first2[i]);
        }

        String str3 = "12.33,44455";
        String [] first3 = str3.split(",");
        for (int i = 0; i < first3.length; i++) {
            Log.i("TAG", TAG + "------------------first3=" + first3[i]);
        }

        String str4 = " 12.33 ,  444 5 5, ,      ,  ";
        String str5 = str4.replace(" ", "");
        String [] first4 = str5.split(",");
        for (int i = 0; i < first4.length; i++) {
            Log.i("TAG", TAG + "------------------first4=" + first4[i]);
        }

        tvCommit.setOnClickListener(this);
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
            case R.id.tv_commit:
                String inputStr = etInput.getText().toString();

                if (!TextUtils.isEmpty(inputStr)){
                    tvCommit.setText("|" + inputStr + "|");
                }else {
                    tvCommit.setText("|||" + inputStr + "|||");
                }

                break;
            default:
                break;
        }
    }
}
