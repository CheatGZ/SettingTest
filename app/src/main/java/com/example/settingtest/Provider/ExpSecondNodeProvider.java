package com.example.settingtest.Provider;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.settingtest.Node.ExpFirstNode;
import com.example.settingtest.Node.ExpSecondNode;
import com.example.settingtest.Node.NodeType;
import com.example.settingtest.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.jetbrains.annotations.NotNull;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/3
 * My Application
 */
public class ExpSecondNodeProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return NodeType.VIEW_TYPE_SECOND;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_exp_second;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        ExpSecondNode entity=(ExpSecondNode)baseNode;
        baseViewHolder.setText(R.id.txt_setting,entity.getSetting());
        baseViewHolder.setText(R.id.txt_setting_description,entity.getSettingDescription());
        @SuppressLint("UseSwitchCompatOrMaterialCode") SwitchMaterial status=baseViewHolder.getView(R.id.switch_setting);
        status.setChecked(entity.getStatus());
    }


}
