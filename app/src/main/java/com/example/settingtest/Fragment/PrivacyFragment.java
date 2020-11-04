package com.example.settingtest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.settingtest.Activity.MainActivity;
import com.example.settingtest.Adapter.PrivacyAdapter;
import com.example.settingtest.Node.PrivacyFirstNode;
import com.example.settingtest.Node.PrivacySecondNode;
import com.example.settingtest.databinding.FragmentPrivacyBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class PrivacyFragment extends Fragment {
    private FragmentPrivacyBinding binding;
    private PrivacyAdapter mAdapter;
    private List<BaseNode> lists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPrivacyBinding.inflate(inflater);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        lists=new ArrayList<>();
        PrivacyFirstNode privacyFirstNode = new PrivacyFirstNode(null, "", "隐私说明", "描述");
        List<BaseNode> childNode = new ArrayList<>();
        PrivacySecondNode privacySecondNode1 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        PrivacySecondNode privacySecondNode2 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        PrivacySecondNode privacySecondNode3 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        PrivacySecondNode privacySecondNode4 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        PrivacySecondNode privacySecondNode5 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        PrivacySecondNode privacySecondNode6 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        PrivacySecondNode privacySecondNode7 = new PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限");
        childNode.add(privacySecondNode1);
        childNode.add(privacySecondNode2);
        childNode.add(privacySecondNode3);
        childNode.add(privacySecondNode4);
        childNode.add(privacySecondNode5);
        childNode.add(privacySecondNode6);
        childNode.add(privacySecondNode7);
        PrivacyFirstNode privacyFirstNode1 = new PrivacyFirstNode(childNode, "", "应用权限管理", "功能描述（管理应用能够使用的权限，如麦克风、访问存储空间）");
        lists.add(privacyFirstNode);
        lists.add(privacyFirstNode1);
        mAdapter = new PrivacyAdapter(lists);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revPrivacy.setLayoutManager(linearLayoutManager);
        binding.revPrivacy.setAdapter(mAdapter);
    }
}
