package org.chennl.demo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import org.chennl.demo.base.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.rg_main_tab)
    RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {

            }
        });
    }
}
