package net.lemonsoft.lemonkit.adapter;


import net.lemonsoft.lemonkit.core.graphics.CGPoint;
import net.lemonsoft.lemonkit.interfaces.ui.LKScrollViewDelegate;
import net.lemonsoft.lemonkit.ui.view.LKUIScrollView;

/**
 * Created by LiuRi on 2017/1/9.
 */

public abstract class LKScrollViewDelegateAdapter implements LKScrollViewDelegate {

    @Override
    public void scrollViewDidScroll(LKUIScrollView scrollView) {

    }

    @Override
    public void scrollViewWillBeginDragging(LKUIScrollView scrollView) {

    }

    @Override
    public void scrollViewWillEndDragging(LKUIScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset) {

    }

    @Override
    public void scrollViewDidEndDragging(LKUIScrollView scrollView, boolean decelerate) {

    }

    @Override
    public void scrollViewWillBeginDecelerating(LKUIScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndDecelerating(LKUIScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndScrollingAnimation(LKUIScrollView scrollView) {

    }

}
