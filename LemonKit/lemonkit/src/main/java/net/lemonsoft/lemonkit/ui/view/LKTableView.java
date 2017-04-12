package net.lemonsoft.lemonkit.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import net.lemonsoft.lemonkit.adapter.LKScrollViewDelegateAdapter;
import net.lemonsoft.lemonkit.core.graphics.CGSize;
import net.lemonsoft.lemonkit.interfaces.ui.LKTableViewDataSource;
import net.lemonsoft.lemonkit.interfaces.ui.LKTableViewDelegate;
import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.model.LKTableViewRowAction;
import net.lemonsoft.lemonkit.tools.LKSizeTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 列表控件
 * Created by LiuRi on 2017/1/11.
 */

public class LKTableView extends LKUIScrollView {
    private ViewDragHelper mDragHelper;
    private GestureDetectorCompat mGestureDetector;
    float downY = 0;
    float downX = 0;
    private Context context;
    public LKTableViewDataSource dataSource;// 数据源
    public LKTableViewDelegate delegate;// 代理

    private LKIndexPath slidedCell;// 已经侧滑的cell的路径，如果没有，则为null
    /**
     * 类型记录池，记录池value格式：type_section_row，header:h,footer:f,cell:c,header和footer的row永远为0
     */
    private HashMap<Integer, String> typeRecorder;
    private List<Integer> startLocationRecorder;// 起始位置记录表

    private HashMap<LKIndexPath, LKHorizontalScrollView> cellScrollContainerPool;// cell的scrollView缓存池
    private HashMap<String, LKTableViewCell> frontCellPool;// 正在显示的cell的父View池

    private RelativeLayout contentView;// 正文控件，实际所有的cell都放到这个view中

    ///// 复用机制池 -- 开始

    private HashMap<String, ArrayList<LKTableViewCell>> reuseCellPool;// cell复用机制池
    private HashMap<String, ArrayList<LKTableViewHeader>> reuseHeaderViewPool;// header复用机制池
    private HashMap<String, ArrayList<LKTableViewFooter>> reuseFooterViewPool;// footer复用机制池
    private HashMap<String, LKTableViewHeader> frontHeaderPool;// 正在显示的header的父View池
    private HashMap<String, LKTableViewFooter> frontFooterPool;// 正在显示的footer的父View池


    ///// 复用机制池 -- 结束
    private int contentViewHeight;

    private boolean autoRun = true;

    public LKTableView(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //初始化手势识别器
        mGestureDetector = new GestureDetectorCompat(context, mOnGestureListener);

        initTableView();
        this.setDelegate(new LKScrollViewDelegateAdapter() {
            @Override
            public void scrollViewDidScroll(LKUIScrollView scrollView) {
                super.scrollViewDidScroll(scrollView);
                //共多少条
                int countPage = spaceOfLocation(contentViewHeight);
                //每页显示多少条
                int pageViewnum = spaceOfLocation(getHeight());
                int scroY = (int) scrollView.getContentOffset().y;
                int currentTopIndex = spaceOfLocation(scroY);
                int currentBottomIndex = spaceOfLocation(scroY + getHeight());
                if (currentBottomIndex <= countPage) {


                    if (scroY > lastScrollY) {

                        // tableView向上滚动
                        if (currentTopIndex > lastTopIndex)// 元素被从屏幕上方滚动出屏幕
                        {
                            for (int i = lastTopIndex; i < currentTopIndex; i++) {
                                //滚出
                                scrollOutScreen(i, typeRecorder.get(i));
                            }


                        }
                        if (currentBottomIndex > lastBottomIndex)// 元素被从屏幕下方滚入屏幕
                        {

                            for (int i = lastBottomIndex + 1; i < currentBottomIndex + 1; i++) {
                                //滚入

                                scrollIntoScreen(i, typeRecorder.get(i));
                            }


                        }


                    } else if (scroY < lastScrollY) {   // tableView向下滚动


                        if (currentBottomIndex < lastBottomIndex)// 元素被从屏幕上方滚动进入屏幕
                        {

                            for (int i = currentBottomIndex + 1; i < lastBottomIndex + 1; i++) {
                                scrollOutScreen(i, typeRecorder.get(i));

                            }
                        }


                        if (currentTopIndex < lastTopIndex)// 元素被从屏幕上方滚动进入屏幕
                        {
                            for (int i = currentTopIndex; i < lastTopIndex; i++) {
                                scrollIntoScreen(i, typeRecorder.get(i));

                            }

                        }


                    }
                    lastTopIndex = currentTopIndex;
                    lastBottomIndex = currentBottomIndex;
                    lastScrollY = scroY;// 刷新lastScrollY
                }

            }
        });
    }

