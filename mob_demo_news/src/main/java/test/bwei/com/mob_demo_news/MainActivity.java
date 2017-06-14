package test.bwei.com.mob_demo_news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.mob.tools.utils.ResHelper.getStringRes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText phone_edit_n;
    private TextView send_phone;
    private EditText phone_edit_y;
    private TextView phone_text_enter;

    private String iPhone;
    private String iCord;
    private int time = 60;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SMSSDK.initSDK(this, "1cf90ed6a74cd", "61737dfe855cbae4edc42ec4413d384d");
        initView();
        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
    }

    private void initView() {
        phone_edit_n = (EditText) findViewById(R.id.phone_edit_n);
        send_phone = (TextView) findViewById(R.id.send_phone);
        phone_edit_y = (EditText) findViewById(R.id.phone_edit_y);
        phone_text_enter = (TextView) findViewById(R.id.phone_text_enter);

        send_phone.setOnClickListener(this);
        phone_text_enter.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String n = phone_edit_n.getText().toString().trim();
        if (TextUtils.isEmpty(n)) {
            Toast.makeText(this, "手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String y = phone_edit_y.getText().toString().trim();
        if (TextUtils.isEmpty(y)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_phone:
                if(!TextUtils.isEmpty(phone_edit_n.getText().toString().trim())){
                    if(phone_edit_n.getText().toString().trim().length()==11){
                        iPhone = phone_edit_n.getText().toString().trim();
                        SMSSDK.getVerificationCode("86",iPhone);
                        phone_edit_y.requestFocus();
                        send_phone.setEnabled(false);
                    }else{
                        Toast.makeText(MainActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                        phone_edit_n.requestFocus();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
                    phone_edit_n.requestFocus();
                }
                break;
            case R.id.phone_text_enter:
                if(!TextUtils.isEmpty(phone_edit_y.getText().toString().trim())){
                    if(phone_edit_y.getText().toString().trim().length()==4){
                        iCord = phone_edit_y.getText().toString().trim();
                        SMSSDK.submitVerificationCode("86", iPhone, iCord);
                        flag = false;
                    }else{
                        Toast.makeText(MainActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                        phone_edit_y.requestFocus();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                    phone_edit_y.requestFocus();
                }
                break;

            default:
                break;
        }
    }
    //验证码送成功后提示文字
    private void reminderText() {

        handlerText.sendEmptyMessageDelayed(1, 1000);
    }
    Handler handlerText =new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(time>0){
                    send_phone.setText("验证码已发送"+time+"秒");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                }else{
                    send_phone.setText("发送验证码");
                    time = 60;

                    send_phone.setEnabled(true);
                }
            }else{
                phone_edit_n.setText("");
                phone_edit_y.setText("");
                send_phone.setEnabled(true);
                send_phone.setText("发送验证码");
                time = 60;


            }
        };
    };


    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
                    handlerText.sendEmptyMessage(2);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(flag){
                    send_phone.setEnabled(true);
                    Toast.makeText(MainActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    phone_edit_n.requestFocus();
                }else{
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(MainActivity.this, "smssdk_network_error");
                    Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    phone_edit_y.selectAll();
                    if (resId > 0) {
                        Toast.makeText(MainActivity.this, resId, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

}
