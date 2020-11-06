package com.example.settingtest.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.settingtest.Adapter.FragmentAdapter
import com.example.settingtest.databinding.ActivityMainBinding


/**
 * @author zhangyongkang01_sx
 * @date 2020/11/5
 * SettingTest
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter:FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
            }
    private fun initView() {
        mAdapter= FragmentAdapter(this.supportFragmentManager)
        binding.viewPager.adapter=mAdapter
        binding.tabLayoutNav.setupWithViewPager(binding.viewPager)
    }
}