    public void initTableView() {
        contentView = new RelativeLayout(this.context);
        typeRecorder = new HashMap<>();// 创建一个y坐标记录池
        startLocationRecorder = new ArrayList<>();// 创建一个y底部坐标记录池
        cellScrollContainerPool = new HashMap<>();// 创建一个根据LKIndexPath索引cellScrollView的池
        frontCellPool = new HashMap<>();// 创建前端正在显示的cell的存储池
        frontHeaderPool = new HashMap<>();// 创建前端正在显示的header的存储池
        frontFooterPool = new HashMap<>();// 创建前端正在显示的footer的存储池
        this.addView(this.contentView);

        //// 复用机制pool创建
        reuseCellPool = new HashMap<>();// cell复用pool
        reuseHeaderViewPool = new HashMap<>();// header复用pool
        reuseFooterViewPool = new HashMap<>();// footer复用pool
        //// 复用机制pool创建结束
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (autoRun)
            reloadData();
        autoRun = false;
    }

    /**
     * 向记录池中添加一条记录，记录池是为了复用机制动态计算游标而用的
     *
     * @param type    类型，h/f/c
     * @param section 分区号
     * @param row     行号
     * @param y       y坐标
     * @param height  行高
     */
    private void recorderAdd(String type, Integer section, Integer row, Integer y, Integer height) {
        if (height > 0) {
            // 生成行标识字符串，然后放到类型记录池中
            typeRecorder.put(startLocationRecorder.size(), String.format("%s_%d_%d", type, section, row));
            startLocationRecorder.add(y);
        }
    }

    /**
     * 刷新数据，该函数会清空所有的控件并完全重新加载，包括重新调用数据源和代理函数进行高度等数值计算
     */
    public void reloadData() {
        contentView.removeAllViews();
        typeRecorder.clear();// 移除所有类型记录
        startLocationRecorder.clear();// 移除所有开始游标记录
        if (dataSource != null) {
            // 指定了dataSource
            Integer sectionCount = dataSource.numberOfSectionsInTableView(this);// 调用数据源的对应函数，获取section的数量
            Integer contentHeight = 0;// 初始化高度统计变量
            for (int si = 0; si < sectionCount; si++) {// 根据section的数量进行依次调用获取row的数量
                Integer rowCount = dataSource.numberOfRowsInSection(this, si);// 加上section的header的高度
                Integer headerHeight = delegate.heightForHeaderInSection(this, si);
                recorderAdd("h", si, 0, contentHeight, headerHeight);// 把header加入游标记录中，准备复用机制的使用
                contentHeight += headerHeight;

                for (int ri = 0; ri < rowCount; ri++) {// 根据row的数量依次调用代理函数获取每行的高度
                    LKIndexPath indexPath = LKIndexPath.make(si, ri);
                    Integer cellHeight = delegate.heightForRowAtIndexPath(this, indexPath);
                    recorderAdd("c", si, ri, contentHeight, cellHeight);// 把cell加入游标记录中，准备复用机制的使用
                    contentHeight += cellHeight;
                }
                Integer footerHeight = delegate.heightForFooterInSection(this, si);
                recorderAdd("f", si, 0, contentHeight, footerHeight);// 把footer加入游标记录中，准备复用机制的使用
                contentHeight += footerHeight;// 加上section的footer的高度
            }
            initContentView(contentHeight);// 初始化容器控件
            this.setContentSize(CGSize.make(0, contentHeight));
            lastBottomIndex = spaceOfLocation(getHeight());// 初始化计算底部所处索引
            contentViewHeight = contentHeight;
        }
        getScreenRangeItems();
    }

