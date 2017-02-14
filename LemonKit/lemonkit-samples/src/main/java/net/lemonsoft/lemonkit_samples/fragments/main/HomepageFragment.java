package net.lemonsoft.lemonkit_samples.fragments.main;

import android.view.View;
import android.widget.TextView;

import net.lemonsoft.lemonkit.annotations.SetContentView;
import net.lemonsoft.lemonkit.base.LKFragment;
import net.lemonsoft.lemonkit_samples.R;

/**
 * Fragment - 柠檬家
 * Created by LiuRi on 2017/2/13.
 */
@SetContentView(R.layout.fragment_main_homepage)
public class HomepageFragment extends LKFragment {

    private TextView myTextView;

    @Override
    protected void initView(View view) {
        super.initView(view);
    }
}
