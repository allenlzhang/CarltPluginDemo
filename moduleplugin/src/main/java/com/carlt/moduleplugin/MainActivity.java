package com.carlt.moduleplugin;

import android.os.Bundle;

import com.carlt.pluginlibs.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
