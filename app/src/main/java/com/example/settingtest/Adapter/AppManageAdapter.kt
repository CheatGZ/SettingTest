package com.example.settingtest.Adapter

import android.view.View
import android.widget.CompoundButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Node.AppAuthorityNode
import com.example.settingtest.R
import com.google.android.material.switchmaterial.SwitchMaterial

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class AppManageAdapter(data: MutableList<AppAuthorityNode>?) : BaseQuickAdapter<AppAuthorityNode, BaseViewHolder>(R.layout.item_authority_manage, data) {
    private var listener: OnItemSwitchListener? = null
    override fun convert(baseViewHolder: BaseViewHolder, appAuthorityNode: AppAuthorityNode) {
        baseViewHolder.setText(R.id.txt_title, appAuthorityNode.title)
                .setText(R.id.txt_description, appAuthorityNode.description)
        val switchMaterial = baseViewHolder.getView<SwitchMaterial>(R.id.btn_switch)
        switchMaterial.isChecked = appAuthorityNode.status
    }

    fun setItemSwitchListener(listener: OnItemSwitchListener?) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (listener != null) {
            val switchMaterial = holder.getView<SwitchMaterial>(R.id.btn_switch)
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