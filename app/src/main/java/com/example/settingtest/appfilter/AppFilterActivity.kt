package com.example.settingtest.appfilter

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.settingtest.Entity.AppCommendData
import com.example.settingtest.R
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.View.ChipsChosen
import com.example.settingtest.databinding.ActivityAppFilterBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.math.abs

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/23
 * com.example.settingtest.appfilter
 */
class AppFilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppFilterBinding

    /** chips选择标签 */
    private lateinit var mChipsSort: MutableList<String>
    private lateinit var mChipsType: MutableList<String>
    private lateinit var mChipsLabel: MutableList<String>
    private lateinit var mChipsGrade: MutableList<String>

    /** recyclerView app*/
    private lateinit var mList: MutableList<AppCommendData>
    private lateinit var mAdapter: AppFilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initChips()
        initRecyclerView()
        initView()
    }
    private fun initView(){
        binding.toolBarBackground.background.alpha=0
        binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            val moveDistance=40f+binding.scrollViewSort.height+binding.scrollViewType.height+binding.scrollViewLabel.height+binding.scrollViewGrade.height
            Log.d("kang", "binding.scrollViewSort.height ${binding.scrollViewSort.height}  moveDistance$moveDistance")
            toolBarAlphaChange(scrollY, moveDistance)
        }
    }

    private fun initChips() {
        mChipsSort = ArrayList()
        mChipsType = ArrayList()
        mChipsLabel = ArrayList()
        mChipsGrade = ArrayList()
        mChipsSort.run {
            add("综合排序")
            add("排序1")
            add("排序2")
        }
        mChipsType.run {
            add("全部类型")
            add("应用")
            add("游戏")
        }
        mChipsLabel.run {
            add("全部标签")
            add("动作")
            add("射击")
            add("冒险")
            add("飞行")
            add("竞技")
            add("卡牌")
            add("策略")
            add("塔防")
            add("学习")
            add("办公")
            add("绘图")
            add("绘图2")
            add("绘图3")
            add("绘图4")
            add("绘图5")
            add("绘图6")
            add("绘图7")
            add("绘图8")
        }
        mChipsGrade.run {
            add("不限评分")
            add("好评如潮")
            add("特别好评")
            add("多半好评")
            add("褒贬不一")
            add("不推荐")
        }
        addChip(binding.chipSortChosen, mChipsSort, binding.chipGroupSort)
        addChip(binding.chipTypeChosen, mChipsType, binding.chipGroupType)
        addChip(binding.chipLabelChosen, mChipsLabel, binding.chipGroupLabel)
        addChip(binding.chipGradeChosen, mChipsGrade, binding.chipGroupGrade)
    }

    private fun addChip(chipsChosen: ChipsChosen, list: MutableList<String>, chipGroup: ChipGroup) {
        for (i in list.indices) {
            val chip = Chip(this)

            chip.text = list[i]
            chip.textSize=16f
            chip.chipBackgroundColor = ContextCompat.getColorStateList(this, R.color.selector_app_filter_chip)
            chip.setTextColor(ContextCompat.getColorStateList(this, R.color.selector_app_filter_chip_text))
            chip.textStartPadding = 8F
            chip.textEndPadding = 8F
            chip.isCheckable = true
            chip.isCheckedIconVisible = false

            chipGroup.addView(chip)
        }
        chipGroup.check(chipGroup[0].id)
        chipsChosen.setChipChosen(list[0], 0)
        chipsChosen.setBtnDeleteClickListener {
            chipsChosen.setChipChosen(list[0], 0)
            chipGroup.check(chipGroup[0].id)
        }
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId==-1){
                group.check(group[chipsChosen.getChipChosenPosition()].id)
                return@setOnCheckedChangeListener
            }
            for (i in list.indices) {
                if (group[i].id == checkedId) {
                    chipsChosen.setChipChosen(list[i], i)
                    //TODO recyclerview refresh
                    break
                }
            }
        }
    }

    private fun initRecyclerView() {
        mList = ArrayList()
        for (i in 1..100) {
            mList.add(AppCommendData("", R.mipmap.ic_launcher, "应用$i", "推荐理由$i"))
        }
        mAdapter = AppFilterAdapter(mList)
        binding.revApp.layoutManager = GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false)
        binding.revApp.adapter = mAdapter
    }

    class AppFilterAdapter(data: MutableList<AppCommendData>) : BaseRecyclerViewAdapter<AppCommendData, BaseRecyclerViewHolder>(R.layout.item_app_filter, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppCommendData) {
            holder.run {
                setText(R.id.txt_title, item.name)
                setText(R.id.txt_description, item.description)
            }
            val imageView = holder.getView<ImageView>(R.id.img_icon)
            val url = "data:image/png;base64,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"
            Glide.with(context).load(url).centerCrop().placeholder(R.mipmap.ic_launcher).into(imageView)
        }
    }

    private fun toolBarAlphaChange(dy: Int, moveDistance: Float) { //设置标题栏透明度变化
        if (dy>moveDistance){
            binding.toolBarBackground.background.alpha = 255
            return
        }
        val percent = abs(dy).toFloat() / abs(moveDistance)
        //如果是设置背景透明度，则传入的参数是int类型，取值范围0-255
        //如果是设置控件透明度，传入的参数是float类型，取值范围0.0-1.0
        //alpha 值越小越透明
        val alpha = (percent * 255).toInt()
        binding.toolBarBackground.background.alpha= alpha//设置控件背景的透明度，传入int类型的参数（范围0~255）
    }
}