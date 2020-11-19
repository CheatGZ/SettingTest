package com.example.settingtest.Activity

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.settingtest.AppFragment.AppImageFragment
import com.example.settingtest.AppFragment.AppVideoFragment
import com.example.settingtest.Entity.AppImageData
import com.example.settingtest.Entity.AppMoreInfoData
import com.example.settingtest.Entity.AppParagraphData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.View.MyItemDecoration
import com.example.settingtest.View.TextViewSuffixWrapper
import com.example.settingtest.databinding.ActivityAppDetailBinding
import com.example.settingtest.databinding.ItemAppMoreInfoHeadBinding
import com.example.settingtest.databinding.TabAppImageBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/18
 * com.example.settingtest.Activity
 */
class AppDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppDetailBinding
    private lateinit var mAdapterImage: AppImageAdapter
    private lateinit var listAppImage: MutableList<AppImageData>
    private lateinit var mAdapterParagraph: AppParagraphAdapter
    private lateinit var listParagraph: MutableList<AppParagraphData>
    private lateinit var mAdapterMoreInfo: AppMoreInfoAdapter
    private lateinit var listMoreInfo: MutableList<AppMoreInfoData>
    private lateinit var mAdapterAppBigImage: AppBigImageAdapter
    private lateinit var listFragment: MutableList<AppImageData>
    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        initAppImageAndVideo()
        initAppDescription()
        initAppParagraph()
        initMoreInfo()
    }

    private fun initAppImageAndVideo() {
//        listAppImage = ArrayList()
//        for (i in 0..4) {
//            listAppImage.add(AppImageData(R.mipmap.ic_launcher))
//        }
//        val itemDecoration = MyItemDecoration(this, RecyclerView.HORIZONTAL)
//        itemDecoration.setColor(R.color.gray_1c)
//        itemDecoration.setDividerHeight(1)
//        itemDecoration.setMargin(1)
//        mAdapterImage = AppImageAdapter(listAppImage)
//        binding.revAppImg.runCatching {
//            addItemDecoration(itemDecoration)
//            layoutManager = LinearLayoutManager(this@AppDetailActivity, LinearLayoutManager.HORIZONTAL, false)
//            adapter = mAdapterImage
//        }
        listFragment = ArrayList()
        listFragment.add(AppImageData(AppImageData.VIDEO, R.mipmap.ic_red_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_blue_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_green_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_yellow_flag))
        listFragment.add(AppImageData(AppImageData.IMAGE, R.mipmap.ic_gray_flag))
        mAdapterAppBigImage = AppBigImageAdapter(supportFragmentManager, lifecycle, listFragment)
        binding.viewPagerAppImage.adapter = mAdapterAppBigImage
        binding.viewPagerAppImage.isUserInputEnabled = false

        tabLayoutMediator = TabLayoutMediator(binding.tabAppImg, binding.viewPagerAppImage, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val bindingTab = TabAppImageBinding.inflate(layoutInflater)
            bindingTab.tabImage.setImageResource(listFragment[position].res)
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            layoutParams.height = 150
            bindingTab.root.layoutParams = layoutParams
            tab.customView = bindingTab.root
        })
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

    private fun initAppParagraph() {
        listParagraph = ArrayList()
        for (i in 1..20) {
            listParagraph.add(AppParagraphData("用户ID$i", "${i}天前", true, getString(R.string.text_collapse), (0..100).random(), (0..100).random(), (0..100).random()))
        }
        mAdapterParagraph = AppParagraphAdapter(listParagraph)
        val itemDecoration = MyItemDecoration(this, RecyclerView.HORIZONTAL)
        itemDecoration.setDividerHeight(8)
        itemDecoration.setColor(R.color.gray_28)
        binding.revParagraph.run {
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(this@AppDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapterParagraph
        }
    }

    private fun initMoreInfo() {
        listMoreInfo = ArrayList()
        listMoreInfo.add(AppMoreInfoData("玩家人数", "单人"))
        listMoreInfo.add(AppMoreInfoData("游玩控件", "站姿"))
        listMoreInfo.add(AppMoreInfoData("联网方式", "单机"))
        listMoreInfo.add(AppMoreInfoData("玩开发商", "爱奇艺"))
        listMoreInfo.add(AppMoreInfoData("应用大小", "1.3G"))
        listMoreInfo.add(AppMoreInfoData("版本号", "1.3G"))

        mAdapterMoreInfo = AppMoreInfoAdapter(listMoreInfo)
        val binding2 = ItemAppMoreInfoHeadBinding.inflate(layoutInflater)
        mAdapterMoreInfo.addHeaderView(binding2.root)
        binding.revMoreInfo.run {
            layoutManager = LinearLayoutManager(this@AppDetailActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapterMoreInfo
        }
    }

    private fun initAppDescription() {
        TextViewSuffixWrapper(binding.appDescription, 5).apply wrapper@{
            val collapseText = SpannableStringBuilder()
            val contentText = getString(R.string.text_collapse)
            val collapseSuffix = "收起"
            collapseText.append(contentText)
            collapseText.append(collapseSuffix)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    this@wrapper.collapse()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            val foregroundColorSpan = ForegroundColorSpan(this@AppDetailActivity.getColor(R.color.green_accent))
            collapseText.run {
                setSpan(clickableSpan, contentText.length, contentText.length + collapseSuffix.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(foregroundColorSpan, contentText.length, contentText.length + collapseSuffix.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            this.mainContent = collapseText
            this.suffix = "...更多"
            this.suffix?.apply {
                suffixColor("...".length, this.length, R.color.green_accent, listener = {
                    if (this@wrapper.isCollapsed) {
                        this@wrapper.expand()
                    }
                    Log.d("kang suffix", "suffix 5")
                })
                collapse(false)
            }
        }
    }

    class AppImageAdapter(data: MutableList<AppImageData>) : BaseRecyclerViewAdapter<AppImageData, BaseRecyclerViewHolder>(R.layout.item_app_image, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppImageData) {
            holder.setImageResource(R.id.app_img, item.res)
        }
    }

    class AppParagraphAdapter(data: MutableList<AppParagraphData>) : BaseRecyclerViewAdapter<AppParagraphData, BaseRecyclerViewHolder>(R.layout.item_app_paragraph, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppParagraphData) {
            holder.setText(R.id.paragraph_info, "${item.userId}·${item.time}")
                    .setText(R.id.paragraph_content, item.Content)
                    .setText(R.id.evaluate_interesting, "有趣 ${item.interestCount}")
                    .setText(R.id.evaluate_useful, "有用 ${item.usefulCount}")
                    .setText(R.id.evaluate_useless, "无用 ${item.uselessCount}")
        }
    }

    class AppMoreInfoAdapter(data: MutableList<AppMoreInfoData>) : BaseRecyclerViewAdapter<AppMoreInfoData, BaseRecyclerViewHolder>(R.layout.item_app_more_info, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppMoreInfoData) {
            holder.setText(R.id.title, item.title)
                    .setText(R.id.content, item.content)
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