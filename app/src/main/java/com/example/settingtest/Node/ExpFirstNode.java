package com.example.settingtest.Node;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/3
 * My Application
 */
public class ExpFirstNode extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String imgUrl;
    private String setting;
    private String settingDescription;
    private Boolean status;


    public ExpFirstNode(List<BaseNode> childNode) {
        this.childNode = childNode;
        setExpanded(false);
    }

    public ExpFirstNode() {
        setExpanded(false);
    }

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

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }
}
