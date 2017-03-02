package com.slpcode.pipi.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.slpcode.zxinglib.activity.CaptureActivity;
import com.slpcode.zxinglib.encoding.EncodingUtils;

/**
 * Created by mofan on 2016/9/1.
 */

public class ZxingActivity extends AppCompatActivity {

    private TextView tv_result;
    private EditText edt_text;
    private ImageView img_result;
    private CheckBox cb_log;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        tv_result = (TextView) findViewById(R.id.tv_result);
        edt_text = (EditText) findViewById(R.id.edt_text);
        img_result = (ImageView) findViewById(R.id.img_result);
        cb_log = (CheckBox) findViewById(R.id.cb_log);
    }

    public void onScan(View v){
        startActivityForResult(new Intent(ZxingActivity.this, CaptureActivity.class),0);
    }

    public void onMackZxing(View v){
        String input = edt_text.getText().toString();
        if (input.equals("")){
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else{
            Bitmap bitmap = EncodingUtils.createQRCode(input,
                    888,888, cb_log.isChecked()?
                            BitmapFactory.decodeResource(getResources(),R.drawable.jelly_fish):
                            null);
            img_result.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            tv_result.setText(result);
        }
    }
}
