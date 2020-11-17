package com.example.settingtest.Adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Entity.AppManageData
import com.example.settingtest.R
import com.example.settingtest.View.LabelView

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */

class PermissionManageAdapter(data: MutableList<AppManageData>?) : BaseQuickAdapter<AppManageData, BaseViewHolder>(R.layout.item_app_authority_manage, data) {
    override fun convert(holder: BaseViewHolder, item: AppManageData) {
        val view = holder.getView<LabelView>(R.id.label_view)
        view.run {
            setTitleText(item.title)
            setDescription(item.description)
            setSwitchStatus(item.status)
            setImage(item.drawable)
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

    }
}