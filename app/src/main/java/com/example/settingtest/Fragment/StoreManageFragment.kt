package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.Adapter.StoreInfoAdapter
import com.example.settingtest.Node.StoreInfo
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentStoreManageBinding
/**
 * @author zhangyongkang
 * @date 2020/11/5
 * com.example.settingtest.View
 */
class StoreManageFragment : Fragment() {
    private lateinit var binding: FragmentStoreManageBinding
    private lateinit var mAdapter: StoreInfoAdapter
    private lateinit var list: MutableList<StoreInfo>
    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.btnBack -> NavHostFragment.findNavController(this).navigateUp()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentStoreManageBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.btnBack.setOnClickListener(onClickListener)
        list = ArrayList()
        for (i in 1..10) {
            list.add(StoreInfo("00$i", "名称00$i", "大小", "最近使用日期",0, false))
        }
        mAdapter = StoreInfoAdapter(list)
        mAdapter.addChildClickViewIds(R.id.btn_delete)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            Toast.makeText(context, "click is $position", Toast.LENGTH_SHORT).show()
            list[position].status = true
            list.removeAt(position)
            mAdapter.notifyDataSetChanged()
        }
        binding.revStoreInfo.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.revStoreInfo.adapter = mAdapter

    }
}