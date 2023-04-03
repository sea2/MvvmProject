package com.sea.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import rxhttp.wrapper.utils.Utils;

/**
 * Created by lhy on 2023/2/21.
 */

public class BaseApplication extends Application {


    private static BaseApplication myApplication;


    public static BaseApplication getInstance() {
        return myApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        setActivityLifecycleCallbacks();
        RxHttpManager.init(this);

    }


    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     */
    private void setActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }


}
