package net.lemonsoft.lemonkit.model.general;

/**
 * 通用模型 - 尺寸信息描述模型
 * Created by LiuRi on 2017/2/21.
 */

public class LKSizeInfo {

    private int width;
    private int height;

    public LKSizeInfo() {
    }

    public LKSizeInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public LKSizeInfo(float width, float height) {
        this.width = (int) width;
        this.height = (int) height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setWidth(float width) {
        this.width = (int) width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHeight(float height) {
        this.height = (int) height;
    }
}
