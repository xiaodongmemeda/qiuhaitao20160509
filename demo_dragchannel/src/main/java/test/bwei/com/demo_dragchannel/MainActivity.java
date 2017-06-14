package test.bwei.com.demo_dragchannel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView edit;
    private RecyclerView myrecyclerview;
    private RecyclerView otherrecyclerview;
    private List<ItemBean> mylist = new ArrayList<>();
    private List<ItemBean> otherlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initData() {
        for(int x=0; x<9 ; x++){
            ItemBean itemBean = new ItemBean();
            itemBean.setString("我的  "+(x+1));
            mylist.add(itemBean);
        }
        for(int x=0; x<9 ; x++){
            ItemBean itemBean = new ItemBean();
            itemBean.setString("其他  "+(x+1));
            otherlist.add(itemBean);
        }

        final RecyAda myada = new RecyAda(this);
        final RecyAda otherada = new RecyAda(this);
        myada.setData(mylist);
        myrecyclerview.setAdapter(myada);
        myada.setOnClick(new RecyclerOnClick() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();

                otherlist.add(mylist.get(position));
                otherada.setData(otherlist);

                mylist.remove(position);
                myada.setData(mylist);

            }
        });


        otherada.setData(otherlist);
        otherrecyclerview.setAdapter(otherada);
        otherada.setOnClick(new RecyclerOnClick() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();

                mylist.add(otherlist.get(position));
                myada.setData(mylist);

                otherlist.remove(position);
                otherada.setData(otherlist);

            }
        });

    }

    private void initView() {
        edit = (TextView) findViewById(R.id.edit);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this,3);
        myrecyclerview = (RecyclerView) findViewById(R.id.myrecyclerview);
        myrecyclerview.setLayoutManager(gridLayoutManager);

        otherrecyclerview = (RecyclerView) findViewById(R.id.otherrecyclerview);
        otherrecyclerview.setLayoutManager(gridLayoutManager1);

    }
}
