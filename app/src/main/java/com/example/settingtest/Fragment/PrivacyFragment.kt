package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.Adapter.AppManageAdapter
import com.example.settingtest.Entity.AppAuthorityData
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentPrivacyBinding
import java.util.*

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class PrivacyFragment : Fragment() {
    private lateinit var binding: FragmentPrivacyBinding
    private lateinit var mAdapter: AppManageAdapter
    private lateinit var lists: MutableList<AppAuthorityData>
    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.labelPrivacyState -> NavHostFragment.findNavController(this).navigate(R.id.action_privacy_fragment_to_privacy_statement_fragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPrivacyBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.labelPrivacyState.setOnClickListener(onClickListener)
        lists = ArrayList()
        for (i in 1..10) {
            lists.add(AppAuthorityData("", "应用名称", "已开启存储、麦克风、定位权限"))
        }
        mAdapter = AppManageAdapter(lists)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            NavHostFragment.findNavController(this).navigate(R.id.action_privacy_fragment_to_app_manage_fragment)
        }
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.revApp.layoutManager = linearLayoutManager
        binding!!.revApp.adapter = mAdapter
    }
}