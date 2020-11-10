package com.example.settingtest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentLogInBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/10
 * com.example.settingtest.Fragment
 */
class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentLogInBinding.inflate(inflater)
        initView()
        return binding.root
    }
    private fun initView(){
        binding.btnLogIn.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_logInFragment_to_userFragment)
//            onDestroy()
        }
    }
}