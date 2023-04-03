package com.sea.baselibrary.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sea.baselibrary.R;
import com.sea.baselibrary.base.bean.CommonImgAndTextBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by lhy on 2022/5/23.
 */

public class CommonBottomHorizontalDialog extends Dialog {


    private Activity mActivity;
    CommonBottomDialog.ItemListener itemListener;

    List<CommonImgAndTextBean> list;
    public interface ItemListener{
        //1 保存  2不保存
        void callBack(int position);
    }


    public CommonBottomHorizontalDialog(Activity activity, List<CommonImgAndTextBean> list, CommonBottomDialog.ItemListener itemListener  ) {
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
    }

    // 设置窗口显示
    public void initWindow() {
        Window window = getWindow(); // 得到对话框
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.dimAmount = 0.2f;
        wl.gravity = Gravity.BOTTOM; // 设置重力
        wl.width = mActivity.getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(wl);
    }


    private void initView() {

        TextView tvCancel = findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(v -> dismiss());

        RecyclerView recyclerView = findViewById(R.id.rv_common_bottom);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mActivity); //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
//        MyAdapter myAdapter=new MyAdapter(list);
//        recyclerView.setAdapter(myAdapter);
//        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if(itemListener!=null){
//                    itemListener.callBack(position);
//                }
//                dismiss();
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