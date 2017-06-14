package test.bwie.com.mvp_demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import test.bwie.com.mvp_demo.R;
import test.bwie.com.mvp_demo.model.home.HomeBean;
import test.bwie.com.mvp_demo.presenter.HomePresenter;
import test.bwie.com.mvp_demo.view.adapter.HomeAdapter;
import test.bwie.com.mvp_demo.view.iview.IHomeView;

public class MainActivity extends AppCompatActivity implements IHomeView<HomeBean> {

    private ListView lv;
    private HomePresenter homePresenter;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        homePresenter = new HomePresenter();
        homePresenter.attachView(this);
        homeAdapter = new HomeAdapter(this);
        homeAdapter.setHomePresenter(homePresenter);
        lv.setAdapter(homeAdapter);
        homePresenter.getDataFromServer();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Second.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void callBackStr(HomeBean s) {
        homeAdapter.setData(s.getData());
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void callBackError(String errorMsg, int error_code) {

    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }
}
