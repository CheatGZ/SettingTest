package com.example.settingtest.Fragment

import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentCommonBinding


class CommonFragment : Fragment() {
    private var _binding: FragmentCommonBinding? = null
    private val binding get()=_binding!!
    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.labelAboutMachine -> {
                NavHostFragment.findNavController(this).navigate(R.id.action_commonFragment_to_aboutmachineFragment)
            }
            binding.labelSystemInfo -> {
                NavHostFragment.findNavController(this).navigate(R.id.action_commonFragment_to_systemInfoFragment)
            }
            binding.labelReset -> {
                Toast.makeText(context, "点击Label的id：${it.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Kang onCreate", "${System.currentTimeMillis()}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        Log.d("Kang onCreateView befor", "${System.currentTimeMillis()}")
        _binding = FragmentCommonBinding.inflate(inflater, container, false)
        Log.d("Kang onCreateView after", "${System.currentTimeMillis()}")

        initView()
        return binding.root
    }

    //    初始化界面，赋值并设置监听事件
    private fun initView() {
        binding.labelAboutMachine.setOnClickListener(onClickListener)
        binding.labelSystemInfo.setOnClickListener(onClickListener)
        binding.labelVoiceControl.setSwitchStatusChangedListener {
            CompoundButton.OnCheckedChangeListener { btn, isChecked ->
                Toast.makeText(requireContext(), "$isChecked", Toast.LENGTH_SHORT).show()
            }
        }

        binding.labelEyeMode.setSwitchStatusChangedListener {
            CompoundButton.OnCheckedChangeListener { btn, isChecked ->
                Toast.makeText(requireContext(), "$isChecked", Toast.LENGTH_SHORT).show()
            }
        }

        binding.slideLuminance.addOnChangeListener { slider, value, fromUser ->
            setScreenBrightness(value.toInt())
        }

        binding.themeOne.setOnClickListener {
            binding.themeOneUsing.rootThemeUsing.visibility = View.VISIBLE
            binding.themeTwoUsing.rootThemeUsing.visibility = View.GONE
        }
        binding.themeTwo.setOnClickListener {
            binding.themeOneUsing.rootThemeUsing.visibility = View.GONE
            binding.themeTwoUsing.rootThemeUsing.visibility = View.VISIBLE
        }
        binding.labelReset.setOnClickListener(onClickListener)

    }

    private fun setScreenBrightness(value: Int) {
        try {
            Settings.System.putInt(requireContext().contentResolver, Settings.System.SCREEN_BRIGHTNESS, value)
        } catch (e: java.lang.Exception) {

        }
    }

    private fun getScreenBrightness(): Int {
        var value = 255
        try {
            value = Settings.System.getInt(requireContext().contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Exception) {

        }
        return value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val instance: CommonFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommonFragment()
        }
    }
}