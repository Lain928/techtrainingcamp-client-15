package com.example.bytedance.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import com.example.bytedance.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LogInActivity extends AppCompatActivity {

    private EditText account, password;
    private String strToken = "";
    private SharedPreferences sharedPreferences;
    private String getAccount, getPassword ;
    private CheckBox checkBox;
    private boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("234", MODE_PRIVATE);

        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

        findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccount = account.getText().toString().trim();
                getPassword = password.getText().toString().trim();
                if (getAccount.isEmpty()) {
                    Toast.makeText(LogInActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getPassword.isEmpty()) {
                    Toast.makeText(LogInActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                OKHttpPost();
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                flag = false;
                intent.putExtra("code",200);
                intent.putExtra("flag",flag);
                startActivity(intent);

                Toast.makeText(LogInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //如果选中，显示密码
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    public void OKHttpPost() {
        //开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 1 创建 okHttpClient
                    OkHttpClient client = new OkHttpClient.Builder().build();
                    // 2 创建RequestBody
                    Map m = new HashMap();
                    m.put("username", getAccount);
                    m.put("password", getPassword);
                    JSONObject jsonObject = new JSONObject(m);
                    String jsonStr = jsonObject.toString();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
                    //3 创建request
                    Request request = new Request.Builder()
                            .url("https://vcapi.lvdaqian.cn/login")
                            .addHeader("contentType", "application/json;charset=utf-8")
                            .post(requestBody)
                            .build();

                    // 4 创建call回调对象
                    final Call call = client.newCall(request);
                    // 5 发起请求
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(LogInActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                            try {
                                JSONObject rootObject = new JSONObject(result);
                                strToken = rootObject.getString("token");
                                if (strToken != "") {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("token", strToken);
                                    editor.apply();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
