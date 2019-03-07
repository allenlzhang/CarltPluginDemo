package com.carlt.pluginlibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Description:
 * Company    : carlt
 * Author     : zhanglei
 * Date       : 2019/3/2 14:10
 */
public class PluginActivity extends Activity implements IPlugin {
    private int      mFrom = FROM_INTERNAL;
    private Activity mActivity;
    public  Bundle   mBundle;

    @Override
    public void onAttach(Activity activity) {
        mActivity = activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFrom = savedInstanceState.getInt(Constant.FROM);
        }
        if (mFrom == FROM_INTERNAL) {
            super.onCreate(savedInstanceState);
            mActivity = this;
        }


    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(layoutResID);
        } else {
            mActivity.setContentView(layoutResID);

        }


    }

    @Override
    public void onStart() {
        if (mFrom == FROM_INTERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAL) {
            super.onResume();
        }

    }

    @Override
    public void onRestart() {
        if (mFrom == FROM_INTERNAL) {
            super.onRestart();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == FROM_INTERNAL) {
            super.onStop();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == FROM_INTERNAL) {
            super.onPause();
        }

    }

    @Override
    public void onDestroy() {
        if (mFrom == FROM_INTERNAL) {
            super.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mFrom == FROM_INTERNAL) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


