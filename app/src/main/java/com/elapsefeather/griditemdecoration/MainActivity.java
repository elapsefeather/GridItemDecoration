package com.elapsefeather.griditemdecoration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    List<String> menu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 5; i++) {
            menu.add("star" + (i + 1));
        }
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        rv.setAdapter(new Adapter(menu));
        GridItemDecoration itemDec = new GridItemDecoration(this, GridItemDecoration.ROUNDALL, getColor(R.color.black));
//        itemDec.setDividerColor(getColor(R.color.black));
        rv.addItemDecoration(itemDec);
    }
}