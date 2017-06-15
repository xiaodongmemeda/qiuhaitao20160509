package test.bwie.com.demo_0615_zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnSan;
    private TextView tv_content;
    private EditText et_input;
    private Button btn_generate;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnSan = (Button) findViewById(R.id.btnSan);
        tv_content = (TextView) findViewById(R.id.tv_content);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_generate = (Button) findViewById(R.id.btn_generate);
        img = (ImageView) findViewById(R.id.img);

        btnSan.setOnClickListener(this);
        btn_generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSan:
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                break;
            case R.id.btn_generate:
                String str = et_input.getText().toString();
                if (str.equals("")) {
                    Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 位图
                    try {
                        /**
                         * 参数：1.文本 2 3.二维码的宽高 4.二维码中间的那个logo
                         */
                        Bitmap bitmap = EncodingUtils.createQRCode(str, 500, 500, null);
                        // 设置图片
                        img.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                break;
        }
    }

    private void submit() {
        // validate
        String input = et_input.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "请输入要生成的二维码文字", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            Log.d("Main", result);
            tv_content.setText(result);
        }
    }

}
