package com.yu.uiautomatortest.module.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yu.uiautomatortest.R;
import com.yu.uiautomatortest.module.main.activity.MainActivity;
import com.yu.uiautomatortest.util.ToastUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            doLogin();
        }
    }

    private void doLogin() {
        //ToastUtils.showToast("登录");

        String name = etUsername.getText().toString().trim();
        String passwd = etPassword.getText().toString().trim();

        Log.e("LoginActivity", "name: " + name + "   passwd: " + passwd);

        startActivity(new Intent(this, MainActivity.class));

    }
}
