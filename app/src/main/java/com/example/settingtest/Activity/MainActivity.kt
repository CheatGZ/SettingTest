package com.example.settingtest.Activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.settingtest.Adapter.ViewPager2FragmentAdapter
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
    private  var fragmentPosition:Int=0

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initTabView()
    }

    private fun initView() {
//        list = ArrayList()
//        list.add(UserNavHostFragment())
//        list.add(CommonNavHostFragment())
//        list.add(PrivacyNavHostFragment())
//        list.add(LabFragment())
//        mAdapter = ViewPager2FragmentAdapter(supportFragmentManager, lifecycle, list)
//        binding.viewPager2Fragment.adapter = mAdapter
//        binding.viewPager2Fragment.isUserInputEnabled = false

        navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_nav) as NavHostFragment
        navController = navHostFragment.navController
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initTabView() {
//        /** 修改radibutton样式*/
//        val vto: ViewTreeObserver = binding.rbCommon.viewTreeObserver
//        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                binding.rbCommon.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                SetDrawableStyle.setRoundDrawableTop(this@MainActivity, R.mipmap.ic_test, binding.rbUser, 300, 0, 50)
//                val drawableCommon = binding.rbCommon.compoundDrawables
//                val drawablePrivacy = binding.rbPrivacy.compoundDrawables
//                val drawableLab = binding.rbLab.compoundDrawables
//                val rec = Rect(0, 0, drawableCommon[0].minimumWidth * 2 / 5, drawableCommon[0].minimumHeight * 2 / 5)
//                drawableCommon[0].bounds = rec
//                drawablePrivacy[0].bounds = rec
//                drawableLab[0].bounds = rec
//                binding.rbCommon.setCompoundDrawables(drawableCommon[0], null, null, null)
//                binding.rbPrivacy.setCompoundDrawables(drawablePrivacy[0], null, null, null)
//                binding.rbLab.setCompoundDrawables(drawableLab[0], null, null, null)
//            }
//        })
//
//
//        binding.rbUser.text = Html.fromHtml("<p><myfont size='80px'>用户ID</font></p> <p><myfont size='40px' >有效期至2021.03.04</font></p>",
//                Html.FROM_HTML_MODE_COMPACT,
//                null,
//                HtmlTagHandler("myfont"))


        binding.rbUser.isChecked = true
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->

            Log.d("Kang onClick","${System.currentTimeMillis()}")
            when (checkedId) {
                R.id.rb_user -> navController.navigate(R.id.userFragment)
                R.id.rb_common -> navController.navigate(R.id.commonFragment)
//                R.id.rb_connect -> navController.navigate(R.id.connectFragment)
//                R.id.rb_controller -> navController.navigate(R.id.generalFragment)
//                R.id.rb_guard -> navController.navigate(R.id.generalFragment)
                R.id.rb_privacy -> navController.navigate(R.id.privacyFragment)
//                R.id.rb_lab -> navController.navigate(R.id.)
            }
        }

//        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
//            when (checkedId) {
//                R.id.rb_user -> binding.viewPager2Fragment.setCurrentItem(0, false)
//                R.id.rb_common -> binding.viewPager2Fragment.setCurrentItem(1, false)
//                R.id.rb_privacy -> binding.viewPager2Fragment.setCurrentItem(2, false)
//                R.id.rb_lab -> binding.viewPager2Fragment.setCurrentItem(3, false)
//            }
//            fragmentPosition=checkedId
//        }
    }

}