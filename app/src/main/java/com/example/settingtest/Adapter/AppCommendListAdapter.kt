package com.example.settingtest.Adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.Entity.AppCommendListData
import com.example.settingtest.R
import com.example.settingtest.Utils.ViewHintUtil.Companion.showSnackBar
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.View.OnItemClickListener

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Adapter
 */
class AppCommendListAdapter(layoutRes: Int, data: MutableList<AppCommendListData>) : BaseRecyclerViewAdapter<AppCommendListData, BaseRecyclerViewHolder>(layoutRes, data) {
    override fun convert(holder: BaseRecyclerViewHolder, item: AppCommendListData) {
        holder.setText(R.id.txt_title, item.typeId)
        val rev = holder.getView<RecyclerView>(R.id.rev_app_list)
        val mAdapter = AppCommendAdapter(R.layout.item_app_commend, item.appList)
        /** 监听事件 */
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int) {
                view.showSnackBar("${item.appList[position]}")
            }
        })
        val snapHelper=LinearSnapHelper()
        snapHelper.attachToRecyclerView(rev)
        rev.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rev.isNestedScrollingEnabled = false
        rev.adapter = mAdapter
    }
}