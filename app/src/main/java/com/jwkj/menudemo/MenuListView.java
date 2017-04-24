package com.jwkj.menudemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单列表
 * Created by dali on 2017/4/24.
 */

public class MenuListView extends LinearLayout {
    private Context context;
    private List<MenuListConfig> datas = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public MenuListView(Context context) {
        this(context, null);
    }

    public MenuListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.setOrientation(VERTICAL);

    }

    public void setAdapter(List<MenuListConfig> datas, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.datas = datas;
        showView(datas);
    }

    /**
     * 显示视图
     *
     * @param datas
     */
    private void showView(List<MenuListConfig> datas) {
        /**
         * TODO 增加可滑动
         */
        for (int i = 0; i < datas.size(); i++) {
            MenuListConfig config = datas.get(i);
            MenuItemView view = new MenuItemView(context);
            view.setImageResId(config.getNormalResId(), config.getNormalPressResId(),
                    config.getLoaddingResId(), config.getCheckedResId(), config.getCheckedPressResId());
            if (onItemClickListener != null) {
                final int finalI = i;
                view.setOnClickMenuItemViewListener(new MenuItemView.OnClickMenuItemViewListener() {
                    @Override
                    public void onClick(MenuItemView view) {
                        onItemClickListener.onClick(view, finalI);
                    }
                });
            }
            addView(view);
        }
    }

    interface OnItemClickListener {
        void onClick(MenuItemView view, int position);
    }
}
