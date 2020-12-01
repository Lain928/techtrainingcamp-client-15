package com.example.bytedance.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.example.bytedance.R;
/**
 *
 * 1.声明界面
 * 2.定义变量
 * 3.调用类Animation
 * 4.写方法让它动起来
 * @author Rain
 *
 */
public class WelcomeActivity extends Activity{
    // 声明控件对象
    private TextView textView;
    //声明时间有多少;
    private int count = 5;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 下面的话就是去除标题的方法
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        // 初始化控件对象textView
        textView = (TextView) findViewById(R.id.textView);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        handler.sendEmptyMessageDelayed(0, 1000);
    }
    //咱在写一个计算Welcome界面的广告时间结束后进入主界面的方法
    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }
    //进行一个消息的处理
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount()+"");
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                textView.startAnimation(animation);
            }
        };
    };
}





