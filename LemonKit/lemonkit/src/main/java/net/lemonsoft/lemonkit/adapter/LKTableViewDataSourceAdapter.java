package net.lemonsoft.lemonkit.adapter;


import net.lemonsoft.lemonkit.interfaces.ui.LKTableViewDataSource;
import net.lemonsoft.lemonkit.ui.view.LKTableView;
import net.lemonsoft.lemonkit.ui.view.LKTableViewFooter;
import net.lemonsoft.lemonkit.ui.view.LKTableViewHeader;

/**
 * LKUITableView的数据源适配器
 * Created by lemonsoft on 16-10-12.
 */
public abstract class LKTableViewDataSourceAdapter implements LKTableViewDataSource {

    @Override
    public Integer numberOfSectionsInTableView(LKTableView tableView) {
        return 1;
    }

    @Override
    public String titleForHeaderInSection(LKTableView tableView, Integer section) {
        return null;// 返回null表示不显示header
    }

    @Override
    public String titleForFooterInSection(LKTableView tableView, Integer section) {
        return null;// 返回null表示不显示footer
    }

    @Override
    public LKTableViewHeader viewForHeaderInSection(LKTableView tableView, Integer section) {
        return null;
    }

    @Override
    public LKTableViewFooter viewForFooterInSection(LKTableView tableView, Integer section) {
        return null;
    }
}
