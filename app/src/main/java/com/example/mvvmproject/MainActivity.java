package com.example.mvvmproject;

import android.util.Log;
import android.view.View;

import com.example.mvvmproject.databinding.ActivityMainBinding;
import com.example.viewpage.Viewpage2Activity;
import com.gyf.immersionbar.ImmersionBar;
import com.sea.baselibrary.base.BaseActivity;
import com.sea.baselibrary.base.view.CommonBottomDialog;

import androidx.lifecycle.Observer;

public class MainActivity extends BaseActivity<ActivityMainBinding,ViewPagerViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate() {
        Log.e("123","1-------");
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)     //状态栏颜色，不写默认透明色
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.white) //导航栏颜色，不写默认黑色
                .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
                .fitsSystemWindows(true)
                .init();  //必须调用方可应用以上所配置的参数
         viewModel.dataLiveEvent.observe(this, new Observer<String>() {
             @Override
             public void onChanged(String s) {
                 Log.e("123","4-------");
                 bindingView.setDataBean(s);
                 bindingView.executePendingBindings();
                 Log.e("123","5-------");
             }
         });
        Log.e("123","2-------");
        viewModel.getData();
        Log.e("123","3-------");


        CommonBottomDialog mCommonBottomDialog=new CommonBottomDialog(MainActivity.this, null, new CommonBottomDialog.ItemListener() {
            @Override
            public void callBack(int position) {

            }
        });
        mCommonBottomDialog.show();



    }


    public void jump(View v) {
        startActivity(StatusBarActivity.class);
    }
    public void Viewpage2Activity(View v) {
        startActivity(Viewpage2Activity.class);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}