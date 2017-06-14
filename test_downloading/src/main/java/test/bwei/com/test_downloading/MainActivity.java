package test.bwei.com.test_downloading;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button downloading;
    private String u = "http://169.254.159.111:8080/ssm/user/download?fileName=app.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        downloading = (Button) findViewById(R.id.downloading);

        downloading.setOnClickListener(this);
    }
    public int getVersonCode() {
        int verCode = -1;
        try {
            //注意："com.example.try_downloadfile_progress"对应AndroidManifest.xml里的package="……"部分
            verCode = MainActivity.this.getPackageManager().getPackageInfo(
                    "com.bwie.android_lian0531_01", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg",e.getMessage());
        }
        return verCode;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.downloading:



                new AlertDialog.Builder(MainActivity.this).setTitle("系统提示")//设置对话框标题

                        .setMessage("是否下载更新版本！")//设置显示的内容

                        .setPositiveButton("下载更新",new DialogInterface.OnClickListener() {//添加确定按钮



                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                DownloadUtil.get().download(u, "download", new DownloadUtil.OnDownloadListener() {
                                    @Override
                                    public void onDownloadSuccess() {

                                    }
                                    @Override
                                    public void onDownloading(int progress) {
                                        //progressBar.setProgress(progress);
                                    }
                                    @Override
                                    public void onDownloadFailed() {

                                    }
                                });

                            }

                        }).setNegativeButton("取消返回",new DialogInterface.OnClickListener() {//添加返回按钮



                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件

                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        Log.i("alertdialog"," 请保存数据！");

                    }

                }).show();//在按键响应事件中显示此对话框



                break;
        }
    }



}
