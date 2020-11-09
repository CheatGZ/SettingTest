package com.example.settingtest.Adapter

import com.example.settingtest.Entity.AppAuthorityData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class AppManageAdapter(data: MutableList<AppAuthorityData>) : BaseRecyclerViewAdapter<AppAuthorityData, BaseRecyclerViewHolder>(R.layout.item_app_manage, data) {

    override fun convert(holder: BaseRecyclerViewHolder, item: AppAuthorityData) {
        holder.setText(R.id.txt_title, item.title)
                .setText(R.id.txt_description, item.description)
    }
}