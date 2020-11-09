package com.example.settingtest.Activity

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.settingtest.Adapter.ViewPager2FragmentAdapter
import com.example.settingtest.Fragment.CommonNavHostFragment
import com.example.settingtest.Fragment.LabFragment
import com.example.settingtest.Fragment.PrivacyNavHostFragment
import com.example.settingtest.R
import com.example.settingtest.Utils.HtmlTagHandler
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView() {
        /** 修改radibutton样式*/
        val vto: ViewTreeObserver = binding.rbCommon.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.rbCommon.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val drawUser = binding.rbUser.compoundDrawables
                val drawableCommon = binding.rbCommon.compoundDrawables
                val drawablePrivacy = binding.rbPrivacy.compoundDrawables
                val drawableLab = binding.rbLab.compoundDrawables
                val rec = Rect(0, 0, drawableCommon[0].minimumWidth * 2 / 5, drawableCommon[0].minimumHeight * 2 / 5)
                drawUser[1].bounds = Rect(0, 0, (drawUser[1].minimumWidth * 1.5).toInt(), (drawUser[1].minimumHeight * 1.5).toInt())
                drawableCommon[0].bounds = rec
                drawablePrivacy[0].bounds = rec
                drawableLab[0].bounds = rec
                binding.rbUser.setCompoundDrawables(null, drawUser[1], null, null)
                binding.rbCommon.setCompoundDrawables(drawableCommon[0], null, null, null)
                binding.rbPrivacy.setCompoundDrawables(drawablePrivacy[0], null, null, null)
                binding.rbLab.setCompoundDrawables(drawableLab[0], null, null, null)
            }
        })


        binding.rbUser.text = Html.fromHtml("<p><myfont size='80px'>用户ID</font></p> <p><myfont size='40px' >有效期至2021.03.04</font></p>",
                Html.FROM_HTML_MODE_COMPACT,
                null,
                HtmlTagHandler("myfont"))

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