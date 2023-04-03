package com.sea.baselibrary.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.sea.baselibrary.R;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/** 通用下弹出dialog
 * Created by lhy on 2022/4/13.
 */

public class CommonBottomDialog extends Dialog {


    private Activity mActivity;
    ItemListener itemListener;

    List<String> list;
    public interface ItemListener{
        //1 保存  2不保存
         void callBack(int position);
    }


    public CommonBottomDialog(Activity activity, List<String> list,ItemListener itemListener  ) {
        super(activity, R.style.AppTheme_BottomDialog);
        this.mActivity = activity;
        this.list = list;
        this.itemListener = itemListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_save_publish_confirm);
        initWindow();
        outSideEnAble();
        initView();

        ImmersionBar.with(mActivity,this)
                .statusBarColor(R.color.library_white)     //状态栏颜色，不写默认透明色
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.library_white) //导航栏颜色，不写默认黑色
                .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
                .fitsSystemWindows(true)
                .init();  //必须调用方可应用以上所配置的参数
    }

    // 设置窗口显示
    public void initWindow() {
        Window window = getWindow(); // 得到对话框
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.gravity = Gravity.BOTTOM; // 设置重力
        wl.width = mActivity.getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(wl);
    }


    private void initView() {

        TextView tvCancel = findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(v -> dismiss());

        RecyclerView recyclerView = findViewById(R.id.rv_common_bottom);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        MyAdapter myAdapter=new MyAdapter(list);
//        recyclerView.setAdapter(myAdapter);
//        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                 if(itemListener!=null){
//                     itemListener.callBack(position);
//                 }
//                 dismiss();
//            }
//        });



    }


    public void outSideEnAble() {
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }



    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }





}
