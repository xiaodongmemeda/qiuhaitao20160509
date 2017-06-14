package test.bwie.com.listview_scrollview_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mob.MobSDK;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import static com.mob.tools.utils.ResHelper.getStringRes;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText phone_edit_number;
    private EditText phone_edit_proofness;
    private Button send_phone;
    private Button login;

    private String iPhone;
    private String iCord;
    private int time = 60;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSDK.init(this, "1cf90ed6a74cd", "61737dfe855cbae4edc42ec4413d384d");

        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler_mob.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
        initView();

    }


    private void initView() {
        phone_edit_number = (EditText) findViewById(R.id.phone_edit_number);
        phone_edit_proofness = (EditText) findViewById(R.id.phone_edit_proofness);
        send_phone = (Button) findViewById(R.id.send_phone);
        login = (Button) findViewById(R.id.login);

        send_phone.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_phone:
                if(!TextUtils.isEmpty(phone_edit_number.getText().toString().trim())){
                    if(phone_edit_number.getText().toString().trim().length()==11){
                        iPhone = phone_edit_number.getText().toString().trim();
                        SMSSDK.getVerificationCode("86",iPhone);
                        phone_edit_proofness.requestFocus();
                        send_phone.setEnabled(false);
                    }else{
                        Toast.makeText(MainActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                        phone_edit_number.requestFocus();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
                    phone_edit_number.requestFocus();
                }
                break;
            case R.id.login:
                if(!TextUtils.isEmpty(phone_edit_proofness.getText().toString().trim())){
                    if(phone_edit_proofness.getText().toString().trim().length()==4){
                        iCord = phone_edit_proofness.getText().toString().trim();
                        SMSSDK.submitVerificationCode("86", iPhone, iCord);
                        flag = false;
                    }else{
                        Toast.makeText(MainActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                        phone_edit_proofness.requestFocus();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                    phone_edit_proofness .requestFocus();
                }
                break;
        }
    }

    private void submit() {
        // validate
        String number = phone_edit_number.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String proofness = phone_edit_proofness.getText().toString().trim();
        if (TextUtils.isEmpty(proofness)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

    }
    //验证码送成功后提示文字
    private void reminderText() {

        handlerText.sendEmptyMessageDelayed(1, 1000);
    }
    Handler handlerText =new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(time>0){
                        send_phone.setText("验证码已发送"+time+"秒");
                        time--;
                        handlerText.sendEmptyMessageDelayed(1, 1000);
                    }else{

                        send_phone.setText("发送验证码");
                        time = 60;

                        send_phone.setEnabled(true);
                    }
                    break;
                case 2:

                    phone_edit_number.setText("");
                    phone_edit_proofness.setText("");
                    send_phone.setEnabled(true);

                    break;
                default:
                    break;
            }
        };
    };


    Handler handler_mob =new Handler(){

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

                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                    reminderText();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(flag){
                    send_phone.setEnabled(true);
                    Toast.makeText(MainActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    phone_edit_number.requestFocus();
                }else{
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(MainActivity.this, "smssdk_network_error");
                    Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    phone_edit_proofness.selectAll();
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
