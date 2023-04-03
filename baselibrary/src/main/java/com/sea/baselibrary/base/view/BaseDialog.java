package com.sea.baselibrary.base.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;

import com.sea.baselibrary.R;
import com.sea.baselibrary.base.manager.DialogStackManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialog;

/**
 * Created by lhy on 2022/5/16.
 */

public class BaseDialog extends AppCompatDialog {

    Dialog dialog_loading;

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    // 加载dialog请求网络用
    public void show_loading_dialog(Context context) {
        if (dialog_loading == null) {
            dialog_loading = new Dialog(context, R.style.Load_Dialog_Style);
            dialog_loading.setContentView(R.layout.view_load_dialog_layout);
        }
        dialog_loading.setCanceledOnTouchOutside(true);
        dialog_loading.setCancelable(true);
        dialog_loading.show();
    }


    // 加载dialog
    public void dismiss_loading_dialog() {
        if (dialog_loading != null && dialog_loading.isShowing()) {
            dialog_loading.dismiss();
        }
    }


    @Override
    public void show() {
        DialogStackManager.getInstance().addDialog(this);
        try {
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        DialogStackManager.getInstance().removeDialog(this);
        dismiss_loading_dialog();
        super.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setWhiteNavigationBar(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(Color.WHITE);

            Drawable[] layers = {dimDrawable, navigationBarDrawable};

            LayerDrawable windowBackground = new LayerDrawable(layers);
            windowBackground.setLayerInsetTop(1, metrics.heightPixels);

            window.setBackgroundDrawable(windowBackground);
        }
    }



}
