package com.example.settingtest.Adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.example.settingtest.Entity.BannerData
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Adapter
 */
class ImageBannerAdapter(data: MutableList<BannerData>) : BannerAdapter<BannerData, BannerImageHolder>(data) {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerImageHolder {
        val imageView=ImageView(parent.context)
        imageView.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        return BannerImageHolder(imageView)
    }

    override fun onBindView(holder: BannerImageHolder, data: BannerData, position: Int, size: Int) {
        holder.imageView.setImageResource(data.imgSrc)
    }
}