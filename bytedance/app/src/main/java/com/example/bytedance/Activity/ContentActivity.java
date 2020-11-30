package com.example.bytedance.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;

import com.example.bytedance.R;
import com.zzhoujay.markdown.MarkDown;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContentActivity extends Activity {
    private SharedPreferences sharedPreferences;
    private String strToken,textId;
    private static String content;
    private static TextView tv_author,tv_title,tv_time,tv_data;
    private static String time,title,author;
    private static Drawable drawable ;
    private static Drawable drawable1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        tv_author = findViewById(R.id.author_data);
        tv_title = findViewById(R.id.title_data);
        tv_time = findViewById(R.id.time_data);
        tv_data = findViewById(R.id.content_data);
        //获取对应链表中的信息
        textId = getIntent().getStringExtra("textId");
        author = getIntent().getStringExtra("author");
        title = getIntent().getStringExtra("title");
        time = getIntent().getStringExtra("time");
        drawable = getResources().getDrawable(R.mipmap.ic_launcher);


        sharedPreferences = getSharedPreferences("ccc",MODE_PRIVATE);
        strToken = sharedPreferences.getString("token","");
        OKHttpGet(strToken,textId);
    }

    public static void OKHttpGet(String token, String textId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url="https://vcapi.lvdaqian.cn/article/" + textId + "?markdown=true";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .get()
                            .url(url)
                            .addHeader("accept","application/json")
                            .addHeader("Authorization","Bearer " + token)
                            .build();

                    Call call = client.newCall(request);
                    Response response = call.execute();
                    int nCode = response.code();
                    String str = response.body().string();
                    JSONObject rootObject = new JSONObject(str);
                    content = rootObject.getString("data");
                    tv_author.setText(author);
                    tv_title.setText(title);
                    tv_time.setText(time);
                    tv_data.setText(content);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
