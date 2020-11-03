package com.example.settingtest;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/3
 * My Application
 */
public class SettingSecondData extends BaseNode {

    /**
     *开发者选项
     */
    public static final int VIEW_TYPE_DEV=0;
    /**
     *其他选项
     */
    public static final int VIEW_TYPE=1;

    private String imgUrl;
    private String setting;
    private String settingDescription;
    private Boolean status;
    private int type;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    public void setSettingDescription(String settingDescription) {
        this.settingDescription = settingDescription;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
