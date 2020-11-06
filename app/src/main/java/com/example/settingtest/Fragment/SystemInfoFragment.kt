package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.settingtest.databinding.FragmentSystemInfoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SystemInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SystemInfoFragment : Fragment() {
    private lateinit var binding: FragmentSystemInfoBinding
    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.btnBack -> NavHostFragment.findNavController(this).popBackStack()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSystemInfoBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.btnBack.setOnClickListener(onClickListener)

        binding.layoutInfo.txtTitle.text="QIYU OS"
        binding.layoutInfo.txtDescription.visibility=View.GONE
        binding.layoutInfo.txtDescription2.visibility=View.GONE
        binding.layoutInfo.btnOne.text="恢复出厂设置"
        binding.layoutInfo.btnTwo.text="检查更新"
        binding.layoutInfo.txtVersion1Title.text="一体机系统版本"
        binding.layoutInfo.txtVersion2Title.text="安卓系统版本"
        binding.layoutInfo.txtVersion1.text="奇遇设备名称"
        binding.layoutInfo.txtVersion2.text="xxxxx"

        binding.layoutVersion.txtTitle.text="手柄信息"
        binding.layoutVersion.txtDescription.text="左手柄信息：已连接"
        binding.layoutVersion.txtDescription2.text="右手柄信息：已连接"
        binding.layoutVersion.btnOne.text="检查更新"
        binding.layoutVersion.btnTwo.visibility=View.GONE
        binding.layoutVersion.txtVersion1Title.text="控制器版本"
        binding.layoutVersion.txtVersion2Title.text="CHIRP版本"
        binding.layoutVersion.txtVersion1.text="xxxx"
        binding.layoutVersion.txtVersion2.text="xxxxxx"

    }
}