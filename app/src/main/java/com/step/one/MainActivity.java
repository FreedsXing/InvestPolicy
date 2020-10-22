package com.step.one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提交测试
        setContentView(R.layout.activity_main);


        final Handler mHandler = new Handler();

        OkHttpClient okHttpClient = new OkHttpClient();

        final Request request = new Request.Builder()
                .method("GET", null)
                .url("https://www.baidu.com")
                .build();

       Call call = okHttpClient.newCall(request);

        final TextView tvContent = findViewById(R.id.tv_content);

       call.enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               Log.e("TAG", TAG + "-----------" + e.toString());
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               Headers headers = response.headers();

               Set<String> names = headers.names();
               for(String name : names){
                   Log.e("TAG", TAG + "-----------------" + name + " = " + headers.get(name));
               }

               Log.e("TAG", TAG + "-----------" + response.toString());
               Log.e("TAG", TAG + "-----------" + response.headers().toString());
              // Log.e("TAG", TAG + "-----------" + response.body().string());   只能调用一次，  E/AndroidRuntime: FATAL EXCEPTION: OkHttp Dispatcher

               final String text = response.body().string();

               //当你异步请求的时候，是不能在子线程修改UI的，所以这里我用了一个Handler去操作相应的内容
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvContent.setText(text);
                    }
                });
           }
       });
    }
}
