package com.skypan.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextActivity extends AppCompatActivity {


    // 声明控件
    private Button mBtnLogin;
    private EditText mEtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        // 监听登录按钮
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditTextActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            }
        });

        // 找到 et_1
        mEtUserName = (EditText) findViewById(R.id.et_1);
        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("edit",s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
