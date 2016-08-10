package org.chennl.demo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.chennl.demo.util.Logger;

/**
 * FragmentActivity基类
 * Created by chennl on 16-8-4.
 */
public class BaseFragmentActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Logger.e(TAG, "===> onCreate <===");
    }

}
