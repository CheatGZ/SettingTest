package com.example.settingtest.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.settingtest.Adapter.ViewPager2FragmentAdapter
import com.example.settingtest.Fragment.CommonNavHostFragment
import com.example.settingtest.Fragment.LabFragment
import com.example.settingtest.Fragment.PrivacyNavHostFragment
import com.example.settingtest.R
import com.example.settingtest.databinding.ActivityMainBinding


/**
 * @author zhangyongkang01_sx
 * @date 2020/11/5
 * SettingTest
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: ViewPager2FragmentAdapter
    private lateinit var list: MutableList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        list = ArrayList()
        list.add(CommonNavHostFragment())
        list.add(PrivacyNavHostFragment())
        list.add(LabFragment())
        mAdapter = ViewPager2FragmentAdapter(supportFragmentManager, lifecycle, list)
        binding.viewPager2.adapter = mAdapter
        binding.viewPager2.isUserInputEnabled = false
        binding.rbCommon.isChecked = true

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.rb_common -> binding.viewPager2.setCurrentItem(0, false)
                R.id.rb_privacy -> binding.viewPager2.setCurrentItem(1, false)
                R.id.rb_lab -> binding.viewPager2.setCurrentItem(2, false)
            }
        }
    }
}