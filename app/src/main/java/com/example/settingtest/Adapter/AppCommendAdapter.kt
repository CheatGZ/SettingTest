package com.example.settingtest.Adapter

import android.view.View
import com.example.settingtest.Entity.AppCommendData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.View.OnItemClickListener

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Adapter
 */
class AppCommendAdapter(layoutRes: Int, data: MutableList<AppCommendData>) : BaseRecyclerViewAdapter<AppCommendData, BaseRecyclerViewHolder>(layoutRes, data) {
    private lateinit var itemClickListener: OnItemClickListener
    private lateinit var listener:View.OnClickListener
    override fun convert(holder: BaseRecyclerViewHolder, item: AppCommendData) {
        holder.run {
            setImageResource(R.id.img_icon, R.mipmap.ic_launcher)
            setText(R.id.txt_title, item.name)
            setText(R.id.txt_description, item.description)
        }
    }
}