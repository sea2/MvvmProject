package com.sea.baselibrary.base;

import android.app.Application;
import android.content.Context;

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

        myApplication=this;
        RxHttpManager.init(this);

    }






}
