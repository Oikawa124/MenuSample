package com.example.menusample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);


        // インテントオブジェクトの取得
        Intent intent = getIntent();

        // リスト画面から渡されたデータを取得
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");

        // 定食名と金額を表示させるtextviewを取得

        TextView tvMenuName = findViewById(R.id.tvmenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        // textViewに定食名と金額を表示

        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        // アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
