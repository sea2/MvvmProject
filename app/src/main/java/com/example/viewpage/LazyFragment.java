package com.example.viewpage;

import androidx.fragment.app.Fragment;

/**
 * Created by lhy on 2023/2/22.
 */

public abstract  class LazyFragment extends Fragment {



private boolean isFirstLoad=true;


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            isFirstLoad = false;
            lazyLoad();
        }
    }

    protected abstract void lazyLoad() ;


}
