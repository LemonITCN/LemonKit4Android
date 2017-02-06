package net.lemonsoft.lemonkit.interfaces.ui;


import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.ui.view.LKTableView;
import net.lemonsoft.lemonkit.ui.view.LKTableViewCell;
import net.lemonsoft.lemonkit.ui.view.LKTableViewFooter;
import net.lemonsoft.lemonkit.ui.view.LKTableViewHeader;

/**
 * 数据源接口
 */
public interface LKTableViewDataSource {
    // required
    Integer numberOfRowsInSection(LKTableView tableView, Integer section);

    LKTableViewCell cellForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    // optional
    Integer numberOfSectionsInTableView(LKTableView tableView);

    String titleForHeaderInSection(LKTableView tableView, Integer section);

    String titleForFooterInSection(LKTableView tableView, Integer section);

    LKTableViewHeader viewForHeaderInSection(LKTableView tableView, Integer section);

    LKTableViewFooter viewForFooterInSection(LKTableView tableView, Integer section);


}