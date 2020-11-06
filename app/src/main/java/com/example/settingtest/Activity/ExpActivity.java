package com.example.settingtest.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.settingtest.Adapter.ExpAdapter;
import com.example.settingtest.Node.ExpFirstNode;
import com.example.settingtest.Node.ExpSecondNode;
import com.example.settingtest.Node.NodeType;
import com.example.settingtest.databinding.ActivityExpBinding;

import java.util.ArrayList;
import java.util.List;

public class ExpActivity extends AppCompatActivity {
    private ActivityExpBinding binding;
    private ExpAdapter mAdapter;
    private List<BaseNode> lists;
    private int count;
    private boolean expandFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        lists = getEntity();
        mAdapter = new ExpAdapter(lists);
        count = (lists.get(0)).getChildNode().size();
        expandFlag = ((ExpFirstNode) lists.get(0)).getStatus();
        if (expandFlag) {
            mAdapter.expand(0);
        } else {
            mAdapter.collapse(0);
        }

        mAdapter.setItemSwitchListener((view, viewType, isChecked, position) -> {
            if (!view.isPressed()) {
                return;
            }

            if (position == 0) {
                if (isChecked) {
                    mAdapter.expand(position);
                    expandFlag = true;
                } else {
                    mAdapter.collapse(position);
                    expandFlag = false;
                }
                ((ExpFirstNode) lists.get(position)).setStatus(expandFlag);
            } else {
                if (expandFlag) {
                    if (viewType == NodeType.VIEW_TYPE_SECOND) {
                        ((ExpSecondNode) ((lists.get(0).getChildNode()).get(position - 1))).setStatus(isChecked);
                    } else {
                        ((ExpFirstNode) lists.get(position - count)).setStatus(isChecked);
                    }
                } else {
                    ((ExpFirstNode) lists.get(position - count)).setStatus(isChecked);
                }

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.revSetting.setLayoutManager(linearLayoutManager);
        binding.revSetting.setAdapter(mAdapter);
        binding.txtTitle.setOnClickListener(v -> {
            finish();
        });
    }

    private List<BaseNode> getEntity() {
        //总的 list，里面放的是 FirstNode
        List<BaseNode> list = new ArrayList<>();
        List<BaseNode> secondNodeLst = new ArrayList<>();

        ExpSecondNode secondData1 = new ExpSecondNode();
        secondData1.setSetting("停用虚拟围栏");
        secondData1.setSettingDescription("功能说明");
        secondData1.setStatus(false);
        ExpSecondNode secondData2 = new ExpSecondNode();
        secondData2.setSetting("安装第三方APK");
        secondData2.setSettingDescription("功能说明");
        secondData2.setStatus(false);
        secondNodeLst.add(secondData1);
        secondNodeLst.add(secondData2);

        ExpFirstNode firstData1 = new ExpFirstNode(secondNodeLst);
        firstData1.setSetting("开发者模式");
        firstData1.setSettingDescription("功能说明");
        firstData1.setStatus(true);

        ExpFirstNode firstData2 = new ExpFirstNode();
        firstData2.setSetting("未知来源应用安装");
        firstData2.setSettingDescription("功能说明");
        firstData2.setStatus(false);
        ExpFirstNode firstData3 = new ExpFirstNode();
        firstData3.setSetting("自动唤醒、熄灭屏幕");
        firstData3.setSettingDescription("功能说明");
        firstData3.setStatus(false);

        list.add(firstData1);
        list.add(firstData2);
        list.add(firstData3);
        return list;
    }

}