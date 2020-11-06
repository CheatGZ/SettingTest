package com.example.settingtest.Adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Node.StoreInfo
import com.example.settingtest.R

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/5
 * SettingTest
 */
class StoreInfoAdapter(data: MutableList<StoreInfo>?) : BaseQuickAdapter<StoreInfo, BaseViewHolder>(R.layout.item_store_info, data) {
    override fun convert(holder: BaseViewHolder, item: StoreInfo) {
        holder.setText(R.id.txt_title, item.title)
                .setText(R.id.txt_description, item.description)
                .setText(R.id.txt_description2,item.description2)
                .setText(R.id.btn_delete,"卸载")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }
}