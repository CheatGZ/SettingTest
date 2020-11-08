package com.example.settingtest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.settingtest.databinding.FragmentPrivacyStatementBinding;

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
public class PrivacyStatementFragment extends Fragment {
    private FragmentPrivacyStatementBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentPrivacyStatementBinding.inflate(inflater);
        binding.btnBack.setOnClickListener(v->{
            NavHostFragment.findNavController(PrivacyStatementFragment.this).navigateUp();
        });
        return binding.getRoot();
    }
}
