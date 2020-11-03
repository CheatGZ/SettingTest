package com.example.settingtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private com.example.settingtest.databinding.ActivityMainBinding binding;
    private SettingAdapter mAdapter;
    private List<BaseNode> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.settingtest.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        initView();

    }


    private void initView() {
        lists = getEntity();
        mAdapter = new SettingAdapter(lists);
        SettingFirstData entity=(SettingFirstData)lists.get(0);
        if(entity.getStatus()){
            mAdapter.expand(0);
        }else {
            mAdapter.collapse(0);
        }
//
//        mAdapter.addChildClickViewIds(R.id.switch_setting);
//        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
//            if(view.getId()==R.id.switch_setting){
//                @SuppressLint("UseSwitchCompatOrMaterialCode") SwitchMaterial status= (SwitchMaterial) view;
////                if(status.isChecked()){
////                    mAdapter.expand(position);
////                }else {
////                    mAdapter.collapse(position);
////                }
//                if (!status.isPressed()) {
//                    return;
//                }
//                mAdapter.expandOrCollapse(position);
//            }
//        });



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.revSetting.setLayoutManager(linearLayoutManager);
        binding.revSetting.setAdapter(mAdapter);
    }

    private List<BaseNode> getEntity() {
        //总的 list，里面放的是 FirstNode
        List<BaseNode> list = new ArrayList<>();
        List<BaseNode> secondNodeLst = new ArrayList<>();

        SettingSecondData secondData1 = new SettingSecondData();
        secondData1.setSetting("停用虚拟围栏");
        secondData1.setSettingDescription("功能说明");
        secondData1.setStatus(false);
        SettingSecondData secondData2 = new SettingSecondData();
        secondData2.setSetting("安装第三方APK");
        secondData2.setSettingDescription("功能说明");
        secondData2.setStatus(false);
        secondNodeLst.add(secondData1);
        secondNodeLst.add(secondData2);

        SettingFirstData firstData1 = new SettingFirstData(secondNodeLst);
        firstData1.setSetting("开发者模式");
        firstData1.setSettingDescription("功能说明");
        firstData1.setStatus(true);

        SettingFirstData firstData2 = new SettingFirstData();
        firstData2.setSetting("未知来源应用安装");
        firstData2.setSettingDescription("功能说明");
        firstData2.setStatus(false);
        SettingFirstData firstData3 = new SettingFirstData();
        firstData3.setSetting("自动唤醒、熄灭屏幕");
        firstData3.setSettingDescription("功能说明");
        firstData3.setStatus(false);

        list.add(firstData1);
        list.add(firstData2);
        list.add(firstData3);
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}