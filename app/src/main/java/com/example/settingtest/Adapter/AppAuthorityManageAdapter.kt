package com.example.settingtest.Adapter

import android.view.View
import android.widget.CompoundButton
import com.example.settingtest.Entity.AppManageData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.View.LabelView

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class AppAuthorityManageAdapter(data: MutableList<AppManageData>?) : BaseRecyclerViewAdapter<AppManageData, BaseRecyclerViewHolder>(R.layout.item_app_authority_manage, data) {

    override fun convert(holder: BaseRecyclerViewHolder, item: AppManageData) {
        val view = holder.getView<LabelView>(R.id.label_view)
        view.run {
            setTitleText(item.title)
            setDescription(item.description)
            setSwitchStatus(item.status)
            setImage(item.drawable)
        }
    }
}