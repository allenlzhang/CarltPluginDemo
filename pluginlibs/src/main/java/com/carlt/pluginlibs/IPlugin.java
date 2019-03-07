package com.carlt.pluginlibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Description:
 * Company    : carlt
 * Author     : zhanglei
 * Date       : 2019/3/2 13:57
 */
public interface IPlugin {
    int FROM_INTERNAL = 0; //来自内部的跳转
    int FROM_EXTERNAL = 1;  //来自外部的跳转

    void onAttach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onRestart();

    void onStop();

    void onPause();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
