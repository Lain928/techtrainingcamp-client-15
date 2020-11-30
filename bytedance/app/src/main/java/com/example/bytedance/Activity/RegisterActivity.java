package com.example.bytedance.Activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bytedance.R;

public class RegisterActivity extends Activity {
    private EditText account;
    private EditText password;
    private EditText rePassword;
    private Button btn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        account = findViewById(R.id.account_re);
        password = findViewById(R.id.password_re);
        rePassword = findViewById(R.id.rePassword_re);
        btn = findViewById(R.id.register);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getAccount =  account.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                String getRePassword = rePassword.getText().toString().trim();
                if (getAccount.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getPassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!getRePassword.equals(getPassword)){
                    Toast.makeText(RegisterActivity.this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Intent intent = getIntent();
                    intent.putExtra("account",getAccount);
                    intent.putExtra("password",getPassword);
                    setResult(1,intent);
                    finish();
                }

            }
        });
    }
}

