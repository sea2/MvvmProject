package com.sea.baselibrary.base;


import android.app.Application;
import android.os.Build;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.param.Method;
import rxhttp.wrapper.ssl.HttpsUtils;

/**
 * RxHttp初始化
 */
public class RxHttpManager {


    public static void init(Application context) {


        // 设置缓存策略: 可以配置全局缓存 设置缓存目录为：Android/data/{app包名目录}/cache/MagicShowCache
        File cacheDir = new File(context.getExternalCacheDir(), "XJCache");

        RxHttpPlugins.init(getUnsafeOkHttpClient())      //自定义OkHttpClient对象
                .setDebug(true)                //是否开启调试模式，开启后，logcat过滤RxHttp，即可看到整个请求流程日志
                .setOnParamAssembly(p -> {
                    Method method = p.getMethod();


                    if (method.isGet()) {     //可根据请求类型添加不同的参数
                    } else if (method.isPost()) {
                    }


                     p.add("version", AppUtils.getVersionName(BaseApplication.getInstance()))//添加公共参数
                            .add("platform","android")
                            .add("package_name",BaseApplication.getInstance().getPackageName())
                            .add("package_version",AppUtils.getVersionName(BaseApplication.getInstance()))
                            .add("package_build_no","30.0.0")
                            .add("os", Build.VERSION.SDK)
                            .add("channel",AppUtils.getManifestChannel(context)); //添加公共请求头
                });


    }


    public static OkHttpClient getUnsafeOkHttpClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }


}
