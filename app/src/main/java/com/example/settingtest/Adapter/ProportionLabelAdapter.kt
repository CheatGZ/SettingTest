package com.example.settingtest.Adapter

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.settingtest.Entity.ProportionData
import com.example.settingtest.R


/**
 * @author zhangyongkang01_sx
 * @date 2020/11/6
 * SettingTest
 */
class ProportionLabelAdapter(private val list: MutableList<ProportionData>) : BaseQuickAdapter<ProportionData, BaseViewHolder>(R.layout.item_proportion_label, list) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun convert(holder: BaseViewHolder, item: ProportionData) {
        holder.setText(R.id.txt_label, item.name)
        val view = holder.getView<View>(R.id.img_label)
        val drawable = view.background as GradientDrawable

        when (item.color) {
            ProportionData.COLOR_GREEN -> drawable.setColor(context.getColor(R.color.green))
            ProportionData.COLOR_BLUE -> drawable.setColor(context.getColor(R.color.blue))
            ProportionData.COLOR_ORANGE -> drawable.setColor(context.getColor(R.color.orange))
            ProportionData.COLOR_WHITE -> drawable.setColor(context.getColor(R.color.white))
            ProportionData.COLOR_RED -> drawable.setColor(context.getColor(R.color.red))
            ProportionData.COLOR_gray -> drawable.setColor(context.getColor(R.color.gray_light))
            else -> drawable.setColor(context.getColor(R.color.purple_200))
        }

        view.background = drawable
    }
}