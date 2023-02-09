package com.elapsefeather.griditemdecoration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.elapsefeather.griditemdecoration.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RecyclerView rv;
    List<String> menu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        for (int i = 0; i < 5; i++) {
            menu.add("star" + (i + 1));
        }
        binding.rv.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
//        rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(new Adapter(menu));
//        1.
//        GridItemDecoration itemDec = new GridItemDecoration(GridItemDecoration.ROUNDALL);
//        itemDec.setDividerColor(getColor(R.color.black));
//        2.
//        GridItemDecoration itemDec = new GridItemDecoration(GridItemDecoration.ROUNDALL, getColor(R.color.black));
//        3.
        GridItemDecoration itemDec = new GridItemDecoration.Builder()
                .orientation(GridItemDecoration.DecorationStyle.HORIZONTAL)
                .color(getColor(R.color.black))
                .size(5)
                .build();
        binding.rv.addItemDecoration(itemDec);
    }
}