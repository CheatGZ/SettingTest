package com.example.settingtest.Adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Entity.AppAuthorityData
import com.example.settingtest.R

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class AppManageAdapter(data: MutableList<AppAuthorityData>) : BaseQuickAdapter<AppAuthorityData, BaseViewHolder>(R.layout.item_app_manage, data) {

    override fun convert(baseViewHolder: BaseViewHolder, appAuthorityData: AppAuthorityData) {
        baseViewHolder.setText(R.id.txt_title, appAuthorityData.title)
                .setText(R.id.txt_description, appAuthorityData.description)
    }
}