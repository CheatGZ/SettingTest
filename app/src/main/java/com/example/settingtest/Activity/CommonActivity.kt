package com.example.settingtest.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.settingtest.databinding.ActivityCommonBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/5
 * SettingTest
 */
class CommonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}