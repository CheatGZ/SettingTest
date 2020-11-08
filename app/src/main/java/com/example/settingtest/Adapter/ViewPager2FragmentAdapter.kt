package com.example.settingtest.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * @author zhangyongkang
 * @date 2020/11/5
 * com.example.settingtest.View
 */
class ViewPager2FragmentAdapter(private val fragmentManager: FragmentManager,
                                private val lifecycle: Lifecycle,
                                private val list: MutableList<Fragment>) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}