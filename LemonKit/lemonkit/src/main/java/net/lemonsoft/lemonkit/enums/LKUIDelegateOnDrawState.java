package net.lemonsoft.lemonkit.enums;

/**
 * LKUIDelegate中的onDraw函数的状态枚举
 * Created by lemonsoft on 2017/1/28.
 */

public enum LKUIDelegateOnDrawState {

    /**
     * 父类绘制方法绘制之前
     */
    PRE_SUPER_DRAW(0),
    /**
     * 父类绘制方法绘制之后
     */
    AFT_SUPER_DRAW(1);

    private int value;

    LKUIDelegateOnDrawState(int value) {
        this.value = value;
    }

}
