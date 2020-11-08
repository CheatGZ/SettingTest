package com.example.settingtest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingtest.Adapter.AppManageAdapter;
import com.example.settingtest.Node.AppAuthorityNode;
import com.example.settingtest.R;
import com.example.settingtest.databinding.FragmentAppManageBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class AppManageFragment extends Fragment {
    private FragmentAppManageBinding binding;
    private AppManageAdapter mAdapter;
    private List<AppAuthorityNode> lists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAppManageBinding.inflate(inflater);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.btnBack.setOnClickListener(v -> NavHostFragment.findNavController(AppManageFragment.this).navigateUp());

        lists = new ArrayList<>();
        AppAuthorityNode appAuthorityNode1 = new AppAuthorityNode("存储", "功能说明", "", true);
        AppAuthorityNode appAuthorityNode2 = new AppAuthorityNode("麦克风", "功能说明", "", false);
        AppAuthorityNode appAuthorityNode3 = new AppAuthorityNode("定位", "功能说明", "", true);
        AppAuthorityNode appAuthorityNode4 = new AppAuthorityNode("无线网和蜂窝数据", "功能说明", "", false);
        lists.add(appAuthorityNode1);
        lists.add(appAuthorityNode2);
        lists.add(appAuthorityNode3);
        lists.add(appAuthorityNode4);
        mAdapter = new AppManageAdapter(lists);
        mAdapter.setItemSwitchListener((view, isChecked, position) -> {
            lists.get(position).setStatus(isChecked);
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revAppManage.setLayoutManager(linearLayoutManager);
        binding.revAppManage.setAdapter(mAdapter);

    }
}
