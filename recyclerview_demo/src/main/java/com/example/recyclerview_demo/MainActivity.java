package com.example.recyclerview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView hello;
    private RecyclerView recy;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        for (int i = 0; i < 22; i++) {
            list.add("仇海涛"+"  "+i);
        }
        RecyAda ada = new RecyAda(this);
        ada.setData(list);
        recy.setAdapter(ada);

        ada.setOnClick(new RecyOnClick() {
            @Override
            public void onClick(View view, int poistion) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int poistion) {

            }
        });
    }

    private void initView() {
        hello = (TextView) findViewById(R.id.hello);
        recy = (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        recy.setLayoutManager(manager);
        recy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
}
