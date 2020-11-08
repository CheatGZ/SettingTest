package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.settingtest.databinding.FragmentLabBinding

/**
 * @author zhangyongkang
 * @date 2020/11/5
 * com.example.settingtest.View
 */
class LabFragment : Fragment() {
    private var expandFlag = false
    private lateinit var binding: FragmentLabBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLabBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.labelDevMode.btnSwitch.isChecked = true
        binding.labelDevMode.txtTitle.text = "开发者模式"
        binding.labelDevMode.txtDescription.text = "功能说明"
        binding.labelDevMode.btnSwitch.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                binding.labelStopVirtual.layoutRoot.visibility = View.VISIBLE
                binding.labelThirdApk.layoutRoot.visibility = View.VISIBLE
            } else {
                binding.labelStopVirtual.layoutRoot.visibility = View.GONE
                binding.labelThirdApk.layoutRoot.visibility = View.GONE
            }
        }

        binding.labelStopVirtual.txtTitle.text = "停用虚拟围栏"
        binding.labelStopVirtual.txtDescription.text = "功能说明"

        binding.labelThirdApk.txtTitle.text = "安装第三方应用"
        binding.labelThirdApk.txtDescription.text = "功能说明"

        binding.labelAppInstall.txtTitle.text = "未知来源应用安装"
        binding.labelAppInstall.txtDescription.text = "功能说明"

        binding.labelAutoWake.txtTitle.text = "自动唤醒、熄灭屏幕"
        binding.labelAutoWake.txtDescription.text = "功能说明"

    }
}