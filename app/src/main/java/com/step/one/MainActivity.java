package com.step.one;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

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


    @BindView(R.id.margin_seconds)
    TextView tvMarginSeconds;
    @BindView(R.id.start_cal)
    TextView tvStartCal;
    @BindView(R.id.end_cal)
    TextView tvEndCal;

    class CalTimeHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    class CalTimeRunnable implements Runnable{
        @Override
        public void run() {
            tvMarginSeconds.setText(getTime());
            calTimeHandler.postDelayed(calTimeRunnable, 1000);
        }
    }


    private CalTimeHandler calTimeHandler = new CalTimeHandler();
    private CalTimeRunnable calTimeRunnable;

    /**
     * 获取时间
     *
     * @return 格式化后的时间
     */
    private static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        return format.format(new Date());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToFirst.setOnClickListener(this);
        tvToSecond.setOnClickListener(this);
        ivStar.setOnClickListener(this::onClick);

        tvStartCal.setOnClickListener(this::onClick);
        tvEndCal.setOnClickListener(this::onClick);


        Log.i("TAG", TAG + "----onCreate----" + Thread.currentThread().getName());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", TAG + "----onCreate----" + Thread.currentThread().getName());
            }
        });
        thread.start();




        String str1=  "hello";
        String str2 = " world";
        String str3 = "hello" + " world";
        String str4 = str1 + " world";
        String str5 = "hello world";

        Log.i("TAG", "---------------" + String.valueOf(str3 == str4));
        Log.i("TAG", "---------------" + String.valueOf(str3 == str5));

        File file = new File("/android");
//
//        Log.i("TAG", TAG + "--------------" + getExternalCacheDir());
//        String str = "12.33";
//        String [] first = str.split(",");
//        for (int i = 0; i < first.length; i++) {
//            Log.i("TAG", TAG + "------------------first=" + first[i]);
//        }
//
//        String str2 = "12.33,";
//        String [] first2 = str2.split(",");
//        for (int i = 0; i < first2.length; i++) {
//            Log.i("TAG", TAG + "------------------first2=" + first2[i]);
//        }
//
//        String str3 = "12.33,44455";
//        String [] first3 = str3.split(",");
//        for (int i = 0; i < first3.length; i++) {
//            Log.i("TAG", TAG + "------------------first3=" + first3[i]);
//        }
//
//        String str4 = " 12.33 ,  444 5 5, ,      ,  ";
//        String str5 = str4.replace(" ", "");
//        String [] first4 = str5.split(",");
//        for (int i = 0; i < first4.length; i++) {
//            Log.i("TAG", TAG + "------------------first4=" + first4[i]);
//        }

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

            case R.id.start_cal:
                if (calTimeRunnable == null){
                    calTimeRunnable = new CalTimeRunnable();
                    calTimeHandler.post(calTimeRunnable);
                }
                break;
            case R.id.end_cal:
                if (calTimeHandler != null){
                    calTimeHandler.removeCallbacks(calTimeRunnable);
                }
                break;
            default:
                break;
        }
    }
}
