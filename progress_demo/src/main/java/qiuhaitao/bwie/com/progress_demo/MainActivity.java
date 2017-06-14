package qiuhaitao.bwie.com.progress_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Progress progress_01;
    private  float i =0;
    private  float j = 188;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (i/j>1){
                        timer.cancel();
                        progress_01.invalidate();
                    }
                    progress_01.setIn_circle_width(i);
                    progress_01.setDrawText(i/j*100+"%");
                    progress_01.invalidate();
                    break;

                default:
                    break;
            }
        }
    };
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        progress_01 = (Progress) findViewById(R.id.progress_01);
        progress_01.setIn_circle_width(188);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                i++;
                handler.sendEmptyMessage(1);
            }
        },1000,1000);
        progress_01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress_01.x = event.getX();
                progress_01.y = event.getY();
                progress_01.invalidate();
                return true;
            }
        });
    }
}
