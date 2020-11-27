package com.example.settingtest.appDetail

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.Entity.AppMoreInfoData
import com.example.settingtest.Entity.AppParagraphData
import com.example.settingtest.R
import com.example.settingtest.View.*
import com.example.settingtest.databinding.FragmentAppDetailBinding
import com.example.settingtest.databinding.ItemAppMoreInfoHeadBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/20
 * com.example.settingtest.appDetail
 */
class AppDetailFragment : Fragment() {
    private var _binding: FragmentAppDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapterParagraph: AppParagraphAdapter
    private lateinit var listParagraph: MutableList<AppParagraphData>
    private lateinit var mAdapterMoreInfo: AppMoreInfoAdapter
    private lateinit var listMoreInfo: MutableList<AppMoreInfoData>

    companion object {
        const val MAX_COLLAPSE_LINE = 5
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAppDetailBinding.inflate(inflater, container, false)

        initView()
        initAppDescription()
        initAppParagraph()
        initMoreInfo()
        return binding.root
    }

    private fun initView() {
        binding.allParagraph.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_appDetailFragment_to_allParagraphFragment)
        }
    }

    private fun initAppParagraph() {
        listParagraph = ArrayList()
        for (i in 1..20) {
            listParagraph.add(AppParagraphData("用户ID$i", "${i}天前", i % 2 == 0, getString(R.string.text_collapse), (0..100).random(), (0..100).random(), (0..100).random()))
        }
        mAdapterParagraph = AppParagraphAdapter(listParagraph)
        mAdapterParagraph.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int) {
                val action=AppDetailFragmentDirections.actionAppDetailFragmentToAllParagraphFragment(position)
                view.findNavController().navigate(action)
            }
        })

        val itemDecoration = MyItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        itemDecoration.setDividerHeight(8)
        itemDecoration.setColor(R.color.gray_28)
        binding.revParagraph.run {
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapterMoreInfo
        }
    }

    private fun initAppDescription() {
        TextViewSuffixWrapper(binding.appDescription, MAX_COLLAPSE_LINE).apply wrapper@{
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
            val foregroundColorSpan = ForegroundColorSpan(requireContext().getColor(R.color.green_accent))
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
                })
                collapse(false)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    class AppParagraphAdapter(data: MutableList<AppParagraphData>) : BaseRecyclerViewAdapter<AppParagraphData, BaseRecyclerViewHolder>(R.layout.item_app_paragraph, data) {
        companion object {
            const val MAX_LINE = 2
        }

        override fun convert(holder: BaseRecyclerViewHolder, item: AppParagraphData) {
            holder.setText(R.id.paragraph_info, "${item.userId}·${item.time}")
                    .setText(R.id.paragraph_content, item.Content)
                    .setText(R.id.evaluate_interesting, "有趣 ${item.interestCount}")
                    .setText(R.id.evaluate_useful, "有用 ${item.usefulCount}")
                    .setText(R.id.evaluate_useless, "无用 ${item.uselessCount}")
            val textView = holder.getView<TextView>(R.id.paragraph_content)
            textView.maxLines = MAX_LINE
        }
    }

    class AppMoreInfoAdapter(data: MutableList<AppMoreInfoData>) : BaseRecyclerViewAdapter<AppMoreInfoData, BaseRecyclerViewHolder>(R.layout.item_app_more_info, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppMoreInfoData) {
            holder.setText(R.id.title, item.title)
                    .setText(R.id.content, item.content)
        }
    }

}