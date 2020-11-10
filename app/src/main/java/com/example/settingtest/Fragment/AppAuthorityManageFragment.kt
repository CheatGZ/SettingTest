package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.Adapter.AppAuthorityManageAdapter
import com.example.settingtest.Entity.AppManageData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.OnItemChildCheckChangedListener
import com.example.settingtest.databinding.FragmentAppManageBinding
import java.util.*

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class AppAuthorityManageFragment : Fragment() {
    private lateinit var binding: FragmentAppManageBinding
    private lateinit var mAdapter: AppAuthorityManageAdapter
    private lateinit var lists: MutableList<AppManageData>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAppManageBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.btnBack.setOnClickListener { v: View? -> NavHostFragment.findNavController(this@AppAuthorityManageFragment).navigateUp() }
        lists = ArrayList()
        val appAuthorityNode1 = AppManageData("存储", requireContext().getDrawable(R.mipmap.ic_question)!!, "存储", "功能说明", true)
        val appAuthorityNode2 = AppManageData("麦克风", requireContext().getDrawable(R.mipmap.ic_question)!!, "麦克风", "功能说明", false)
        val appAuthorityNode3 = AppManageData("定位", requireContext().getDrawable(R.mipmap.ic_question)!!, "定位", "功能说明", true)
        val appAuthorityNode4 = AppManageData("无线网和蜂窝数据", requireContext().getDrawable(R.mipmap.ic_question)!!, "无线网和蜂窝数据", "功能说明", false)
        lists.add(appAuthorityNode1)
        lists.add(appAuthorityNode2)
        lists.add(appAuthorityNode3)
        lists.add(appAuthorityNode4)
        mAdapter = AppAuthorityManageAdapter(lists)
//        mAdapter.setItemSwitchListener(object : OnItemSwitchListener {
//            override fun onItemCheckedChanged(view: View?, isChecked: Boolean?, position: Int) {
//                lists[position].status = isChecked!!
//            }
//
//        })

        mAdapter.addChildClickViewIds(R.id.btn_switch)
        mAdapter.setOnItemChildCheckChangedListener(object :OnItemChildCheckChangedListener{
            override fun onItemChildCheckChanged(adapter: BaseRecyclerViewAdapter<*, *>, view: CompoundButton, position: Int) {
                if (!view.isPressed) {
                    return
                }
                lists[position].status = view.isChecked
                Toast.makeText(context, "click is $position ,status is ${view.isChecked}", Toast.LENGTH_SHORT).show()
            }
        })
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.revAppManage.layoutManager = linearLayoutManager
        binding.revAppManage.adapter = mAdapter
    }
}