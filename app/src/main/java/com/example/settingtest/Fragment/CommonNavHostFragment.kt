package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.settingtest.databinding.FragmentNavHostCommonBinding

/**
 * @author zhangyongkang
 * @date 2020/11/6
 * com.example.settingtest.View
 */
class CommonNavHostFragment : Fragment() {
    private lateinit var binding: FragmentNavHostCommonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNavHostCommonBinding.inflate(inflater)
        return binding.root
    }
}