package com.sea.baselibrary.base.manager;

import android.app.Dialog;

import com.sea.baselibrary.base.util.StringUtils;

import java.util.Stack;

/**
 * Created by lhy on 2019/3/26.
 */
public class DialogStackManager {

    private Stack<Dialog> activityStack = new Stack<>();
    private static DialogStackManager INSTANCE;


    public static DialogStackManager getInstance() {
        if (INSTANCE == null) INSTANCE = new DialogStackManager();
        return INSTANCE;
    }


    /**
     * @param dialog dialog
     */
    public void addDialog(Dialog dialog) {
        activityStack.add(dialog);
    }

    /**
     * @param dialog 需要从栈管理中删除的dialog
     * @return
     */
    public boolean removeDialog(Dialog dialog) {
        return activityStack.remove(dialog);
    }

    /**
     * @param dialog 查询指定dialog在栈中的位置，从栈顶开始
     * @return
     */
    public int searchDialog(Dialog dialog) {
        return activityStack.search(dialog);
    }

    /**
     * @param dialog 将指定的activity从栈中删除然后dismiss()掉
     */
    public void finishDialog(Dialog dialog) {
        for (int i = 0; i < activityStack.size(); i++) {
          if(activityStack.get(i).getClass()==dialog.getClass()){
              activityStack.pop().dismiss();
          }
        }

    }


    /**
     * 销毁所有的activity
     */
    public void finishAllDialog() {
        while (activityStack != null && !activityStack.isEmpty()) {
            activityStack.pop().dismiss();
        }
    }


    /**
     * 获取栈顶的activity
     *
     * @return
     */
    public Dialog currentActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            return activityStack.lastElement();
        } else {
            return null;
        }
    }


    /**
     * @param classDialog 将指定的dialog从栈中删除然后dismiss()掉
     */
    public void finishDialog(Class classDialog) {
        for (int i = 0; i < activityStack.size(); i++) {
            if(StringUtils.isEquals(activityStack.get(i).getClass().getSimpleName(),classDialog.getSimpleName())){
                activityStack.get(i).dismiss();
            }
        }

    }



    public boolean findOneNameContain(String className) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < activityStack.size(); i++) {
            sb.append(activityStack.get(i).getClass().getSimpleName()).append("--");
        }
        return StringUtils.isContains(sb.toString(), className);
    }


}
