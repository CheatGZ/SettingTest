package com.example.settingtest.Node;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class PrivacyFirstNode extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String imgUrl;
    private String title;
    private String description;

    public PrivacyFirstNode(List<BaseNode> childNode, String imgUrl, String title, String description) {
        this.childNode = childNode;
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
        setExpanded(true);
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
