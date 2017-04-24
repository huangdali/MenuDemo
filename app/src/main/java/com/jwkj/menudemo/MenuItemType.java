package com.jwkj.menudemo;

/**
 * 菜单的类型
 * Created by Administrator on 2017/4/24.
 */

public enum MenuItemType {
    SMALL,//两个状态[正常，按下--->抬起之后恢复正常]
    SWITCH,//开关，四个状态[正常，正常时按下，选中，选中时按下]
    SWITCH_LOADDING//带有加载动画/图的，五个状态[正常，正常时按下，加载动画，选中，选中时按下]
}
