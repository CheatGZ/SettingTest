package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.settingtest.databinding.FragmentNavHostCommonBinding
import com.example.settingtest.databinding.FragmentNavHostPrivacyBinding
/**
 * @author zhangyongkang
 * @date 2020/11/4
 * com.example.settingtest.View
 */
class PrivacyNavHostFragment : Fragment() {
    private lateinit var binding: FragmentNavHostPrivacyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNavHostPrivacyBinding.inflate(inflater)
        return binding.root
    }
}