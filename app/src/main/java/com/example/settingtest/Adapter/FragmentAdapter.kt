package com.example.settingtest.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.settingtest.Fragment.CommonFragment
import com.example.settingtest.Fragment.PrivacyFragment

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/6
 * SettingTest
 */
class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var listTitle: MutableList<String> = ArrayList()
    private var listFragment:MutableList<Fragment>


    init {
        listTitle.add("通用")
        listTitle.add("隐私")
        listFragment=ArrayList()
        listFragment.add(CommonFragment.instance)
        listFragment.add(PrivacyFragment.instance)
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }
}