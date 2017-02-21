package net.lemonsoft.lemonkit.model.general;

/**
 * LK通用模型对象 - 点信息描述类
 * 描述一个点的坐标，包括x/y
 * Created by LiuRi on 2017/2/21.
 */

public class LKPointInfo {

    private int x;
    private int y;

    public LKPointInfo() {
    }

    public LKPointInfo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public LKPointInfo(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX(float x) {
        this.x = (int) x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY(float y) {
        this.y = (int) y;
    }

}
