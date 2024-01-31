package com.sea.baselibrary.base.view;

import android.content.Context;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.sea.baselibrary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by lhy on 2023/4/24.
 */

  public class CustomPopup extends BottomPopupView {
      RecyclerView recyclerView;
      public CustomPopup(@NonNull Context context) {
         super(context);
      }
      @Override
      protected int getImplLayoutId() {
         return R.layout.custom_bottom_popup;
      }

      @Override
      protected void onCreate() {
         super.onCreate();
         recyclerView = findViewById(R.id.recyclerView);

         ArrayList<String> strings = new ArrayList<>();
         for (int i = 0; i < 30; i++) {
            strings.add("");
         }

      }
      // 最大高度为Window的0.85
      @Override
      protected int getMaxHeight() {
         return (int) (XPopupUtils.getAppHeight(getContext())*.85f);
      }
   }