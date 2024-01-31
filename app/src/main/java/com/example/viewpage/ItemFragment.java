package com.example.viewpage;

import android.content.Context;
import android.os.Bundle;

import com.example.mvvmproject.R;
import com.example.mvvmproject.ViewPagerViewModel;
import com.example.mvvmproject.databinding.FragmentItemBinding;
import com.example.viewpage.placeholder.PlaceholderContent;
import com.sea.baselibrary.base.util.LogUtils;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends BaseFragment <FragmentItemBinding, ViewPagerViewModel> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }



    @Override
    protected int setContent() {
        return R.layout.fragment_item;
    }

    @Override
    protected void initViewCreated() {
        // Set the adapter
        if (bindingView.getRoot() instanceof RecyclerView) {
            Context context = bindingView.getRoot() .getContext();
            RecyclerView recyclerView = (RecyclerView) bindingView.getRoot() ;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }
    }
    @Override
    protected void lazyLoad() {
        LogUtils.logi("lazyLoad");
    }
}