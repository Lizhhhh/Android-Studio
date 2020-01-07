package com.skypan.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skypan.textview.listview.ListViewActivity;

public class MainActivity extends AppCompatActivity {

    // 声明button
    private Button mBtnTextView ;
    private Button mBtnButton ;
    private Button mBtnEditText;
    private Button mBtnRadioButton;
    private Button mBtnCheckBox;
    private Button mBtnImageView;
    private Button mBtnListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到按钮 btn_textview
        mBtnTextView = (Button) findViewById(R.id.btn_textview);
        // 找到按钮 btn_button
        mBtnButton = (Button) findViewById(R.id.btn_button);
        // 找到按钮 btn_edittext
        mBtnEditText = (Button) findViewById(R.id.btn_edittext);
        // 找到按钮 btn_radiobutton
        mBtnRadioButton = (Button) findViewById(R.id.btn_radiobutton);
        // 找到按钮 btn_checkbox
        mBtnCheckBox = (Button) findViewById(R.id.btn_checkbox);
        // 找到按钮 btn_imageview
        mBtnImageView = (Button) findViewById(R.id.btn_imageview);
        // 找到按钮 btn_listview
        mBtnListView = (Button) findViewById(R.id.btn_listview);
        setListeners();

    }

    private void setListeners(){
        OnClick onClick = new OnClick();
        mBtnTextView.setOnClickListener(onClick);
        mBtnButton.setOnClickListener(onClick);
        mBtnEditText.setOnClickListener(onClick);
        mBtnRadioButton.setOnClickListener(onClick);
        mBtnCheckBox.setOnClickListener(onClick);
        mBtnImageView.setOnClickListener(onClick);
        mBtnListView.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_textview:
                    // 跳转到 TextView 演示界面
                    intent = new Intent(MainActivity.this, TextViewActivity.class);
                    break;
                case R.id.btn_button:
                    intent = new Intent(MainActivity.this, ButtonActivity.class);
                    break;
                case R.id.btn_edittext:
                    intent = new Intent(MainActivity.this, EditTextActivity.class);
                    break;
                case R.id.btn_radiobutton:
                    intent = new Intent(MainActivity.this, RadioButtonActivity.class);
                    break;
                case R.id.btn_checkbox:
                    intent = new Intent(MainActivity.this,CheckBoxActivity.class);
                    break;
                case R.id.btn_imageview:
                    intent = new Intent(MainActivity.this,ImageViewActivity.class);
                    break;
                case R.id.btn_listview:
                    intent = new Intent(MainActivity.this, ListViewActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
