package com.example.settingtest;

import android.view.View;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/3
 * My Application
 */
public class SettingAdapter extends BaseNodeAdapter {
    private onItemSwitchListener listener;

    public SettingAdapter(@Nullable List<BaseNode> nodeList) {
        super(nodeList);
        addNodeProvider(new RootNodeProvider());
        addNodeProvider(new SecondNodeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof SettingFirstData) {
            return SettingFirstData.VIEW_TYPE_EXPAND;
        } else if (node instanceof SettingSecondData) {
            return SettingFirstData.VIEW_TYPE;
        }
        return -1;
    }

    public void setItemSwitchListener(onItemSwitchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
//        if(holder.getItemViewType()==SettingFirstData.VIEW_TYPE_EXPAND) {
            SwitchMaterial switchMaterial = holder.getView(R.id.switch_setting);
            if (listener != null) {
                switchMaterial.setOnCheckedChangeListener((compoundButton, b) -> listener.onItemCheckedChanged(compoundButton,holder.getItemViewType(), b, position));
            }
//        }else if()
    }

    public interface onItemSwitchListener {
        void onItemCheckedChanged(View view, int viewType,Boolean isChecked, int position);
    }
}
