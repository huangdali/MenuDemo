package com.jwkj.menudemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


/**
 * 菜单子项
 * Created by dali on 2017/4/24.
 */

public class MenuItemView extends ImageView {
    private static final int STATE_NORMAL = 1;//正常状态
    private static final int STATE_NORMAL_PRESS = 2;//正常按下时
    private static final int STATE_LODDING = 3;//正在加载
    private static final int STATE_CHECKED = 4;//选中状态
    private static final int STATE_CHECKED_PRESS = 5;//选中按下状态

    private static final String TAG = "MenuItemView";
    private int widthSize = 72, heightSize = 72;
    private int normalResId, normalPressResId, loaddingResId, checkedResId, checkedPressResId;
    private boolean isChecked;//是否选中
    private boolean isLoadding = true;//是否正在加载中

    private OnClickMenuItemViewListener onClickMenuItemViewListener;
    private int paddingSize = 15;//内边距大小
    private ObjectAnimator loaddingAnimator = ObjectAnimator.ofFloat(this, "Rotation", 0, 360);

    public MenuItemView(Context context) {
        this(context, null);
    }

    public MenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.FIT_XY);
        widthSize = DensityUtil.dip2px(context, widthSize);
        heightSize = DensityUtil.dip2px(context, heightSize);
        paddingSize = DensityUtil.dip2px(context, paddingSize);
        this.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
        this.setBackground(getResources().getDrawable(R.drawable.selector_menuitemview_circlebg));

        initAnimation();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoadding(true);//点击的时候默认是加载状态
                if (onClickMenuItemViewListener != null) {
                    onClickMenuItemViewListener.onClick((MenuItemView) v);
                } else {
                    setLoadding(false);//没有设置监听就停止显示动画
                }
                if (checkedResId == 0) {//没有选中状态
                    setState(STATE_NORMAL);
                }
            }
        });
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        loaddingAnimator.setDuration(1000);//设置旋转
        loaddingAnimator.setRepeatCount(100);//重复
    }


    /**
     * 设置图片资源id
     *
     * @param normalResId       正常显示图片
     * @param normalPressResId  按下的时候显示的图片
     * @param loaddingResId     加载的时候显示的图片
     * @param checkedResId      最后显示的图片
     * @param checkedPressResId 最后选中的图片的按下状态
     */
    public void setImageResId(int normalResId, int normalPressResId, int loaddingResId, int checkedResId, int checkedPressResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
        this.loaddingResId = loaddingResId;
        this.checkedResId = checkedResId;
        this.checkedPressResId = checkedPressResId;
        setState(STATE_NORMAL);
    }

    /**
     * 设置图片资源id
     *
     * @param normalResId       正常显示图片
     * @param normalPressResId  按下的时候显示的图片
     * @param checkedResId      最后显示的图片
     * @param checkedPressResId 最后选中的图片的按下状态
     */
    public void setImageResId(int normalResId, int normalPressResId, int checkedResId, int checkedPressResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
        this.checkedResId = checkedResId;
        this.checkedPressResId = checkedPressResId;
        setState(STATE_NORMAL);
    }

    /**
     * 设置图片资源id
     *
     * @param normalResId      正常显示图片
     * @param normalPressResId 按下的时候显示的图片
     * @param checkedResId     最后显示的图片
     */
    public void setImageResId(int normalResId, int normalPressResId, int checkedResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
        this.checkedResId = checkedResId;
        setState(STATE_NORMAL);
    }

    /**
     * 设置图片资源id
     *
     * @param normalResId      正常显示图片
     * @param normalPressResId 按下的时候显示的图片
     */
    public void setImageResId(int normalResId, int normalPressResId) {
        this.normalResId = normalResId;
        this.normalPressResId = normalPressResId;
        setState(STATE_NORMAL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {//抬起了，要恢复
            if (checkedResId == 0) {//没有最终状态
                setState(STATE_NORMAL);
            } else {
                if (isChecked) {
                    setState(STATE_CHECKED);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下了要显示按下时的图片
            if (checkedResId == 0) {//没有选中
                setState(STATE_NORMAL);
            } else {
                if (isChecked) {
                    setState(STATE_CHECKED_PRESS);
                } else {
                    setState(STATE_NORMAL_PRESS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public boolean isChecked() {
        return isChecked;
    }

    /**
     * 设置是否选中
     *
     * @param checked
     */
    public void setChecked(boolean checked) {
        this.isLoadding = false;//调用setchecked说明已经有结果了，就不在加载了
        this.isChecked = checked;
        if (isChecked) {
            setState(STATE_CHECKED);
        } else {
            setState(STATE_NORMAL);
        }
    }

    public boolean isLoadding() {
        return isLoadding;
    }

    /**
     * 设置是否正在加载中
     *
     * @param loadding
     */
    public void setLoadding(boolean loadding) {
        isLoadding = loadding;
        if (isLoadding) {
            setState(STATE_LODDING);
        } else {
            setState(isChecked ? STATE_CHECKED : STATE_NORMAL);//判断是否选中了
        }
    }

    /**
     * 设置单击事件
     *
     * @param onClickMenuItemViewListener
     */
    public void setOnClickMenuItemViewListener(OnClickMenuItemViewListener onClickMenuItemViewListener) {
        this.onClickMenuItemViewListener = onClickMenuItemViewListener;
    }

    interface OnClickMenuItemViewListener {
        void onClick(MenuItemView view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);//设置测量的宽度
    }


    /**
     * 设置状态
     *
     * @param state
     */
    public void setState(int state) {
        closeLoaddingAnimator();//关闭动画
        switch (state) {
            case STATE_NORMAL:
                setImageResource(normalResId);
                break;
            case STATE_NORMAL_PRESS:
                setImageResource(normalPressResId);
                break;
            case STATE_LODDING:
                setImageResource(loaddingResId);
                loaddingAnimator.start();
                break;
            case STATE_CHECKED:
                setImageResource(checkedResId);
                break;
            case STATE_CHECKED_PRESS:
                setImageResource(checkedPressResId);
                break;
        }
    }

    /**
     * 关闭动画
     */
    private void closeLoaddingAnimator() {
        if (loaddingAnimator.isRunning()) {
            loaddingAnimator.end();
        }
    }
}
