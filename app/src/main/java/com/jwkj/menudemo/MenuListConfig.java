package com.jwkj.menudemo;

/**
 * 菜单列表配置项
 * Created by dali on 2017/4/24.
 */

public class MenuListConfig {
    private MenuItemType menuItemType;
    /**
     * 正常状态下的图片id
     */
    private int normalResId;
    /**
     * 正常按下状态下的图片id
     */
    private int normalPressResId;
    /**
     * 正在加载状态下的图片id
     */
    private int loaddingResId;
    /**
     * 选中状态下的图片id
     */
    private int checkedResId;
    /**
     * 选中按下状态下的图片id
     */
    private int checkedPressResId;

    public MenuListConfig(MenuItemType menuItemType) {
        this.menuItemType = menuItemType;
    }

    /**
     * 创建最小类型
     *
     * @param normalResId
     * @param normalPressResId
     * @return
     */
    public void buildSmallType(int normalResId, int normalPressResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
    }

    /**
     * 创建开关类型
     *
     * @param normalResId
     * @param normalPressResId
     * @return
     */
    public void buildSwitchType(int normalResId, int normalPressResId, int checkedResId, int checkedPressResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
        this.checkedResId = checkedResId;
        this.checkedPressResId = checkedPressResId;
    }

    /**
     * 创建开关并且含有加载体动画的类型
     *
     * @param normalResId
     * @param normalPressResId
     * @return
     */
    public void buildSwitchLoaddingType(int normalResId, int normalPressResId, int checkedResId, int checkedPressResId, int loaddingResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
        this.checkedResId = checkedResId;
        this.checkedPressResId = checkedPressResId;
        this.loaddingResId = loaddingResId;
    }

    public int getNormalResId() {
        return normalResId;
    }

    public int getNormalPressResId() {
        return normalPressResId;
    }

    public int getLoaddingResId() {
        return loaddingResId;
    }

    public int getCheckedResId() {
        return checkedResId;
    }

    public int getCheckedPressResId() {
        return checkedPressResId;
    }

    public MenuItemType getMenuItemType() {
        return menuItemType;
    }
}
