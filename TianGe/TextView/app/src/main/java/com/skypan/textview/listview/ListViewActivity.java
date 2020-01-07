package com.skypan.textview.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.skypan.textview.R;

public class ListViewActivity extends Activity {

    private ListView mLv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mLv1 = (ListView) findViewById(R.id.lv_1);
        mLv1.setAdapter(new MyListAdapter(ListViewActivity.this));
        mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mLv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "长按了" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
