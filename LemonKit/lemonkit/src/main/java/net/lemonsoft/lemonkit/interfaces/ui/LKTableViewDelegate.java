package net.lemonsoft.lemonkit.interfaces.ui;


import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKTableViewRowAction;
import net.lemonsoft.lemonkit.ui.view.LKTableView;

import java.util.List;


/**
 * 代理接口
 */
public interface LKTableViewDelegate {
    // Variable height support
    Integer heightForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    Integer heightForHeaderInSection(LKTableView tableView, Integer section);

    Integer heightForFooterInSection(LKTableView tableView, Integer section);

    void didSelectRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    List<LKTableViewRowAction> editActionsForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);
}