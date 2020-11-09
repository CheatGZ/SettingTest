package com.example.settingtest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.settingtest.Entity.StoreInfoData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseViewHolder

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/5
 * SettingTest
 */
//class StoreInfoAdapter(data: MutableList<StoreInfoData>?) : BaseQuickAdapter<StoreInfoData, BaseViewHolder>(R.layout.item_store_info, data) {
//    override fun convert(holder: BaseViewHolder, item: StoreInfoData) {
//        holder.setText(R.id.txt_title, item.title)
//                .setText(R.id.txt_description, item.description)
//                .setText(R.id.txt_description2,item.description2)
//                .setText(R.id.btn_delete,"卸载")
//    }
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: MutableList<Any>) {
//        super.onBindViewHolder(holder, position, payloads)
//    }
//}
class StoreInfoAdapter(private val list: MutableList<StoreInfoData>) : BaseRecyclerViewAdapter<StoreInfoData,BaseViewHolder>(R.layout.item_store_info,list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_store_info, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setText(R.id.txt_title, list[position].title)
                .setText(R.id.txt_description, list[position].description)
                .setText(R.id.txt_description2, list[position].description2)
                .setText(R.id.btn_delete, "卸载")
    }

    override fun getItemCount(): Int {
        return data.size
    }


}