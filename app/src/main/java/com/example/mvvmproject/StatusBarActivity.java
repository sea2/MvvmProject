package com.example.mvvmproject;

import android.util.Log;
import android.view.View;

import com.example.mvvmproject.databinding.ActivityStatusBarBinding;
import com.gyf.immersionbar.ImmersionBar;
import com.sea.baselibrary.base.BaseActivity;

import androidx.lifecycle.Observer;

/**
 * Created by lhy on 2023/2/21.
 */

public class StatusBarActivity extends BaseActivity<ActivityStatusBarBinding,ViewPagerViewModel> {


   @Override
   protected int getLayoutId() {
      return R.layout.activity_status_bar;
   }

   @Override
   protected void afterCreate() {
      Log.e("123","1-------");
      ImmersionBar.with(this)
//                .transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
              .statusBarColor(R.color.white)     //状态栏颜色，不写默认透明色
              .navigationBarColor(R.color.white) //导航栏颜色，不写默认黑色
//                .barColor(R.color.white)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
              .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
//                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
//                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//                .autoNavigationBarDarkModeEnable(true,0.2f) //自动导航栏图标变色，必须指定导航栏颜色才可以自动变色哦
//                .flymeOSStatusBarFontColor(R.color.white)  //修改flyme OS状态栏字体颜色
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
              .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
//                .supportActionBar(true) //支持ActionBar使用
//                .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
//                .navigationBarWithEMUI3Enable(true) //是否可以修改emui3.x手机导航栏颜色，默认为true
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
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
   }


   public void jump(View v) {
   }


   public void trans(View v) {
      ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
              .init();  //必须调用方可应用以上所配置的参数
   }
   public void statue1(View v) {
      ImmersionBar.with(this)
              .statusBarDarkFont(true) //状态栏图标是深色，不写默认为亮色
              .init();  //必须调用方可应用以上所配置的参数
   }
   public void statue2(View v) {
      ImmersionBar.with(this)
              .statusBarDarkFont(false) //状态栏图标是深色，不写默认为亮色
              .init();  //必须调用方可应用以上所配置的参数
   }
   public void inok(View v) {   //进入状态栏
      ImmersionBar.with(this)
              .fitsSystemWindows(false) //导航栏图标是深色，不写默认为亮色
              .init();  //必须调用方可应用以上所配置的参数
   }
   public void inno(View v) {//不进入状态栏
      ImmersionBar.with(this)
              .fitsSystemWindows(true) //导航栏图标是深色，不写默认为亮色
              .init();  //必须调用方可应用以上所配置的参数
   }


}