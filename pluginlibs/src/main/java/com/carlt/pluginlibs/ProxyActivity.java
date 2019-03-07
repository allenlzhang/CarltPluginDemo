package com.carlt.pluginlibs;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Description: 代理activity，管理插件activity的生命周期
 * Company    : carlt
 * Author     : zhanglei
 * Date       : 2019/3/2 14:28
 */
public class ProxyActivity extends Activity {
    private String    mClassName;
    private PluginApk mPluginApk;
    private IPlugin   mIPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra(Constant.CLASS_NAME);
        mPluginApk = PluginManager.getInstance().getPluginApk();

        lauchPluginApk();
    }

    private void lauchPluginApk() {
        if (mPluginApk == null) {
            //            throw new RuntimeException("请先加载apk");
            Toast.makeText(this, "请先加载apk", Toast.LENGTH_SHORT).show();
        }
        try {
            Class<?> loadClass = mPluginApk.mDexClassLoader.loadClass(mClassName);
            Log.e("====>", loadClass.getName() + "");
            Object instance = loadClass.newInstance();

            if (instance instanceof IPlugin) {
                mIPlugin = (IPlugin) instance;
                mIPlugin.onAttach(this);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.FROM, IPlugin.FROM_EXTERNAL);
                mIPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk == null ? super.getResources() : mPluginApk.mResources;
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk == null ? super.getAssets() : mPluginApk.mAssetManager;
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk == null ? super.getClassLoader() : mPluginApk.mDexClassLoader;
    }
}
