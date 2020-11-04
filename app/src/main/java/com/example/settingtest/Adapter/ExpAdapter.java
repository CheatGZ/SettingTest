package com.example.settingtest.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.settingtest.Node.ExpFirstNode;
import com.example.settingtest.Node.ExpSecondNode;
import com.example.settingtest.Node.NodeType;
import com.example.settingtest.R;
import com.example.settingtest.Provider.ExpRootNodeProvider;
import com.example.settingtest.Provider.ExpSecondNodeProvider;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/3
 * My Application
 */
public class ExpAdapter extends BaseNodeAdapter {
    private OnItemSwitchListener listener;

    public ExpAdapter(@Nullable List<BaseNode> nodeList) {
        super(nodeList);
        addNodeProvider(new ExpRootNodeProvider());
        addNodeProvider(new ExpSecondNodeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof ExpFirstNode) {
            return NodeType.VIEW_TYPE;
        } else if (node instanceof ExpSecondNode) {
            return NodeType.VIEW_TYPE_SECOND;
        }
        return -1;
    }

    public void setItemSwitchListener(OnItemSwitchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
            if (listener != null) {
                SwitchMaterial switchMaterial = holder.getView(R.id.switch_setting);
                switchMaterial.setOnCheckedChangeListener((compoundButton, b) -> listener.onItemCheckedChanged(compoundButton,holder.getItemViewType(), b, position));
            }
    }

    public interface OnItemSwitchListener {
        /**
         * 监听switch事件
         * @param view 控件
         * @param viewType 控件类型
         * @param isChecked 控件状态
         * @param position 控件位置
         */
        void onItemCheckedChanged(View view, int viewType, Boolean isChecked, int position);
    }
}
