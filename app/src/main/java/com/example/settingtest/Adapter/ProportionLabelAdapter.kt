package com.example.settingtest.Adapter

import android.graphics.drawable.GradientDrawable
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
class ProportionLabelAdapter(private val list: MutableList<ProportionNode>) : BaseQuickAdapter<ProportionNode, BaseViewHolder>(R.layout.item_proportion_label, list) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun convert(holder: BaseViewHolder, item: ProportionNode) {
        holder.setText(R.id.txt_label, item.name)
        val view = holder.getView<View>(R.id.img_label)
        val drawable = view.background as GradientDrawable

        when (item.color) {
            ProportionNode.COLOR_GREEN -> drawable.setColor(context.getColor(R.color.green))
            ProportionNode.COLOR_BLUE -> drawable.setColor(context.getColor(R.color.blue))
            ProportionNode.COLOR_ORANGE -> drawable.setColor(context.getColor(R.color.orange))
            ProportionNode.COLOR_WHITE -> drawable.setColor(context.getColor(R.color.white))
            ProportionNode.COLOR_RED -> drawable.setColor(context.getColor(R.color.red))
            ProportionNode.COLOR_gray -> drawable.setColor(context.getColor(R.color.gray_light))
            else -> drawable.setColor(context.getColor(R.color.purple_200))
        }

        view.background = drawable
    }
}