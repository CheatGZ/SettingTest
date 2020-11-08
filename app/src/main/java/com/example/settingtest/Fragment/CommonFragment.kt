package com.example.settingtest.Fragment

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.settingtest.R
import com.example.settingtest.databinding.FragmentCommonBinding
import com.example.settingtest.databinding.ViewLabelBinding


class CommonFragment : Fragment() {
    private lateinit var binding: FragmentCommonBinding
    private val onClickListener = View.OnClickListener {
        when (it) {
//            binding.labelAboutMachine-> {
//                NavHostFragment.findNavController(this).navigate(R.id.action_commonFragment_to_aboutmachineFragment)
//            }
            binding.labelSystemInfo.layoutRoot -> {
                NavHostFragment.findNavController(this).navigate(R.id.action_commonFragment_to_systemInfoFragment)
            }
            binding.labelReset.layoutRoot -> {
                Toast.makeText(context, "点击Label的id：${it.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        when (buttonView) {
            binding.labelVoiceControl.btnSwitch -> {
                Toast.makeText(context, "切换Switch的状态：${buttonView.id} $isChecked", Toast.LENGTH_SHORT).show()
            }
            binding.labelEyeMode.btnSwitch -> {
                Toast.makeText(context, "切换Switch的状态：${buttonView.id} $isChecked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommonBinding.inflate(inflater)


        initView()
        return binding.root
    }

    //    初始化界面，赋值并设置监听事件
    private fun initView() {
//        binding.labelAboutMachine.setOnClickListener(onClickListener)

        binding.labelSystemInfo.txtTitle.text = "系统信息"
        binding.labelSystemInfo.txtDescription.text = "QIYU OS 5.0"
        binding.labelSystemInfo.layoutRoot.setOnClickListener(onClickListener)

        binding.labelVoiceControl.txtTitle.text = "语音控制"
        binding.labelVoiceControl.txtDescription.text = "功能描述"
        binding.labelVoiceControl.btnSwitch.setOnCheckedChangeListener(onCheckedChangeListener)

        binding.labelEyeMode.txtTitle.text = "护眼模式"
        binding.labelEyeMode.txtDescription.visibility = View.GONE
        binding.labelEyeMode.btnSwitch.setOnCheckedChangeListener(onCheckedChangeListener)

        binding.labelLuminance.txtTitle.text = "亮度调节"
        binding.labelLuminance.txtDescription.text = "功能描述，调节屏幕亮度"
        binding.labelLuminance.imgGoTo.visibility = View.GONE
        binding.slideLuminance.value = getScreenBrightness().toFloat()
        binding.slideLuminance.addOnChangeListener { slider, value, fromUser ->
            setScreenBrightness(value.toInt())
        }

        binding.labelTheme.txtTitle.text = "虚拟主题"
        binding.labelTheme.txtDescription.text = "选择在奇遇Home中默认使用的主题"
        binding.labelTheme.imgGoTo.visibility = View.GONE
        binding.themeOne.setOnClickListener {
            binding.themeOneUsing.rootThemeUsing.visibility = View.VISIBLE
            binding.themeTwoUsing.rootThemeUsing.visibility = View.GONE
        }
        binding.themeTwo.setOnClickListener {
            binding.themeOneUsing.rootThemeUsing.visibility = View.GONE
            binding.themeTwoUsing.rootThemeUsing.visibility = View.VISIBLE
        }

        binding.labelEnergy.txtTitle.text = "电源管理"
        binding.labelEnergy.txtDescription.text = "功能描述，设置自动关机时间"
        binding.labelEnergy.imgGoTo.visibility = View.GONE


        binding.labelReset.txtTitle.text = "恢复出厂设置"
        binding.labelReset.txtDescription.visibility = View.GONE
        binding.labelReset.layoutRoot.setOnClickListener(onClickListener)

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

    companion object {
        val instance: CommonFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommonFragment()
        }
    }
}