    /**
     * 初始化容器控件
     *
     * @param contentHeight 容器控件的应有高度
     */
    private void initContentView(Integer contentHeight) {
        this.contentView.setX(0);
        this.contentView.setY(0);
        RelativeLayout.LayoutParams contentViewLayoutParams =
                new RelativeLayout.LayoutParams(getWidth(), contentHeight);// 整个tableView控件大小的params
        this.contentView.setLayoutParams(contentViewLayoutParams);
    }

    /**
     * 初始化cell，该函数负责把LKUITableViewCell显示到屏幕上
     *
     * @param indexPath 当前初始化行的位置信息
     */
    private LKTableViewCell initCell(final LKIndexPath indexPath, Integer cellHeight, Integer y) {
        final RelativeLayout cellContainerView = new RelativeLayout(context);
        cellContainerView.setX(0);
        cellContainerView.setY(0);
        Integer actionWidth = 0;
        // 通过代理函数获取当前行需要显示的actions
        List<LKTableViewRowAction> actions = delegate.editActionsForRowAtIndexPath(this, indexPath);
        //  设置actions相关-开始
        if (actions != null) {
            for (final LKTableViewRowAction action : actions) {
                RelativeLayout actionItemLayout = new RelativeLayout(context);
                actionItemLayout.setLayoutParams(new RelativeLayout.LayoutParams(action.getWidth(), cellHeight));// 初始化action控件大小
                action.getContainerView().setLayoutParams(new RelativeLayout.LayoutParams(action.getWidth(), cellHeight));// 设置action控件高度为cell高度
                action.getContainerView().setX(0);
                action.getContainerView().setY(0);
                actionItemLayout.addView(action.getContainerView());
                actionItemLayout.setX(actionWidth + getWidth());
                actionItemLayout.setBackgroundColor(action.getBackgroundColor());
                actionItemLayout.setY(0);
                actionItemLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 调用action点击事件
                        action.getTouchAction().onTouch(LKTableView.this, action, indexPath);
                        closeCurrentSlide();
                    }
                });
                cellContainerView.addView(actionItemLayout);
                actionWidth += action.getWidth();
            }
        }
        // 根据所有actions设置布局参数（scrollView宽度，使其能够滑动）
        RelativeLayout.LayoutParams cellContainerParams
                = new RelativeLayout.LayoutParams(getWidth() + actionWidth, cellHeight);// 使用等同于控件的宽度，用户设置的高度
        cellContainerView.setLayoutParams(cellContainerParams);
        //  设置actions相关-结束

        RelativeLayout.LayoutParams cellSizeParams
                = new RelativeLayout.LayoutParams(getWidth(), cellHeight);// 使用等同于控件的宽度，用户设置的高度
        final LKHorizontalScrollView scrollView = new LKHorizontalScrollView(context);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setLayoutParams(cellSizeParams);
        scrollView.setX(0);// 最左边开始
        scrollView.setY(y);// 顶部开始

        final Integer finalActionWidth = actionWidth;


        scrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (flage) {
                    //纵向手势时横向禁止滑动
                    return false;
                }

                if (event.getAction() == event.ACTION_MOVE && !indexPath.equals(slidedCell)) {
                    closeCurrentSlide();// 关闭当前侧滑
                }
                if (event.getAction() == event.ACTION_UP) {// 判断是触摸抬起的时候

                    if (v.getScrollX() < finalActionWidth / 2) {
                        ((LKHorizontalScrollView) v).smoothScrollTo(0, 0);// 平滑回到0
                    } else {
                        ((LKHorizontalScrollView) v).smoothScrollTo(finalActionWidth, 0);// 平滑移动到完全打开
                    }
                    //抬起后滑动手势重新初始化
                    LKUIScrollView.touchFlage = false;
                    flage = true;
                    return true;// 这里返回true，否则smoothScrollTo不可用
                }
                return false;
            }
        });
        // 处理记录当前哪条cell正在侧滑
        scrollView.setOnScrollListener(new LKHorizontalScrollView.ScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (l == finalActionWidth)
                    slidedCell = indexPath;
                else if (l == 0 && slidedCell != null && slidedCell.equals(indexPath))
                    slidedCell = null;
            }
        });
        scrollView.addView(cellContainerView);// 把整个容器控件放入到横向scrollView中

        // 初始化cellContentView，即控件主显示内容，不包括rowAction相关的内容
        RelativeLayout cellContentView = new RelativeLayout(context);
        cellContentView.setLayoutParams(cellSizeParams);
        cellContentView.setX(0);
        cellContentView.setY(0);
        cellContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCurrentSlide();// 点击内容时候让其自动关闭已经侧滑的cell
                delegate.didSelectRowAtIndexPath(LKTableView.this, indexPath);// 调用代理中的行点击事件
            }
        });
        LKTableViewCell cell = dataSource.cellForRowAtIndexPath(this, indexPath);
        cell.setLayoutParams(cellSizeParams);
        cellContentView.addView(cell);// 把LKTableViewCell控件实际放入到contentView中显示

        cellContainerView.addView(cellContentView);
        contentView.addView(scrollView);// 把cell添加到整个显示区的容器view中
        cellScrollContainerPool.put(indexPath, scrollView);
        frontCellPool.put(getCellPathInfoStringWithIndexPath(indexPath), cell);// 放到正在显示的池中
        return cell;
    }

    /**
     * 初始化section的header控件
     *
     * @param section 当前要初始化的控件的section索引
     */
    private void initHeader(Integer section, String pathInfo) {

        if (reuseHeaderViewPool.get(pathInfo) == null) {
            LKTableViewHeader view = dataSource.viewForHeaderInSection(this, section);
            if (view == null) {
                view = new LKTableViewHeader(getContext(), "header");
                TextView textView = new TextView(context);
                textView.setText(dataSource.titleForHeaderInSection(this, section));
                view.addView(textView);
                view.setY(startLocationRecorder.get(section));
                contentView.addView(view);// 把footer添加到整个显示区的容器view中
                frontHeaderPool.put(pathInfo, view);// 放到正在显示的header池中
            } else {
                frontHeaderPool.put(pathInfo, view);// 放到正在显示的header池中
                view.setY(startLocationRecorder.get(section));
                contentView.addView(view);
            }
        } else {
            try {
                contentView.removeView(reuseHeaderViewPool.get(pathInfo).get(0));
            } catch (Exception e) {
            }
            frontHeaderPool.put(pathInfo, reuseHeaderViewPool.get(pathInfo).get(0));// 放到正在显示的header池中
            contentView.addView(reuseHeaderViewPool.get(pathInfo).get(0));
        }


    }

    /**
     * 初始化section的footer控件
     *
     * @param section 当前要初始化的控件的section索引
     */
    private void initFooter(Integer section, String pathInfo) {
        if (reuseFooterViewPool.get(pathInfo) == null) {
            LKTableViewFooter view = dataSource.viewForFooterInSection(this, section);
            if (view == null) {
                view = new LKTableViewFooter(getContext(), "footer");
                TextView textView = new TextView(context);
                textView.setText(dataSource.titleForFooterInSection(this, section));
                view.addView(textView);
                view.setY(startLocationRecorder.get(section));
                contentView.addView(view);// 把footer添加到整个显示区的容器view中
                frontFooterPool.put(pathInfo, view);// 放到正在显示的footer池中
            } else {
                frontFooterPool.put(pathInfo, view);// 放到正在显示的footer池中
                view.setY(startLocationRecorder.get(section));
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, delegate.heightForHeaderInSection(this, 0)));
                contentView.addView(view);
            }
        } else {
            try {
                contentView.removeView(reuseFooterViewPool.get(pathInfo).get(0));
            } catch (Exception e) {
            }
            frontFooterPool.put(pathInfo, reuseFooterViewPool.get(pathInfo).get(0));// 放到正在显示的footer池中
            contentView.addView(reuseFooterViewPool.get(pathInfo).get(0));
        }


    }

    /**
     * 当前所在的位置处于哪段索引控件位置之上
     *
     * @param current 当前的位置
     * @return 所在的控件的索引
     */
    public Integer spaceOfLocation(Integer current) {
        if (startLocationRecorder.size() > 0) {
            for (int i = 0; i < startLocationRecorder.size() - 1; i++) {
                if (current >= startLocationRecorder.get(i) && current < startLocationRecorder.get(i + 1))// 在此区段之间
                    return i;
            }
            //判断是走到最上边还是最下边返回所在的控件的索引
            if (current <= 0) {
                return 0;
            } else {
                return startLocationRecorder.size() - 1;

            }
        }
        return 0;
    }

    private int lastTopIndex = 0;
    private int lastBottomIndex;
    private int lastScrollY = 0;

    /**
     * 滚动进入屏幕
     */
    private void scrollIntoScreen(Integer index, String pathInfo) {
        initLineWithRecorderItemIndex(index);// 要显示这个行了，现在初始化这个行
    }

    /**
     * 滚出屏幕
     */
    private void scrollOutScreen(Integer index, String pathInfo) {

        if (pathInfo.startsWith("c")) { // 移出去屏幕的是cell
            LKIndexPath indexPath = getIndexPathWithPathInfoString(pathInfo);
            if (indexPath != null && indexPath.equals(slidedCell))// 移除屏幕的cell要关闭侧滑
                closeCurrentSlide();// 关闭当前侧滑的cell
            // cell -> cellContentView -> cellContainerView -> scrollView
            LKTableViewCell cell = frontCellPool.get(pathInfo);
            if (cell != null) {// 不是null
                this.contentView.removeView((View) cell.getParent().getParent().getParent());// 从当前视图中移除控件
                ((ViewGroup) cell.getParent()).removeView(cell);
                if (!reuseCellPool.containsKey(cell.getReuseIdentifier()) ||
                        reuseCellPool.get(cell.getReuseIdentifier()) == null)// 没有这个服用标识或者不存在list
                    reuseCellPool.put(cell.getReuseIdentifier(), new ArrayList<LKTableViewCell>());// 那么重新创建一个list放到复用池中
                if (cell.getReuseIdentifier() != null)// 存在复用标识
                {
                    reuseCellPool.get(cell.getReuseIdentifier()).add(frontCellPool.get(pathInfo));// 把cell放到复用池中
                }
            }
            frontCellPool.remove(pathInfo);// 从控件存储池中移除
        } else if (pathInfo.startsWith("h")) {// 移出去屏幕的是Header
            LKTableViewHeader view = frontHeaderPool.get(pathInfo);
            if (view != null) {// 不是null
                contentView.removeView(view);
                if (!reuseHeaderViewPool.containsKey(view.getReuseIdentifier()) ||
                        reuseHeaderViewPool.get(view.getReuseIdentifier()) == null)// 没有这个服用标识或者不存在list
                    reuseHeaderViewPool.put(view.getReuseIdentifier(), new ArrayList<LKTableViewHeader>());// 那么重新创建一个list放到复用池中
                if (pathInfo != null)// 存在复用标识
                {
                    reuseHeaderViewPool.get(view.getReuseIdentifier()).add(frontHeaderPool.get(pathInfo));// 把header放到复用池中
                }

            }
            frontHeaderPool.remove(pathInfo);// 从控件存储池中移除
        } else {//移出去屏幕的是Footer
            LKTableViewFooter view = frontFooterPool.get(pathInfo);
            if (view != null) {// 不是null
                contentView.removeView(view);
                if (!reuseFooterViewPool.containsKey(view.getReuseIdentifier()) ||
                        reuseFooterViewPool.get(view.getReuseIdentifier()) == null)// 没有这个服用标识或者不存在list
                    reuseFooterViewPool.put(view.getReuseIdentifier(), new ArrayList<LKTableViewFooter>());// 那么重新创建一个list放到复用池中
                if (pathInfo != null)// 存在复用标识
                    reuseFooterViewPool.get(view.getReuseIdentifier()).add(frontFooterPool.get(pathInfo));// 把header放到复用池中
            }
            frontFooterPool.remove(pathInfo);// 从控件存储池中移除
        }
    }

    /**
     * 关闭当前已经侧滑的cell侧滑
     */
    public void closeCurrentSlide() {
        if (slidedCell != null)
            cellScrollContainerPool.get(slidedCell).smoothScrollTo(0, 0);
    }

    /**
     * 通过路径信息字符串得到LKIndexPath
     *
     * @param pathInfo 路径信息字符串
     * @return 路径信息字符串对应的IndexPath
     */
    public LKIndexPath getIndexPathWithPathInfoString(String pathInfo) {
        String[] pathItems = pathInfo.split("_");
        if (pathItems[0].equals("c")) {// 是cell

            return LKIndexPath.make(Integer.parseInt(pathItems[1]), Integer.parseInt(pathItems[2]));
        }
        return null;// 不是cell
    }

    /**
     * 根据LKIndexPath计算PathInfo字符串
     *
     * @param indexPath indexPath路径信息
     * @return PathInfo字符串
     */
    public String getCellPathInfoStringWithIndexPath(LKIndexPath indexPath) {
        return String.format("c_%d_%d", indexPath.section, indexPath.row);
    }

    /**
     * 获取当前屏幕正在显示的所有项目
     */
    public void getScreenRangeItems() {
        Integer startLocation = spaceOfLocation(0);
        Integer endLocation = spaceOfLocation(getHeight());
        for (int i = startLocation; i <= endLocation; i++) {
            initLineWithRecorderItemIndex(i);
        }
    }

    /**
     * 初始化行通过pathInfo字符串
     *
     * @param recorderItemIndex 记录索引
     */
    public void initLineWithRecorderItemIndex(Integer recorderItemIndex) {
        String pathInfo = typeRecorder.get(recorderItemIndex);
        if (pathInfo == null)
            return;
        ;
        String[] items = pathInfo.split("_");
        LKIndexPath indexPath = getIndexPathWithPathInfoString(pathInfo);
        switch (items[0]) {
            case "h":
                initHeader(recorderItemIndex, pathInfo);
                break;
            case "f":
                initFooter(recorderItemIndex, pathInfo);
                break;
            default:
                initCell(indexPath,
                        delegate.heightForRowAtIndexPath(this, indexPath),
                        startLocationRecorder.get(recorderItemIndex));// 初始化cell行控件
        }
    }


    //// 复用机制方法 - 开始

    /**
     * 根据复用标识从复用池中取cell
     *
     * @param identifier 复用标识字符串
     * @return 复用池中存储的对应cell
     */
    public LKTableViewCell dequeueReusableCellWithIdentifier(String identifier) {
        if (reuseCellPool.containsKey(identifier) &&
                reuseCellPool.get(identifier) != null &&
                reuseCellPool.get(identifier).size() > 0) {
            LKTableViewCell cell = reuseCellPool.get(identifier).get(0);
            reuseCellPool.get(identifier).remove(0);// 从复用池中移除
            return cell;
        }
        return null;
    }

    /**
     * 根据复用标识从复用池中取View header
     *
     * @param identifier 复用标识字符串
     * @return 复用池中存储的对应View header
     */
    public LKTableViewHeader dequeueReusableHeaderWithIdentifier(String identifier) {
        if (reuseHeaderViewPool.containsKey(identifier) &&
                reuseHeaderViewPool.get(identifier) != null &&
                reuseHeaderViewPool.get(identifier).size() > 0) {
            LKTableViewHeader view = reuseHeaderViewPool.get(identifier).get(0);
            reuseHeaderViewPool.get(identifier).remove(0);// 从复用池中移除
            return view;
        }
        return null;
    }


    /**
     * 根据复用标识从复用池中取View header
     *
     * @param identifier 复用标识字符串
     * @return 复用池中存储的对应View header
     */
    public LKTableViewFooter dequeueReusableFooterWithIdentifier(String identifier) {

        if (reuseFooterViewPool.containsKey(identifier) &&
                reuseFooterViewPool.get(identifier) != null &&
                reuseFooterViewPool.get(identifier).size() > 0) {
            LKTableViewFooter view = reuseFooterViewPool.get(identifier).get(0);
            reuseFooterViewPool.get(identifier).remove(0);// 从复用池中移除
            return view;
        }
        System.out.println(" ERRRR: " + identifier);
        System.out.println(" ---> time to : " + System.currentTimeMillis());
        return null;
    }

    // 复用机制方法 - 结束

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        int changeHei = params.height;
        int currentBottomIndex = 0;
        //params.height 为-1时 传入的是LayoutParams.MATCH_PARENT,并且改变之前高度不是充满状态
        if (this.getParent() != null) {
            //共多少条数据
            int countPage = spaceOfLocation(contentViewHeight);
            //每页显示多少条数据
            int pageViewnum = spaceOfLocation(getHeight());

            //&& i < countPage && i < pageViewnum - 1

            if (changeHei == -1 && ((View) this.getParent()).getHeight() != getHeight()) {
                changeHei = LKSizeTool.getDefaultSizeTool().screenHeightDp();
                currentBottomIndex = spaceOfLocation(changeHei + getHeight());
                if (currentBottomIndex > lastBottomIndex && lastBottomIndex > countPage)// 元素从屏幕底部滚动进入屏幕
                {
                    for (int i = lastBottomIndex; i < currentBottomIndex && i < contentViewHeight - 1; i++) {
                        //当前最后一个的下一个进入之前变化
                        scrollIntoScreen(i + 1, typeRecorder.get(i + 1));
                    }
                    //需要刷新显示池最后一个标识
                    lastBottomIndex = currentBottomIndex;
                    lastScrollY = changeHei;// 刷新lastScrollY
                }

            } else {
                //传入参数为数值时
                if (changeHei - getHeight() > 0) {
                    //高度变大了后需要向显示控件池内增加
                    currentBottomIndex = spaceOfLocation(changeHei + getHeight());
                    if (currentBottomIndex > lastBottomIndex && lastBottomIndex > countPage)// 元素从屏幕底部滚动进入屏幕
                    {
                        for (int i = lastBottomIndex; i < currentBottomIndex; i++) {
                            //当前最后一个的下一个进入之前变化
                            scrollIntoScreen(i + 1, typeRecorder.get(i + 1));
                        }
                        //需要刷新显示池最后一个标识
                        lastBottomIndex = currentBottomIndex;
                        lastScrollY = changeHei;// 刷新lastScrollY
                    }

                } else if (changeHei - getHeight() < 0) {
                    //高度变小了后需要从显示控件池内删除
                    currentBottomIndex = spaceOfLocation(changeHei + getHeight());
                    if (currentBottomIndex < lastBottomIndex)// 元素从屏幕底部滚动移出去了
                    {
                        for (int i = currentBottomIndex; i < lastBottomIndex; i++) {
                            //当前最后一个的下一个进入之前变化
                            scrollOutScreen(i + 1, typeRecorder.get(i + 1));
                        }
                        //需要刷新显示池最后一个标识
                        lastBottomIndex = currentBottomIndex;
                        lastScrollY = changeHei;// 刷新lastScrollY
                    }
                }

            }
        }
    }


    //横向是否可滑动标识
    boolean flage = true;


    @Override
    public boolean onInterceptTouchEvent(android.view.MotionEvent ev) {

        // 决定当前的SwipeLayout是否要把touch事件拦截下来，直接交由自己的onTouchEvent处理
        // 返回true则为拦截
        flage = mGestureDetector.onTouchEvent(ev);
        LKUIScrollView.touchFlage = !flage;
        if (flage)
            closeCurrentSlide();
        return flage;
    }

    private android.view.GestureDetector.SimpleOnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            // 当纵向移动距离大于等于横向时，返回true
            return Math.abs(distanceX) <= Math.abs(distanceY);

        }
    };

    public HashMap<String, ArrayList<LKTableViewCell>> getReuseCellPool() {
        return reuseCellPool;
    }
}
