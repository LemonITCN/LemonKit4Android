package net.lemonsoft.lemonkit.model.general;

/**
 * 通用模型类 - 矩形信息描述对象
 * 保存矩形的尺寸信息，和矩形的左上点的位置信息
 * Created by LiuRi on 2017/2/21.
 */
public class LKRectInfo {

    private LKPointInfo origin;

    private LKSizeInfo size;

    public LKRectInfo() {
        this.origin = new LKPointInfo();
        this.size = new LKSizeInfo();
    }

    public LKRectInfo(LKPointInfo origin, LKSizeInfo size) {
        this.origin = origin;
        this.size = size;
    }

    public LKRectInfo(int x, int y, int width, int height) {
        this.origin = new LKPointInfo(x, y);
        this.size = new LKSizeInfo(width, height);
    }

    public LKRectInfo(float x, float y, float width, float height) {
        this.origin = new LKPointInfo(x, y);
        this.size = new LKSizeInfo(width, height);
    }

    public LKPointInfo getOrigin() {
        return origin;
    }

    public void setOrigin(LKPointInfo origin) {
        this.origin = origin;
    }

    public LKSizeInfo getSize() {
        return size;
    }

    public void setSize(LKSizeInfo size) {
        this.size = size;
    }
}
