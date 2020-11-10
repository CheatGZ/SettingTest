package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.settingtest.databinding.FragmentNavHostUserBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/10
 * com.example.settingtest.Fragment
 */
class UserNavHostFragment :Fragment(){
    private lateinit var binding: FragmentNavHostUserBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentNavHostUserBinding.inflate(inflater)
        return binding.root
    }
}