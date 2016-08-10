package org.chennl.demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.chennl.demo.util.Logger;

/**
 * 一般Activity的基类
 * Created by chennl on 16-8-4.
 */
public class BaseActivity extends Activity {

    final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "===> onCreate <===");
    }
}
