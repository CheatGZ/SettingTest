package com.example.settingtest.appDetail

import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.settingtest.Activity.SecondActivity
import com.example.settingtest.Entity.AppImageData
import com.example.settingtest.R
import com.example.settingtest.databinding.ActivityAppDetailBinding
import com.example.settingtest.databinding.TabAppImageBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/18
 * com.example.settingtest.Activity
 */
class AppDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppDetailBinding
    private lateinit var tabLayoutMediator: TabLayoutMediator
    private lateinit var mAdapterAppBigImage: AppBigImageAdapter
    private lateinit var listFragment: MutableList<AppImageData>
    private var isPause = false
    private var curProgress = 0f
    private val maxProgress = 10000f

    /** 模拟APP下载*/
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initAppImageAndVideo()
        initProgressButton()
    }

    private fun initProgressButton() {

        binding.appDownload.progressBackground.setOnClickListener {
            if (countDownTimer != null) {
                if (!isPause) {
                    isPause = true
                    countDownTimer!!.cancel()
                    binding.appDownload.progressText.text = "已暂停"
                } else {//继续播放
                    if (curProgress != 0f) {
                        initDownLoadProgress(curProgress)
                        isPause = false
                    }
                }
            } else {
                initDownLoadProgress(10000f)
            }
        }
        binding.appDownload.progressCancel.setOnClickListener {
            countDownTimer!!.cancel()
            countDownTimer = null
            binding.appDownload.progressText.text = "安装应用"
            binding.appDownload.progressCancel.visibility = View.GONE
            val clipDrawable = binding.appDownload.progressForeground.background as ClipDrawable
            clipDrawable.level = 0
        }

    }

    private fun initDownLoadProgress(progress: Float) {
        val clipDrawable = binding.appDownload.progressForeground.background as ClipDrawable
            binding.appDownload.progressIcon.visibility = View.VISIBLE
            countDownTimer = object : CountDownTimer(progress.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    clipDrawable.level = (maxProgress - millisUntilFinished).toInt()
                    binding.appDownload.progressText.text = "${(((maxProgress  - millisUntilFinished) / maxProgress) * 10000).toInt()} / 10000"
                    curProgress = millisUntilFinished.toFloat()
                    Log.d("kang", "curProgress $curProgress  millisUntilFinished $millisUntilFinished")
                }

                override fun onFinish() {
                    clipDrawable.level = 10000
                    binding.appDownload.progressText.text = "下载完成"
                    binding.appDownload.progressIcon.visibility = View.GONE
                    binding.appDownload.progressCancel.visibility = View.GONE
                    curProgress = 0f
                    countDownTimer = null
                }
            }.start()
            binding.appDownload.progressCancel.visibility = View.VISIBLE
    }

    private fun initView() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAppImageAndVideo() {
        listFragment = ArrayList()
        listFragment.add(AppImageData(AppImageData.VIDEO, R.mipmap.ic_red_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_blue_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_green_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_yellow_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_gray_flag))
        mAdapterAppBigImage = AppBigImageAdapter(supportFragmentManager, lifecycle, listFragment)
        binding.viewPagerAppImage.adapter = mAdapterAppBigImage
        binding.viewPagerAppImage.isUserInputEnabled = false

        tabLayoutMediator = TabLayoutMediator(binding.tabAppImg, binding.viewPagerAppImage) { tab, position ->
            val bindingTab = TabAppImageBinding.inflate(layoutInflater)
            bindingTab.tabImage.setImageResource(listFragment[position].res)
            bindingTab.tabImage.scaleType = ImageView.ScaleType.FIT_XY
//            val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//            bindingTab.root.layoutParams = layoutParams
            tab.customView = bindingTab.root
        }
        tabLayoutMediator.attach()

        binding.viewPagerAppImage.setOnHoverListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_HOVER_ENTER -> {
                    when (binding.viewPagerAppImage.currentItem) {
                        0 -> binding.appForeImage.visibility = View.VISIBLE
                        listFragment.size - 1 -> binding.appRearImage.visibility = View.VISIBLE
                        else -> {
                            binding.appForeImage.visibility = View.VISIBLE
                            binding.appRearImage.visibility = View.VISIBLE
                        }
                    }
                }
                MotionEvent.ACTION_HOVER_EXIT -> {
                    binding.appForeImage.visibility = View.GONE
                    binding.appRearImage.visibility = View.GONE
                }
            }
            false
        }

        binding.appForeImage.setOnClickListener {
            binding.viewPagerAppImage.currentItem = binding.viewPagerAppImage.currentItem - 1
            if (binding.viewPagerAppImage.currentItem == 0) {
                binding.appForeImage.visibility = View.GONE
            }
            binding.appRearImage.visibility = View.VISIBLE
        }

        binding.appRearImage.setOnClickListener {
            binding.viewPagerAppImage.currentItem = binding.viewPagerAppImage.currentItem + 1
            if (binding.viewPagerAppImage.currentItem == listFragment.size - 1) {
                binding.appRearImage.visibility = View.GONE
            }
            binding.appForeImage.visibility = View.VISIBLE
        }
    }


    class AppBigImageAdapter(fragmentManager: FragmentManager,
                             lifecycle: Lifecycle,
                             private val list: MutableList<AppImageData>) : FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return list.size
        }

        override fun createFragment(position: Int): Fragment {
            var fragment = Fragment()
            when (list[position].type) {
                AppImageData.VIDEO -> fragment = AppVideoFragment()
                AppImageData.IMAGE -> fragment = AppImageFragment(list[position].res)
            }
            return fragment
        }
    }
}