package com.example.viewpage;

import com.example.mvvmproject.R;
import com.example.mvvmproject.ViewPagerViewModel;
import com.example.mvvmproject.databinding.ActivityViewpage2Binding;
import com.sea.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Viewpage2Activity extends BaseActivity<ActivityViewpage2Binding, ViewPagerViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpage2;
    }

    @Override
    protected void afterCreate() {


        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance("", ""));
        fragments.add(ItemFragment.newInstance(2));

        bindingView.viewPage.setOffscreenPageLimit(1);
        MyFragmentAdapter adapter = new MyFragmentAdapter(this, fragments);
        bindingView.viewPage.setAdapter(adapter);


    }


    private class MyFragmentAdapter extends FragmentStateAdapter {
        private List<Fragment> fragments;

        public MyFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> list) {
            super(fragmentActivity);
            this.fragments = list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }


}