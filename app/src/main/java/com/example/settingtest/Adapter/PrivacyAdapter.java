package com.example.settingtest.Adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.settingtest.Node.NodeType;
import com.example.settingtest.Node.PrivacyFirstNode;
import com.example.settingtest.Node.PrivacySecondNode;
import com.example.settingtest.Provider.PrivacyFirstNodeProvider;
import com.example.settingtest.Provider.PrivacySecondNodeProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class PrivacyAdapter extends BaseNodeAdapter {
    public PrivacyAdapter(@Nullable List<BaseNode> nodeList) {
        super(nodeList);
        addNodeProvider(new PrivacyFirstNodeProvider());
        addNodeProvider(new PrivacySecondNodeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof PrivacyFirstNode) {
            return NodeType.VIEW_TYPE;
        } else if (node instanceof PrivacySecondNode) {
            return NodeType.VIEW_TYPE_SECOND;
        }
        return -1;
    }

}
