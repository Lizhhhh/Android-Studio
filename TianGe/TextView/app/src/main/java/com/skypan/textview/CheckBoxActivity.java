package com.skypan.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {

    // 声明控件
    private CheckBox mCb5, mCb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        // 获取控件的视图
        mCb5 = (CheckBox) findViewById(R.id.cb_5);
        mCb6 = (CheckBox) findViewById(R.id.cb_6);

        mCb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(CheckBoxActivity.this,isChecked? "5选中":"5取消",Toast.LENGTH_SHORT).show();
            }
        });
        mCb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(CheckBoxActivity.this, isChecked? "6选中":"6取消", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
