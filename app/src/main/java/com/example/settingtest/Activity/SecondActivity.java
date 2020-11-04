package com.example.settingtest.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.settingtest.databinding.ActivityMainBinding;
import com.example.settingtest.databinding.ActivitySecondBinding;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
