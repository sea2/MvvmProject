package com.example.mvvmproject;

import android.widget.Toast;

import com.example.mvvmproject.databinding.ActivityMain2Binding;
import com.sea.baselibrary.base.BaseActivity;
import com.sea.baselibrary.base.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.lifecycle.Observer;

public class MainActivity2 extends BaseActivity<ActivityMain2Binding, ViewPagerViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void afterCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        viewModel.dataLiveEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity2.this, "mai2", Toast.LENGTH_SHORT).show();
                bindingView.setDataBean(s);
                bindingView.executePendingBindings();
            }
        });
        viewModel.getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateMessageEvent(MessageEvent messageEvent) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }




}