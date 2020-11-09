package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.Adapter.StoreManageAdapter
import com.example.settingtest.Entity.StoreInfoData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.OnItemChildClickListener
import com.example.settingtest.databinding.FragmentStoreManageBinding


/**
 * @author zhangyongkang
 * @date 2020/11/5
 * com.example.settingtest.View
 */
class StoreManageFragment : Fragment() {
    private lateinit var binding: FragmentStoreManageBinding
    private lateinit var mAdapter: StoreManageAdapter
    private lateinit var list: MutableList<StoreInfoData>
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
        val kindAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_kind, R.layout.layout_spinner)
        val timeAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_time, R.layout.layout_spinner)
        kindAdapter.setDropDownViewResource(R.layout.layout_spinner_item)
        timeAdapter.setDropDownViewResource(R.layout.layout_spinner_item)
        binding.spinnerKind.adapter = kindAdapter
        binding.spinnerTime.adapter = timeAdapter


        binding.btnBack.setOnClickListener(onClickListener)
        list = ArrayList()
        for (i in 1..10) {
            list.add(StoreInfoData("00$i", "名称00$i", "大小", "最近使用日期", 0, false))
        }
        mAdapter = StoreManageAdapter(list)
        mAdapter.addChildClickViewIds(R.id.btn_delete)
        mAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int) {
                Toast.makeText(context, "click is $position", Toast.LENGTH_SHORT).show()
                list[position].status = true
                list.removeAt(position)
                mAdapter.notifyDataSetChanged()
            }
        })
        binding.revStoreInfo.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.revStoreInfo.adapter = mAdapter

    }
}

