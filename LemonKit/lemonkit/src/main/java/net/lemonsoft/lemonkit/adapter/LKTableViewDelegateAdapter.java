package net.lemonsoft.lemonkit.adapter;


import net.lemonsoft.lemonkit.interfaces.ui.LKTableViewDelegate;
import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKTableViewRowAction;
import net.lemonsoft.lemonkit.ui.view.LKTableView;

import java.util.ArrayList;
import java.util.List;

/**
 * LKUITableView的代理适配器
 * Created by lemonsoft on 16-10-12.
 */
public abstract class LKTableViewDelegateAdapter implements LKTableViewDelegate {

    @Override
    public Integer heightForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
        return 140;
    }

    @Override
    public Integer heightForHeaderInSection(LKTableView tableView, Integer section) {
        return 80;
    }

    @Override
    public Integer heightForFooterInSection(LKTableView tableView, Integer section) {
        return 80;
    }

    @Override
    public void didSelectRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
    }

    @Override
    public List<LKTableViewRowAction> editActionsForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
        return new ArrayList<>();
    }
}
