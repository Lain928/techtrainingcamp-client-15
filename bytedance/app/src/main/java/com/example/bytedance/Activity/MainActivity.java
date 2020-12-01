package com.example.bytedance.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bytedance.Info.JsonLoad;
import com.example.bytedance.Info.JsonUtils;
import com.example.bytedance.R;
import com.example.bytedance.adapter.MyAdapter;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyAdapter listAdapter;
    private String strToken = "";
    private List<JsonLoad> jsonDataList;
    private SharedPreferences sharedPreferences;
    private static boolean flag = false;
    private static int nCode = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.listview);
        String strTextId = "event_01";
        sharedPreferences = getSharedPreferences("byteDance",MODE_PRIVATE);
        strToken = sharedPreferences.getString("token","");
        MainOKHttpGet(strToken, strTextId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonDataList = JsonUtils.getInstance().getJsonData(MainActivity.this);
                listAdapter = new MyAdapter(MainActivity.this,jsonDataList);
                listView.setAdapter(listAdapter);
            }
        }).start();

        // listview点击事件 点击后判断缓存中是否存在账号密码从而决定是否进入登录界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //存入缓存的登录账号密码你不在进行输入
                String textId = "";
                String author = "";
                String title = "";
                String time = "";

                if (nCode == 401) {
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                    nCode = 200;
                    return;
                }

                else{
                    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                    textId = jsonDataList.get(position).getId();
                    author = jsonDataList.get(position).getAuthor();
                    title = jsonDataList.get(position).getTitle();
                    time = jsonDataList.get(position).getPublishTime();
                    intent.putExtra("textId",textId);
                    intent.putExtra("author",author);
                    intent.putExtra("title",title);
                    intent.putExtra("time",time);
                    startActivity(intent);
                }
            }
        });
    }

    public static void MainOKHttpGet(String token, String textId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "https://vcapi.lvdaqian.cn/article/" + textId + "?markdown=true";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .get()
                            .url(url)
                            .addHeader("accept", "application/json")
                            .addHeader("Authorization", "Bearer " + token)
                            .build();

                    Call call = client.newCall(request);
                    Response response = call.execute();
                    nCode = response.code();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}