package com.jwkj.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isChecked = false;
    private MenuListView menulistView;
    List<MenuListConfig> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menulistView = (MenuListView) findViewById(R.id.mlv_main_test);
        /**
         * 构造数据项
         */
        MenuListConfig mSMALL = new MenuListConfig(MenuItemType.SMALL);
        mSMALL.buildSmallType(R.mipmap.monitor_control_sensor, R.mipmap.monitor_control_sensor_p);

        MenuListConfig mSWITCH = new MenuListConfig(MenuItemType.SWITCH);
        mSWITCH.buildSwitchType(R.mipmap.monitor_control_memory_point,
                R.mipmap.monitor_control_memory_point_p, R.mipmap.monitor_control_key,
                R.mipmap.monitor_control_key_p);

        MenuListConfig mSWITCH_LOADDING = new MenuListConfig(MenuItemType.SWITCH_LOADDING);
        mSWITCH_LOADDING.buildSwitchLoaddingType(R.mipmap.monitor_control_sensor, R.mipmap.monitor_control_sensor_p,
                R.mipmap.monitor_control_key, R.mipmap.monitor_control_key_p, R.mipmap.a1);

        datas.add(mSMALL);
        datas.add(mSWITCH);
        datas.add(mSWITCH_LOADDING);
        /**
         * 设置适配器
         */
        menulistView.setAdapter(datas, new MenuListView.OnItemClickListener() {
            @Override
            public void onClick(final MenuItemView view, int position) {
                switch (datas.get(position).getMenuItemType()) {
                    case SMALL:
                        KLog.e("点击 SMALL 了");
                        break;
                    case SWITCH:
                        KLog.e("点击开关了");
                        isChecked = !isChecked;
                        view.setChecked(isChecked);
                        break;
                    case SWITCH_LOADDING:
                        KLog.e("点击带有进度的开关了");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                isChecked = !isChecked;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        view.setChecked(isChecked);
                                    }
                                });

                            }
                        }.start();

                        break;
                }

            }
        });
    }
}
