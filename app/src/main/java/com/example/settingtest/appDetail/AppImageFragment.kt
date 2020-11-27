package com.example.settingtest.appDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.settingtest.databinding.FragmentAppImageBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/19
 * com.example.settingtest.AppFragment
 */
class AppImageFragment(private val src: Int) : Fragment() {
    private var _binding: FragmentAppImageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAppImageBinding.inflate(inflater, container, false)
        Glide.with(requireContext()).load(src).centerCrop().into(binding.appImage)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}