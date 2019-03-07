package com.carlt.pluginlibs;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Description:
 * Company    : carlt
 * Author     : zhanglei
 * Date       : 2019/3/2 13:29
 */
public class PluginManager {
    private static final PluginManager instance = new PluginManager();
    private              Context       mContext;
    private              PluginApk     mPluginApk;


    private PluginManager() {

    }

    public static PluginManager getInstance() {
        return instance;
    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }

    public PluginManager init(Context context) {
        mContext = context.getApplicationContext();
        return this;
    }

    /**
     * 加载apk文件
     * @param apkPath
     */
    public void loadApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES
                | PackageManager.GET_PROVIDERS | PackageManager.GET_RECEIVERS);
        if (packageInfo == null) {
            return;
        }
        AssetManager manager = createAssetMangager(apkPath);
        Resources resources = createResources(manager);
        DexClassLoader loader = createClassLoader(apkPath);
        mPluginApk = new PluginApk(packageInfo, resources, loader);
    }


    private AssetManager createAssetMangager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(am, apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createClassLoader(String apkPath) {

        File apkFile = new File(apkPath);

        return new DexClassLoader(apkPath, apkFile.getAbsolutePath(), null, mContext.getClassLoader());
    }

    private Resources createResources(AssetManager am) {
        Resources res = mContext.getResources();

        return new Resources(am, res.getDisplayMetrics(), res.getConfiguration());
    }
}
