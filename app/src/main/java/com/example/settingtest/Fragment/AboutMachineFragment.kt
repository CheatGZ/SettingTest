package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settingtest.Adapter.ProportionAdapter
import com.example.settingtest.Adapter.ProportionLabelAdapter
import com.example.settingtest.Entity.ProportionData
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentAboutMachineBinding

/**
 * @author zhangyongkang
 * @date 11/8/20
 * com.example.settingtest.View
 */
class AboutMachineFragment : Fragment() {
    private lateinit var binding: FragmentAboutMachineBinding
    private lateinit var mAdapter: ProportionAdapter
    private lateinit var mLabelAdapter: ProportionLabelAdapter
    private lateinit var list: MutableList<ProportionData>
    private var maxStore: Float = 0f
    private var maxWidth: Int = 0

    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.btnBack -> NavHostFragment.findNavController(this).navigateUp()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutMachineBinding.inflate(inflater)
        val vto: ViewTreeObserver = binding.revProportion.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.revProportion.viewTreeObserver.removeOnGlobalLayoutListener(this)
                maxWidth = binding.revProportion.width
                initView()
            }
        })
        return binding.root
    }

    private fun initView() {
        maxStore = 100f
        list = ArrayList()
        list.add(ProportionData("001", "系统", 40f, ProportionData.COLOR_GREEN))
        list.add(ProportionData("002", "应用", 20f, ProportionData.COLOR_BLUE))
        list.add(ProportionData("003", "视频", 10f, ProportionData.COLOR_WHITE))
        list.add(ProportionData("004", "图片", 5f, ProportionData.COLOR_ORANGE))
        list.add(ProportionData("005", "其他", 5f, ProportionData.COLOR_RED))
        list.removeAt(list.size - 1)
        binding.btnBack.setOnClickListener(onClickListener)

        binding.labelStoreManage.setGotoClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_aboutMachineFragment_to_storeManageFragment)
        })


        mAdapter = ProportionAdapter(maxStore, maxWidth, list)
        binding.revProportion.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.revProportion.adapter = mAdapter

        mLabelAdapter = ProportionLabelAdapter(list)
        binding.revProportionLabel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.revProportionLabel.adapter = mLabelAdapter

    }
}