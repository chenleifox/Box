package org.chennl.demo.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.chennl.demo.util.Logger;

/**
 * Fragment基类
 * Created by chennl on 16-8-4.
 */
public class BaseFragment extends Fragment {

    final String TAG = getClass().getSimpleName();

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Logger.e(TAG, "===> onAttach <===");
    }
}
