package test.bwei.com.eventbus_demo.view.activityy;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import test.bwei.com.eventbus_demo.R;
import test.bwei.com.eventbus_demo.model.SecondBean;
import test.bwei.com.eventbus_demo.presenter.Second_presenter;
import test.bwei.com.eventbus_demo.view.adapter.Second_ada;
import test.bwei.com.eventbus_demo.view.iview.OnRecyclerViewItemClickListener;
import test.bwei.com.eventbus_demo.view.iview.Second_Iview;

public class Second_Activity extends AppCompatActivity implements  Second_Iview<SecondBean> {

    private Button second_bt;
    private Second_ada ada;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout swiperefreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        initView();
        initData();

    }

    private void initData() {
        Second_presenter presenter = new Second_presenter();
        presenter.attachView(this);
        ada = new Second_ada(this);

        presenter.getDataFromServer(SecondBean.class);
        recyclerview.setAdapter(ada);

        ada.setClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Second_Activity.this, "点击了" + position + "   @", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(Second_Activity.this, "长按了" + position + "   @", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        second_bt = (Button) findViewById(R.id.second_bt);
        second_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post("hello --- HELLO_SECOND !");
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(linearLayoutManager);

        swiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);

    }



    @Override
    public void callBackDataFromP(SecondBean bean) {

        ada.setData(bean.getResult().getData());
        ada.notifyDataSetChanged();

    }
}
