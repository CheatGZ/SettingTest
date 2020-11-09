package com.example.settingtest.Adapter

import com.example.settingtest.Entity.StoreInfoData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/5
 * SettingTest
 */

class StoreManageAdapter(list: MutableList<StoreInfoData>) : BaseRecyclerViewAdapter<StoreInfoData,BaseRecyclerViewHolder>(R.layout.item_store_info,list) {

    override fun convert(holder: BaseRecyclerViewHolder, item: StoreInfoData) {
        holder.setText(R.id.txt_title, item.title)
                .setText(R.id.txt_description, item.description)
                .setText(R.id.txt_description2, item.description2)
                .setText(R.id.btn_delete, "卸载")
    }
}