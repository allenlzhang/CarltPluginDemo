package com.carlt.carltplugindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.carlt.pluginlibs.Constant;
import com.carlt.pluginlibs.PluginManager;
import com.carlt.pluginlibs.ProxyActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载插件apk文件
        if (PluginManager.getInstance().getPluginApk() == null) {
            String path = copyAssetsFileToAppFiles("moduleplugin.apk");
            PluginManager.getInstance().init(this).loadApk(path);
        }


        findViewById(R.id.btnLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = copyAssetsFileToAppFiles("moduleplugin.apk");
                //                String apkPath = Environment.getExternalStorageDirectory().getPath() + "/yema/moduleplugin.apk";
                PluginManager.getInstance().loadApk(path);

            }
        });
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                intent.putExtra(Constant.CLASS_NAME, "com.carlt.moduleplugin.MainActivity");

                startActivity(intent);
            }
        });
    }

    private String copyAssetsFileToAppFiles(String fileName) {
        InputStream open = null;
        FileOutputStream fos = null;
        try {
            File dir = getCacheDir();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);

            if (!file.exists()) {

                boolean newFile = file.createNewFile();
                if (newFile) {
                    open = getAssets().open(fileName);
                    fos = new FileOutputStream(file);
                    byte[] buffer = new byte[open.available()];
                    int byteCount;
                    while ((byteCount = open.read(buffer)) != -1) {
                        fos.write(buffer, 0, byteCount);
                    }
                    fos.flush();
                    fos.close();
                    open.close();
                    Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
                    return file.getAbsolutePath();
                }

            } else {
                Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
                return file.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (open != null) {
                    open.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

