package com.example.settingtest.Provider;

import android.view.View;
import android.widget.ImageView;

import androidx.navigation.Navigation;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.settingtest.Node.NodeType;
import com.example.settingtest.Node.PrivacyFirstNode;
import com.example.settingtest.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class PrivacyFirstNodeProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return NodeType.VIEW_TYPE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_privacy;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        baseViewHolder.setText(R.id.txt_title, ((PrivacyFirstNode) baseNode).getTitle())
                .setText(R.id.txt_description, ((PrivacyFirstNode) baseNode).getDescription());
        if (baseNode.getChildNode() != null) {
            ImageView imageView = baseViewHolder.getView(R.id.img_go_to);
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        if (data.getChildNode() == null) {
//            NavHostFragment.findNavController().navigate(R.id.action_privacy_fragment_to_privacy_statement_fragment);
            Navigation.findNavController(view).navigate(R.id.action_privacy_fragment_to_privacy_statement_fragment);
        }
    }

}
