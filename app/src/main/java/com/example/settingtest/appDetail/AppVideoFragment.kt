package com.example.settingtest.appDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.settingtest.databinding.FragmentAppVideoBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/19
 * com.example.settingtest.AppFragment
 */
class AppVideoFragment : Fragment() {
    private var _binding: FragmentAppVideoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAppVideoBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView(){
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}