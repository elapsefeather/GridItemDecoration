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
//        1.
//        GridItemDecoration itemDec = new GridItemDecoration(GridItemDecoration.ROUNDALL);
//        itemDec.setDividerColor(getColor(R.color.black));
//        2.
//        GridItemDecoration itemDec = new GridItemDecoration(GridItemDecoration.ROUNDALL, getColor(R.color.black));
//        3.
        GridItemDecoration itemDec = new GridItemDecoration.Builder()
                .orientation(GridItemDecoration.DecorationStyle.VERTICAL)
                .color(getColor(R.color.black))
                .size(5)
                .build();
        rv.addItemDecoration(itemDec);
    }
}