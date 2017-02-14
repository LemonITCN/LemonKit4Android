package net.lemonsoft.lemonkit.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.lemonsoft.lemonkit.core.parser.LKUIAnnotationParser;

/**
 * LemonKit Fragment，对原有的Fragment的封装和增强
 * Created by LiuRi on 2017/2/14.
 */

public class LKFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LKUIAnnotationParser.parse(this, inflater, container);
        initView(view);
        return view;
    }

    protected void initView(View view) {
    }

}
