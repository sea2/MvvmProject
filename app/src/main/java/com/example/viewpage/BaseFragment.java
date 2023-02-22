package com.example.viewpage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.mvvmproject.R;
import com.sea.baselibrary.base.ClassUtil;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by lhy on 2023/2/22.
 */


public abstract class BaseFragment<SV extends ViewDataBinding,VM extends AndroidViewModel> extends LazyFragment {
    // ViewModel
    protected VM viewModel;
    // 布局view
    protected SV bindingView;


    // Activity
    public Activity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 布局初始化
        View mView = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(mActivity.getLayoutInflater(), setContent(), null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = mView.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());

        return mView;
    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化ViewModel
        initViewModel();
        // 初始布局相关
        initViewCreated();
    }
    /**
     * 布局
     */
    protected abstract int setContent();

    /**
     * 初始化
     */
    protected abstract void initViewCreated();


    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtil.getViewModel(this);
        if (viewModelClass != null) {
            this.viewModel = ViewModelProviders.of(this).get(viewModelClass);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(bindingView!=null){
            bindingView.unbind();
            bindingView=null;
        }
    }

}
