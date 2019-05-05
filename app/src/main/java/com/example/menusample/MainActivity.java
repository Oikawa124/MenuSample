package com.example.menusample;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // リストビューを表すフィールド
    private ListView _lvMenu;

    // リストビューに表示するリストデータ
    private List<Map<String, Object>> _menuList;

    // SimpleAdapterの第4引数に使用する定数フィールド
    private static final String[] FROM = {"name", "price"};

    // SimpleAdapterの第5引数に使用する定数フィールド
    private static final int[] TO = {R.id.tvmenuName, R.id.tvMenuPrice};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 画面部品ListViewを取得し、フィールドに格納
        _lvMenu = findViewById(R.id.lvMenu);

        // 定数フィールドListオブジェクトをpriveteメソッドを利用して用意し、フィールドの格納

        _menuList = createTeishokuList();

        // SimpleAdapterの生成

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList,
                R.layout.row, FROM, TO);

        // アダプタの登録
        _lvMenu.setAdapter(adapter);
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        // アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private List<Map<String, Object>> createTeishokuList() {
        // SimpleAdapterで使用するListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();

        // 定食のデータを格納する
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鳥の唐揚げにサラダ、ご飯とみそ汁がつきます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 940);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とみそ汁がつきます。");
        menuList.add(menu);

        return menuList;
    }

    private List<Map<String, Object>> createCurryList() {
        // SimpleAdapterで使用するListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();

        // 定食のデータを格納する
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 300);
        menu.put("desc", "特選スパイスをきかせました");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 940);
        menu.put("desc", "スパイスがきいています");
        menuList.add(menu);

        return menuList;
    }


    private class ListItemClickListener implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // タップされた行のデータを取得
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);

            // 定食と金額を取得

            String menuName = (String) item.get("name");
            Integer menuPrice = (Integer) item.get("price");


            // インテントオブジェクトを生成
            Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);

            // 第2画面に送るデータを格納
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice + "円");

            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        }

        switch (itemId) {

            case R.id.menuListOptionTeishoku:
                _menuList = createTeishokuList();
                break;
            case R.id.menuListOptionCurry:
                _menuList = createCurryList();
                break;
        }

        // SimpleAdapterの生成

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList,
                R.layout.row, FROM, TO);

        // アダプタの登録
        _lvMenu.setAdapter(adapter);

        //_lvMenu.setOnItemClickListener(new ListItemClickListener()); はonCreateですでに登録されている。

        return super.onOptionsItemSelected(item);
    }
}