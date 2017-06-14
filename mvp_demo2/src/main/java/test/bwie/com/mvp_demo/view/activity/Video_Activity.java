package test.bwie.com.mvp_demo.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import test.bwie.com.mvp_demo.R;
import test.bwie.com.mvp_demo.model.home.HomeBean;
import test.bwie.com.mvp_demo.presenter.MyDecoration;
import test.bwie.com.mvp_demo.presenter.VideoPresenter;
import test.bwie.com.mvp_demo.view.adapter.Video_ada;
import test.bwie.com.mvp_demo.view.iview.IVideoView;
import test.bwie.com.mvp_demo.view.iview.OnRecyclerViewItemClickListener;

public class Video_Activity extends AppCompatActivity implements IVideoView<HomeBean>  {

    private VideoPresenter videoPresenter;
    //private ListView video_lv;
    //private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    private Video_ada home_ada;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    swipeRefreshLayout.setRefreshing(false);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_);
        initView();
        initData();
    }

    private void initData() {
        videoPresenter = new VideoPresenter();
        videoPresenter.attachView(this);

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this,3);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new MyDecoration());
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        home_ada = new Video_ada(this);
        home_ada.setVideoPresenter(videoPresenter);
        home_ada.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText (Video_Activity.this, "你点击了第" + position + "个item" ,
                        Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText (Video_Activity.this, "你长按了第" + position + "个item" ,
                        Toast.LENGTH_SHORT).show ();
            }
        });
        recyclerView.setAdapter(home_ada);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("onScrollStateChanged", "ScrollStateChanged" );

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                Log.e("onScrolled", "Scrolled" );
                if (dy>0){
                    // LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();

                    int totalItemCount = manager.getItemCount();

                    int lastVisibleItem = manager.findLastVisibleItemPosition();
                    if (totalItemCount == lastVisibleItem+1){
                        Toast.makeText(Video_Activity.this, "上啦", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        videoPresenter.getVideoDataFromServer(HomeBean.class);
        /*homeAdapter = new HomeAdapter(this);
        homeAdapter.setHomePresenter(videoPresenter);
        video_lv.setAdapter(homeAdapter);*/
    }


    @Override
    public void callBackStr(final HomeBean string) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                home_ada.setData(string.getResult().getData());
                home_ada.notifyDataSetChanged();
                handler.sendEmptyMessageDelayed(1,3000);
            }
        });



       /* homeAdapter.setData(string.getResult().getData());
        homeAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void callBackerr(String string, int errCode) {

    }

    private void initView() {
        //video_lv = (ListView) findViewById(R.id.video_lv);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE);


    }
}
