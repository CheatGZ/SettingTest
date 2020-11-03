package com.example.settingtest;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

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

    public SettingAdapter() {
        super();
        addNodeProvider(new RootNodeProvider());
        addNodeProvider(new SecondNodeProvider());
    }

    public SettingAdapter(@Nullable List<BaseNode> nodeList) {
        super(nodeList);
        addNodeProvider(new RootNodeProvider());
        addNodeProvider(new SecondNodeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof SettingFirstData) {
            return SettingFirstData.VIEW_TYPE_DEV;
        } else if (node instanceof SettingSecondData) {
            return SettingFirstData.VIEW_TYPE;
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SwitchMaterial switchMaterial=holder.getView(R.id.switch_setting);
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
//                    expand(position);
                    Log.d("kangkang adapter","expand");
                }else {
//                    collapse(position);

                    Log.d("kangkang adapter","collapse");
                }
            }
        });
    }
}
