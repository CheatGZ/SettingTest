package com.example.settingtest.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.settingtest.Node.AppAuthorityNode;
import com.example.settingtest.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class AppManageAdapter extends BaseQuickAdapter<AppAuthorityNode, BaseViewHolder> {
    private OnItemSwitchListener listener;

    public AppManageAdapter(@Nullable List<AppAuthorityNode> data) {
        super(R.layout.item_authority_manage, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AppAuthorityNode appAuthorityNode) {
        baseViewHolder.setText(R.id.txt_title, appAuthorityNode.getTitle())
                .setText(R.id.txt_description, appAuthorityNode.getDescription());
        SwitchMaterial switchMaterial = baseViewHolder.getView(R.id.btn_switch);
        switchMaterial.setChecked(appAuthorityNode.getStatus());
    }

    public void setItemSwitchListener(OnItemSwitchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (listener != null) {
            SwitchMaterial switchMaterial = holder.getView(R.id.btn_switch);
            switchMaterial.setOnCheckedChangeListener((compoundButton, b) -> listener.onItemCheckedChanged(compoundButton, b, position));
        }
    }

    public interface OnItemSwitchListener {
        /**
         * 监听switch事件
         *
         * @param view      控件
         * @param isChecked 控件状态
         * @param position  控件位置
         */
        void onItemCheckedChanged(View view, Boolean isChecked, int position);
    }
}
