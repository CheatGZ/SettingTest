package com.example.settingtest.Adapter

import android.view.View
import android.widget.CompoundButton
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
class AppAuthorityManageAdapter(data: MutableList<AppManageData>?) : BaseQuickAdapter<AppManageData, BaseViewHolder>(R.layout.item_app_authority_manage, data) {
    private var listener: OnItemSwitchListener? = null
    override fun convert(baseViewHolder: BaseViewHolder, appManageData: AppManageData) {
        val view = baseViewHolder.getView<LabelView>(R.id.label_view)
        view.run {
            setTitleText(appManageData.title)
            setDescription(appManageData.description)
            setSwitchStatus(appManageData.status)
            setImage(appManageData.drawable)
        }
    }

    fun setItemSwitchListener(listener: OnItemSwitchListener?) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (listener != null) {
            val view = holder.getView<LabelView>(R.id.label_view)
            val switchMaterial=view.getSwitch()
            switchMaterial.setOnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean -> listener!!.onItemCheckedChanged(compoundButton, b, position) }
        }
    }

    interface OnItemSwitchListener {
        /**
         * 监听switch事件
         *
         * @param view      控件
         * @param isChecked 控件状态
         * @param position  控件位置
         */
        fun onItemCheckedChanged(view: View?, isChecked: Boolean?, position: Int)
    }
}