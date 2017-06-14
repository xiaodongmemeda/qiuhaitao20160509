package test.bwie.com.demo_0614;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView main2_text_01;
    private Button main2_bt_01;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        main2_text_01.setText(user);

    }

    private void initView() {
        main2_text_01 = (TextView) findViewById(R.id.main2_text_01);
        main2_bt_01 = (Button) findViewById(R.id.main2_bt_01);

        main2_bt_01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main2_bt_01:
                Intent intent = new Intent();
                intent.putExtra("user",user+"   "+"onpause");
                setResult(200,intent);
                finish();
                break;
        }
    }
}
