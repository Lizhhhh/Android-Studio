package com.skypan.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class ButtonActivity extends AppCompatActivity {

    private Button mBtn3;
    private TextView mTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        // btn_3 的点击事件
        mBtn3 = (Button) findViewById(R.id.btn_3);
        mBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ButtonActivity.this,"btn3被点击",Toast.LENGTH_SHORT).show();
            }
        });

        // tv_1 的点击事件
        mTv1 = (TextView) findViewById(R.id.tv_1);
        mTv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ButtonActivity.this,"TextView被点击",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void showToast(View view){
        Toast.makeText(this,"btn4被点击",Toast.LENGTH_SHORT).show();
    }
}
