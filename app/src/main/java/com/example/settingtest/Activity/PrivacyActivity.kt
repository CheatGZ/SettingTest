package com.example.settingtest.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.settingtest.databinding.ActivityPrivacyBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
class PrivacyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}