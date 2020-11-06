package com.example.settingtest.Adapter

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Node.ProportionNode
import com.example.settingtest.R

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/6
 * SettingTest
 */
class ProportionAdapter(private val maxProportion: Float,
                        private val rootViewWidth: Int,
                        private val list: MutableList<ProportionNode>) : BaseQuickAdapter<ProportionNode, BaseViewHolder>(R.layout.item_proportion,list) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun convert(holder: BaseViewHolder, item: ProportionNode) {
        val view = holder.getView<View>(R.id.view_proportion)
        view.layoutParams.width = ((item.proportion / maxProportion) * rootViewWidth).toInt()
        when (item.color) {
            ProportionNode.COLOR_GREEN -> view.setBackgroundColor(context.getColor(R.color.green))
            ProportionNode.COLOR_BLUE -> view.setBackgroundColor(context.getColor(R.color.blue))
            ProportionNode.COLOR_ORANGE -> view.setBackgroundColor(context.getColor(R.color.orange))
            ProportionNode.COLOR_WHITE -> view.setBackgroundColor(context.getColor(R.color.white))
            ProportionNode.COLOR_RED -> view.setBackgroundColor(context.getColor(R.color.red))
            ProportionNode.COLOR_gray -> view.setBackgroundColor(context.getColor(R.color.gray_light))
        }
    }
}