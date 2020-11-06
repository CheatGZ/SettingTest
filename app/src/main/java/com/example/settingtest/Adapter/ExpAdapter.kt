package com.example.settingtest.Adapter

import android.view.View
import android.widget.CompoundButton
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Node.ExpFirstNode
import com.example.settingtest.Node.ExpSecondNode
import com.example.settingtest.Node.NodeType
import com.example.settingtest.Provider.ExpRootNodeProvider
import com.example.settingtest.Provider.ExpSecondNodeProvider
import com.example.settingtest.R
import com.google.android.material.switchmaterial.SwitchMaterial

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/3
 * My Application
 */
class ExpAdapter(nodeList: MutableList<BaseNode>?) : BaseNodeAdapter(nodeList) {
    private var listener: OnItemSwitchListener? = null
    override fun getItemType(list: List<BaseNode>, i: Int): Int {
        val node = list[i]
        if (node is ExpFirstNode) {
            return NodeType.VIEW_TYPE
        } else if (node is ExpSecondNode) {
            return NodeType.VIEW_TYPE_SECOND
        }
        return -1
    }

    fun setItemSwitchListener(listener: OnItemSwitchListener?) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (listener != null) {
            val switchMaterial = holder.getView<SwitchMaterial>(R.id.switch_setting)
            switchMaterial.setOnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean -> listener!!.onItemCheckedChanged(compoundButton, holder.itemViewType, b, position) }
        }
    }

    interface OnItemSwitchListener {
        /**
         * 监听switch事件
         * @param view 控件
         * @param viewType 控件类型
         * @param isChecked 控件状态
         * @param position 控件位置
         */
        fun onItemCheckedChanged(view: View?, viewType: Int, isChecked: Boolean?, position: Int)
    }

    init {
        addNodeProvider(ExpRootNodeProvider())
        addNodeProvider(ExpSecondNodeProvider())
    }
}