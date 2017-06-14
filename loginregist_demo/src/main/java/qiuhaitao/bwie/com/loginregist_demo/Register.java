package qiuhaitao.bwie.com.loginregist_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText register_user;
    private EditText register_password;
    private EditText register_password2;
    private EditText register_email;
    private Button register_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        register_user = (EditText) findViewById(R.id.register_user);
        register_password = (EditText) findViewById(R.id.register_password);
        register_password2 = (EditText) findViewById(R.id.register_password2);
        register_email = (EditText) findViewById(R.id.register_email);
        register_register = (Button) findViewById(R.id.register_register);

        register_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_register:
                getCode(register_user.getText().toString(),
                        register_password.getText().toString(),
                        register_password2.getText().toString(),
                        register_email.getText().toString());
                break;
        }
    }

    public void getCode(String user, String password, String password2, String email) {
        Observer<RegisterBean> observer = new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean value) {
                Log.d("memeda", "register" + value.getCode());
                if (value.getCode() == 200) {
                    Toast.makeText(Register.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("memeda", "register" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        HttpUtils.register(user, password, password2, email, "android", observer);
    }

    private void submit() {
        // validate
        String user = register_user.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password2 = register_password2.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = register_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
