package com.example.mvvmproject;

import android.app.Application;

import com.sea.baselibrary.base.BaseViewModel;
import com.sea.baselibrary.base.SingleLiveEvent;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import rxhttp.RxHttp;

/**
 * 所有例子仅做参考,千万不要把它当成一种标准,毕竟主打的不是例子,业务场景繁多,理解如何使用才最重要。
 * Created by goldze on 2018/7/18.
 */

public class ViewPagerViewModel extends BaseViewModel {
    public ViewPagerViewModel(@NonNull Application application) {
        super(application);
    }


    public SingleLiveEvent<String> dataLiveEvent = new SingleLiveEvent<>();
    public void getData() {
       Disposable disposable=RxHttp.get("https://configure-server-test.xjlm.com/configure/find_recommend/banner_list")  //第一步, 通过get、postXxx、putXxx等方法，确定请求类型
                .add("key", "value")
                .toObservableString()
               .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {     //第三步, 订阅回调(此步骤同RxJava订阅观察者)
                    //请求成功
                dataLiveEvent.setValue(s);
                }, throwable -> {
                    //请求失败
                    dataLiveEvent.setValue("");
                });
    addSubscribe(disposable);
    }

    public void onDestroy() {
        onCleared();
    }


}
