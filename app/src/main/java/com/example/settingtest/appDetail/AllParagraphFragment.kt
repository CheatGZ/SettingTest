package com.example.settingtest.appDetail

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settingtest.Entity.AppParagraphData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.databinding.FragmentAllParagraphBinding
import com.example.settingtest.databinding.TabParagrapgTypeBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/20
 * com.example.settingtest.appDetail
 */
class AllParagraphFragment : Fragment() {
    private var _binding: FragmentAllParagraphBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: AllParagraphAdapter
    private lateinit var mList: MutableList<AppParagraphData>
    private lateinit var allList: MutableList<AppParagraphData>
    private val args: AllParagraphFragmentArgs by navArgs()
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAllParagraphBinding.inflate(inflater)
        /** 初始化基本View */
        initView()
        /** 初始化tab 顺序tab和类型tab */
        initTab()
        /** 初始化recyclerview */
        initAllParagraphView()
        return binding.root
    }

    private fun initView() {
        binding.back.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
    }

    private fun initTab() {
        /** 添加顺序过滤（默认排序  最新发布） */
        binding.filterOrder.setSelectedTabIndicator(null)
        addOrderTab("默认排序", true)
        addOrderTab("最新发布")

        /** 添加顺序过滤tab分割线 */
        val linearLayout: LinearLayout = binding.filterOrder.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_divider)
        linearLayout.dividerPadding = 2
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.height = 20
        linearLayout.layoutParams = layoutParams


        /** 添加类型过滤（全部  推荐  不推荐） */
        binding.filterType.setSelectedTabIndicator(null)
        addTypeTab("全部（20）", true)
        addTypeTab("推荐（20）")
        addTypeTab("不推荐（0）")

        /** 监听事件 */
        binding.filterOrder.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //TODO("Not yet implemented")

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //TODO("Not yet implemented")
            }
        })
        binding.filterType.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        GlobalScope.launch(Dispatchers.Default) {
                            mList.clear()
                            for (i in 0 until allList.size) {
                                mList.add(allList[i])
                            }
                        }
                        mAdapter.notifyDataSetChanged()
                    }
                    1 -> {
                        GlobalScope.launch(Dispatchers.Default) {
                            mList.clear()
                            for (i in 0 until allList.size) {
                                if (allList[i].isCommended) {
                                    mList.add(allList[i])
                                }
                            }
                        }

                        mAdapter.notifyDataSetChanged()
                    }
                    2 -> {
                        GlobalScope.launch(Dispatchers.Default) {
                            mList.clear()
                            for (i in 0 until allList.size) {
                                Log.d("kang tab", "${allList[i].isCommended}")
                                if (!allList[i].isCommended) {
                                    mList.add(allList[i])
                                }
                            }
                        }

                        mAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //TODO("Not yet implemented")
            }

        })
    }

    private fun addOrderTab(text: String, select: Boolean = false) {
        val tab = binding.filterOrder.newTab()
        tab.text = text
        binding.filterOrder.addTab(tab, select)
    }

    private fun addTypeTab(text: String, select: Boolean = false) {
        val bindingTab = TabParagrapgTypeBinding.inflate(layoutInflater)
        val tab = binding.filterType.newTab()
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.width = 70
        layoutParams.height = 30
        bindingTab.root.layoutParams = layoutParams
        bindingTab.paragraphType.text = text
        tab.customView = bindingTab.root
        binding.filterType.addTab(tab, select)
    }

    private fun initAllParagraphView() {
        mList = ArrayList()
        allList = ArrayList()
        for (i in 1..20) {
            mList.add(AppParagraphData("用户ID$i", "${i}天前", i % 2 == 0, getString(R.string.text_collapse), (0..100).random(), (0..100).random(), (0..100).random()))
            allList.add(AppParagraphData("用户ID$i", "${i}天前", i % 2 == 0, getString(R.string.text_collapse), (0..100).random(), (0..100).random(), (0..100).random()))
        }
        mAdapter = AllParagraphAdapter(mList)
        binding.revAllParagraph.run {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        /** 用户点击paragraphPosition出短评，跳转页面后定位并高亮短评1秒 */
        val paragraphPosition = args.paragraphPosition
        if (paragraphPosition >= 0) {
            binding.revAllParagraph.scrollToPosition(paragraphPosition)
            var view:CardView?=null
            val vto: ViewTreeObserver = binding.revAllParagraph.viewTreeObserver
            vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.revAllParagraph.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    view = binding.revAllParagraph.layoutManager!!.findViewByPosition(paragraphPosition) as CardView
                    view!!.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_paragraph_accent)
                    countDownTimer = object : CountDownTimer(1000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                        }

                        override fun onFinish() {
                            view!!.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_paragraph_normal)
                        }
                    }.start()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            countDownTimer = null
        }
    }

    class AllParagraphAdapter(data: MutableList<AppParagraphData>) : BaseRecyclerViewAdapter<AppParagraphData, BaseRecyclerViewHolder>(R.layout.item_all_paragraph, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppParagraphData) {
            holder.setText(R.id.paragraph_info, "${item.userId}·${item.time}")
                    .setText(R.id.paragraph_content, item.Content)
                    .setText(R.id.evaluate_interesting, "有趣 ${item.interestCount}")
                    .setText(R.id.evaluate_useful, "有用 ${item.usefulCount}")
                    .setText(R.id.evaluate_useless, "无用 ${item.uselessCount}")
        }

    }
}