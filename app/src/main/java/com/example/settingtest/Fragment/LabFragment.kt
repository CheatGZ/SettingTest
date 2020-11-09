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
        binding.labelDevMode.setSwitchStatusChangedListener { compoundButton, b ->
            if (b) {
                binding.labelStopVirtual.visibility = View.VISIBLE
                binding.labelThirdApk.visibility = View.VISIBLE
            } else {
                binding.labelStopVirtual.visibility = View.GONE
                binding.labelThirdApk.visibility = View.GONE
            }
        }
    }
}