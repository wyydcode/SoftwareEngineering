package com.example.softwareengineering;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.instance = this;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationTestClass());
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 系统内存低时调用，释放一些不必要的资源
        releaseMemoryResources();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 根据内存状态释放资源
        handleTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 仅在模拟器中调用，执行清理操作
        cleanupResources();
    }

    private void initializeLibraries() {
        // 初始化第三方库
        // 例如：初始化网络库、数据库库等
        // Retrofit.initialize(this);
        // Realm.init(this);
    }

    private void releaseMemoryResources() {
        // 释放内存资源
        // 例如：清空缓存、关闭不必要的数据库连接等
    }

    private void handleTrimMemory(int level) {
        // 根据内存状态执行操作
        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            // 当内存处于危机状态时，释放一些资源
            releaseMemoryResources();
        } else if (level >= TRIM_MEMORY_MODERATE) {
            // 在中等内存情况下，可以进行更少的释放操作
        }
    }

    private void cleanupResources() {
        // 执行资源清理操作
        // 例如：关闭数据库连接、保存数据等
    }
}
