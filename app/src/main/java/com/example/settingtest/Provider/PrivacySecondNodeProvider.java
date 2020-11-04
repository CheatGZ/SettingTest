package com.example.settingtest.Provider;

import android.view.View;

import androidx.navigation.Navigation;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.settingtest.Node.NodeType;
import com.example.settingtest.Node.PrivacySecondNode;
import com.example.settingtest.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class PrivacySecondNodeProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return NodeType.VIEW_TYPE_SECOND;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_privacy_second;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        baseViewHolder.setText(R.id.txt_title, ((PrivacySecondNode) baseNode).getTitle())
                .setText(R.id.txt_description, ((PrivacySecondNode) baseNode).getDescription());
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        Navigation.findNavController(view).navigate(R.id.action_privacy_fragment_to_app_manage_fragment);
    }
}
