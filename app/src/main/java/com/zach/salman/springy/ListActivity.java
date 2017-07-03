package com.zach.salman.springy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<String> titleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        makeList();
        mAdapter = new Adapter(titleList,recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    private void makeList(){
        for (int i = 0; i <99 ; i++) {
              int count = i+1;
             titleList.add("Title" +count);
        }
    }

}
