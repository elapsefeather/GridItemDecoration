package com.elapsefeather.griditemdecoration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.elapsefeather.griditemdecoration.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Adapter adapter = new Adapter();
    GridItemDecoration itemDec;
    List<String> menu = new ArrayList<>();
    List<String> menu2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        for (int i = 0; i < 35; i++) {
            if (i < 5)
                menu.add("star" + (i + 1));
            menu2.add("star" + (i + 1));
        }
        binding.rv.setAdapter(adapter);
        setExp();

        binding.check.setOnClickListener(v -> {
            setExp();
        });
    }

    public void setExp() {
        if (itemDec != null)
            binding.rv.removeItemDecoration(itemDec);
//        1.
//        GridItemDecoration itemDec = new GridItemDecoration(GridItemDecoration.ROUNDALL);
//        itemDec.setDividerColor(getColor(R.color.black));
//        2.
//        GridItemDecoration itemDec = new GridItemDecoration(GridItemDecoration.ROUNDALL, getColor(R.color.black));
//        3.
        int mOrientation = -1;
        switch (binding.line.getCheckedRadioButtonId()) {
            default:
            case R.id.insideAll:
                mOrientation = (binding.rounded.isChecked()) ?
                        GridItemDecoration.DecorationStyle.ROUNDALL : GridItemDecoration.DecorationStyle.INSIDEALL;
                break;
            case R.id.horizontal:
                mOrientation = (binding.rounded.isChecked()) ?
                        GridItemDecoration.DecorationStyle.OVERHORIZONTAL : GridItemDecoration.DecorationStyle.HORIZONTAL;
                break;
            case R.id.vertical:
                mOrientation = (binding.rounded.isChecked()) ?
                        GridItemDecoration.DecorationStyle.OVERVERTICAL : GridItemDecoration.DecorationStyle.VERTICAL;
                break;
        }
        if (mOrientation != -1)
            itemDec = new GridItemDecoration.Builder()
                    .orientation(mOrientation)
                    .color(getColor(R.color.black))
                    .size(5)
                    .build();
        if (itemDec != null)
            binding.rv.addItemDecoration(itemDec);

        switch (binding.layoutMgr.getCheckedRadioButtonId()) {
            default:
            case R.id.gridMgr:
                binding.rv.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
                break;
            case R.id.verticalMgr:
                binding.rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                break;
            case R.id.horizontalMgr:
                binding.rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                break;
        }

        switch (binding.expCount.getCheckedRadioButtonId()) {
            default:
            case R.id.less:
                adapter.setList(menu);
                break;
            case R.id.more:
                adapter.setList(menu2);
                break;
        }
    }
}