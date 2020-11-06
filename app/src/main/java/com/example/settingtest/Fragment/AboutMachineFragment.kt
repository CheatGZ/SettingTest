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
import com.example.settingtest.Node.ProportionNode
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentAboutMachineBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AboutMachineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutMachineFragment : Fragment() {
    private lateinit var binding: FragmentAboutMachineBinding
    private lateinit var mAdapter: ProportionAdapter
    private lateinit var mLabelAdapter: ProportionLabelAdapter
    private lateinit var list: MutableList<ProportionNode>
    private var maxStore: Float = 0f
    private var maxWidth: Int = 0

    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.btnBack -> NavHostFragment.findNavController(this).popBackStack()
            binding.labelStoreManage.layoutGoto -> NavHostFragment.findNavController(this).navigate(R.id.action_aboutMachineFragment_to_storeManageFragment)
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
        list.add(ProportionNode("001", "系统", 40f, ProportionNode.COLOR_GREEN))
        list.add(ProportionNode("002", "应用", 20f, ProportionNode.COLOR_BLUE))
        list.add(ProportionNode("003", "视频", 10f, ProportionNode.COLOR_WHITE))
        list.add(ProportionNode("004", "图片", 5f, ProportionNode.COLOR_ORANGE))
        list.add(ProportionNode("005", "其他", 5f, ProportionNode.COLOR_RED))
        list.removeAt(list.size - 1)
        binding.btnBack.setOnClickListener(onClickListener)

        binding.labelStoreManage.txtTitle.text = "设备存储空间"
        binding.labelStoreManage.txtDescription.text = "16.5G可用/128G"
        binding.labelStoreManage.txtNotify.text = "管理"
        binding.labelStoreManage.layoutGoto.setOnClickListener(onClickListener)


        mAdapter = ProportionAdapter(maxStore, maxWidth, list)
        binding.revProportion.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.revProportion.adapter = mAdapter

        mLabelAdapter = ProportionLabelAdapter(list)
        binding.revProportionLabel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.revProportionLabel.adapter = mLabelAdapter



        binding.labelHardwareInfo.txtTitle.text = "一体机硬件信息"
        binding.labelHardwareInfo.txtDescription.visibility = View.VISIBLE
        binding.labelHardwareInfo.imgGoTo.visibility = View.GONE
    }
}