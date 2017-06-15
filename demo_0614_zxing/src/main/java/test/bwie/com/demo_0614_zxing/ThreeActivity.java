package test.bwie.com.demo_0614_zxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_content;
    private Button button_content;
    private Button button1_content;
    private ImageView image_content;
    private Bitmap mBitmap;
    private String textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initView();
    }

    private void initView() {
        edit_content = (EditText) findViewById(R.id.edit_content);
        button_content = (Button) findViewById(R.id.button_content);
        button1_content = (Button) findViewById(R.id.button1_content);
        image_content = (ImageView) findViewById(R.id.image_content);

        button_content.setOnClickListener(this);
        button1_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_content:
                textContent = edit_content.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(ThreeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                edit_content.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                image_content.setImageBitmap(mBitmap);
                break;
            case R.id.button1_content:
                 textContent = edit_content.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(ThreeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                edit_content.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                image_content.setImageBitmap(mBitmap);
                break;
        }
    }

    private void submit() {
        // validate
        String content = edit_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "content不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
