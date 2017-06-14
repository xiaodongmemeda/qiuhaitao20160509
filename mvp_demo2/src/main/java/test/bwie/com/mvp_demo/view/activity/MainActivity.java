package test.bwie.com.mvp_demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import test.bwie.com.mvp_demo.R;
import test.bwie.com.mvp_demo.model.home.HomeBean;
import test.bwie.com.mvp_demo.presenter.HomePresenter;
import test.bwie.com.mvp_demo.view.adapter.HomeAdapter;
import test.bwie.com.mvp_demo.view.iview.IHomeView;

public class MainActivity extends AppCompatActivity implements IHomeView<HomeBean> {

    private HomePresenter presenter;
    private TextView main_text;
    private ListView main_lv;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initeData();
    }

    private void initeData() {
        presenter = new HomePresenter();
        presenter.attachView(this);
        adapter = new HomeAdapter(this);
        adapter.setHomePresenter(presenter);
        main_lv.setAdapter(adapter);
        presenter.getDataFromServer(HomeBean.class);
    }

    @Override
    public void callBackStr(HomeBean string) {
        adapter.setData(string.getResult().getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void callBackerr(String string, int errCode) {

    }

    private void initView() {
        main_text = (TextView) findViewById(R.id.main_text);
        main_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Video_Activity.class);
                startActivity(intent);
            }
        });
        main_lv = (ListView) findViewById(R.id.main_lv);

    }
}
