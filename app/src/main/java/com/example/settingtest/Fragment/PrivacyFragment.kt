package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.settingtest.Adapter.PrivacyAdapter
import com.example.settingtest.Node.PrivacyFirstNode
import com.example.settingtest.Node.PrivacySecondNode
import com.example.settingtest.databinding.FragmentPrivacyBinding
import java.util.*

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class PrivacyFragment private constructor(): Fragment() {
    private lateinit var binding: FragmentPrivacyBinding
    private var mAdapter: PrivacyAdapter? = null
    private var lists: MutableList<BaseNode>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPrivacyBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding!!.txtTitle.setOnClickListener { activity?.finish() }
        lists = ArrayList()
        val privacyFirstNode = PrivacyFirstNode(null, "", "隐私说明", "描述")
        val childNode: MutableList<BaseNode> = ArrayList()
        val privacySecondNode1 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        val privacySecondNode2 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        val privacySecondNode3 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        val privacySecondNode4 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        val privacySecondNode5 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        val privacySecondNode6 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        val privacySecondNode7 = PrivacySecondNode("", "应用名称", "已开启存储、麦克风、定位权限")
        childNode.add(privacySecondNode1)
        childNode.add(privacySecondNode2)
        childNode.add(privacySecondNode3)
        childNode.add(privacySecondNode4)
        childNode.add(privacySecondNode5)
        childNode.add(privacySecondNode6)
        childNode.add(privacySecondNode7)
        val privacyFirstNode1 = PrivacyFirstNode(childNode, "", "应用权限管理", "功能描述（管理应用能够使用的权限，如麦克风、访问存储空间）")
        (lists as ArrayList<BaseNode>).add(privacyFirstNode)
        (lists as ArrayList<BaseNode>).add(privacyFirstNode1)
        mAdapter = PrivacyAdapter(lists)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.revPrivacy.layoutManager = linearLayoutManager
        binding!!.revPrivacy.adapter = mAdapter
    }

    companion object {
        val instance: PrivacyFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PrivacyFragment()
        }
    }
}