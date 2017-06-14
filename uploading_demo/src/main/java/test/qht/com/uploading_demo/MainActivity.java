package test.qht.com.uploading_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView helloWorld;
    private String uploadUrl = "http://169.254.159.111:8080/ssm/user/photoUpload";
    private String upUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + "/qiuhaitao.gif";
    private TextView helloWorld_;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        Log.e("myMessage", " upUrl= " + upUrl);

    }


    private void initView() {

        helloWorld = (TextView) findViewById(R.id.helloWorld);
        helloWorld.setOnClickListener(this);
        helloWorld_ = (TextView) findViewById(R.id.helloWorld_);
        helloWorld_.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.helloWorld:
                upLoadImage(uploadUrl, upUrl);
                Toast.makeText(MainActivity.this, "么么哒！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.helloWorld_:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadFile("qiuhaitao",upUrl);
                        upLoadImage_HttpUrlConnection("qiuhaitao", upUrl);
                        //Toast.makeText(MainActivity.this, "么么哒！", Toast.LENGTH_SHORT).show();
                    }
                }).start();

            default:
                break;
        }
    }

    public void upLoadImage_HttpUrlConnection(String fileName, String filePath) {
        HttpURLConnection connection = null;
        // boundary就是request头和上传文件内容的分隔符(可自定义任意一组字符串)
        String BOUNDARY = "--------------------------7e1533190472";
        // 用来标识payLoad+文件流的起始位置和终止位置(相当于一个协议,告诉你从哪开始,从哪结束)
        String preFix = ("\r\n" + BOUNDARY + "\r\n");
        try {
            URL url = new URL(uploadUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
            OutputStream outputstream = new DataOutputStream(connection.getOutputStream());
            File file = new File(filePath);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(preFix);
            stringBuffer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + ".gif" + "\"\r\n");
            stringBuffer.append("Content-Type: image/pjpeg" + "\r\n\r\n");
            outputstream.write(stringBuffer.toString().getBytes());

            // 获取文件流
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream inputStream = new DataInputStream(fileInputStream);

            // 每次上传文件的大小(文件会被拆成几份上传)
            int bytes = 0;
            // 计算上传进度
            float count = 0;
            // 获取文件总大小
            int fileSize = fileInputStream.available();
            // 每次上传的大小
            byte[] bufferOut = new byte[1024];
            // 上传文件
            while ((bytes = inputStream.read(bufferOut)) != -1) {
                // 上传文件(一份)
                outputstream.write(bufferOut, 0, bytes);
                // 计算当前已上传的大小
                count += bytes;
                //打印上传文件进度(已上传除以总大*100就是进度)
                Log.d("123q", "progress:" + (count / fileSize * 100) + "%");
            }

            // 关闭文件流
            inputStream.close();

            // 标识payLoad + 文件流的结尾位置
            outputstream.write(preFix.getBytes());

            // 至此上传代码完毕

            // 总结上传数据的流程：preFix + payLoad(标识服务器表单接收文件的格式) + 文件(以流的形式) + preFix
            // 文本与图片的不同,仅仅只在payLoad那一处的后缀的不同而已。

            // 输出所有数据到服务器
            outputstream.flush();

            // 关闭网络输出流
            outputstream.close();

            // 重新构造一个StringBuffer,用来存放从服务器获取到的数据
            stringBuffer = new StringBuffer();

            // 打开输入流 , 读取服务器返回的数据
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(connection.getInputStream()));

            String line;

            // 一行一行的读取服务器返回的数据
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }

            // 关闭输入流
            reader.close();

            // 打印服务器返回的数据
            Log.d("123q", "上传成功:" + stringBuffer.toString());


        } catch (Exception e) {
            Log.d("123q", "上传图片错误" + e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public void upLoadImage(String uri, String filePath) {
        RequestParams params = new RequestParams(uri);

        //params.setAutoRename(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file", new File(filePath)));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setMultipart(true);
        params.setRequestBody(body);


        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("memeda", "onSuccess: " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("error", "onSuccess: " + ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    /**
     * fileName 文件名(不带后缀)
     * filePath 文件的本地路径 (xxx / xx / test.jpg)
     */
        public void uploadFile(String fileName , String filePath) {

        HttpURLConnection conn = null;

        /// boundary就是request头和上传文件内容的分隔符(可自定义任意一组字符串)
        String BOUNDARY = "******";
        // 用来标识payLoad+文件流的起始位置和终止位置(相当于一个协议,告诉你从哪开始,从哪结束)
        String  preFix = ("\r\n--" + BOUNDARY + "--\r\n");

        try {
            // (HttpConst.uploadImage 上传到服务器的地址
            URL url = new URL(uploadUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方法
            conn.setRequestMethod("POST");
            // 设置header
            conn.setRequestProperty("Accept","*/*");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 获取写输入流
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 获取上传文件
            File file = new File(filePath);

            // 要上传的数据
            StringBuffer strBuf = new StringBuffer();

            // 标识payLoad + 文件流的起始位置
            strBuf.append(preFix);

            // 下面这三行代码,用来标识服务器表单接收文件的name和filename的格式
            // 在这里,我们是file和filename.后缀[后缀是必须的]。
            // 这里的fileName必须加个.jpg,因为后台会判断这个东西。
            // 这里的Content-Type的类型,必须与fileName的后缀一致。
            // 如果不太明白,可以问一下后台同事,反正这里的name和fileName得与后台协定！
            // 这里只要把.jpg改成.txt，把Content-Type改成上传文本的类型，就能上传txt文件了。
            strBuf.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName+".gif" + "\"\r\n");
            strBuf.append("Content-Type: image/pjpeg"  + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());

            // 获取文件流
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream inputStream = new DataInputStream(fileInputStream);
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            x.task().post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });

            // 每次上传文件的大小(文件会被拆成几份上传)
            int bytes = 0;
            // 计算上传进度
            float count = 0;
            // 获取文件总大小
            int fileSize = fileInputStream.available();
            // 每次上传的大小
            byte[] bufferOut = new byte[1024];
            // 上传文件
            while ((bytes = inputStream.read(bufferOut)) != -1) {
                // 上传文件(一份)
                out.write(bufferOut, 0, bytes);
                // 计算当前已上传的大小
                count += bytes;
                // 打印上传文件进度(已上传除以总大*100就是进度)
                Log.d("123q","progress:" +(count / fileSize * 100) +"%");
            }

            // 关闭文件流
            inputStream.close();

            // 标识payLoad + 文件流的结尾位置
            out.write(preFix.getBytes());

            // 至此上传代码完毕

            // 总结上传数据的流程：preFix + payLoad(标识服务器表单接收文件的格式) + 文件(以流的形式) + preFix
            // 文本与图片的不同,仅仅只在payLoad那一处的后缀的不同而已。

            // 输出所有数据到服务器
            out.flush();

            // 关闭网络输出流
            out.close();

            // 重新构造一个StringBuffer,用来存放从服务器获取到的数据
            strBuf = new StringBuffer();

            // 打开输入流 , 读取服务器返回的数据
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            String line;

            // 一行一行的读取服务器返回的数据
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }

            // 关闭输入流
            reader.close();

            // 打印服务器返回的数据
            Log.d("123q","上传成功:"+strBuf.toString());

        } catch (Exception e) {
            Log.d("123q","上传图片出错:"+e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

    }
}
