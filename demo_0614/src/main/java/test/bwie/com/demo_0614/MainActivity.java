package test.bwie.com.demo_0614;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user;
    private EditText pwd;
    private Button bt;
    private String userString;
    private String pwdString;
    private TextView main_text_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        user = (EditText) findViewById(R.id.user);
        pwd = (EditText) findViewById(R.id.pwd);
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(this);
        main_text_01 = (TextView) findViewById(R.id.main_text_01);
        main_text_01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                submit();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200){
            main_text_01.setText(data.getStringExtra("user"));
        }

    }

    private void submit() {
        // validate
        userString = user.getText().toString().trim();
        pwdString = pwd.getText().toString().trim();
        if (TextUtils.isEmpty(userString)) {
            Toast.makeText(this, "输入用户名", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(pwdString)){
            Toast.makeText(this, "输入用密码", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("user", userString + "     " + pwdString);
            startActivityForResult(intent, 200);
        }



    }

}
