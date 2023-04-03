package com.sea.baselibrary.base.bean;

/**
 * Created by lhy on 2022/5/23.
 */

public class CommonImgAndTextBean {

    public CommonImgAndTextBean(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    private   int resId;
    private  String title;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
