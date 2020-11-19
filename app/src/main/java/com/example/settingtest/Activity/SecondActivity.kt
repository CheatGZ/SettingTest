package com.example.settingtest.Activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.settingtest.Entity.AppCommendData
import com.example.settingtest.Entity.AppCommendListData
import com.example.settingtest.Entity.BannerData
import com.example.settingtest.R
import com.example.settingtest.Utils.CinemaHorizontalPageLayoutManager
import com.example.settingtest.Utils.CinemaPageScrollManager
import com.example.settingtest.Utils.ViewHintUtil.Companion.showSnackBar
import com.example.settingtest.View.BaseRecyclerViewAdapter
import com.example.settingtest.View.BaseRecyclerViewHolder
import com.example.settingtest.View.OnItemClickListener
import com.example.settingtest.databinding.ActivitySecondBinding
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Activity
 */
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var mAdapter: AppCommendListAdapter
    private lateinit var listBanner: MutableList<BannerData>
    private lateinit var list: MutableList<AppCommendListData>
    private lateinit var listAppCommendA: MutableList<AppCommendData>
    private lateinit var listAppCommendB: MutableList<AppCommendData>
    private lateinit var listAppCommendC: MutableList<AppCommendData>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initView() {
        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > 350) {
                binding.btnTop.visibility = View.VISIBLE
            } else {

                binding.btnTop.visibility = View.GONE
            }
        }
        binding.btnTop.setOnClickListener {
            binding.scrollView.smoothScrollTo(0, 0)
        }


        listBanner = ArrayList()
        listBanner.add(BannerData(R.mipmap.ic_launcher))
        listBanner.add(BannerData(R.mipmap.ic_question))
        listBanner.add(BannerData(R.mipmap.ic_default))
        binding.banner.isHorizontalScrollBarEnabled = true
        binding.banner.addBannerLifecycleObserver(this)
                .setAdapter(ImageBannerAdapter(listBanner)).indicator = CircleIndicator(this)

        list = ArrayList()
        listAppCommendA = ArrayList()
        listAppCommendB = ArrayList()
        listAppCommendC = ArrayList()
        for (i in 1..40) {
            listAppCommendA.add(AppCommendData("", R.mipmap.ic_launcher, "应用$i", "推荐理由$i"))
            listAppCommendB.add(AppCommendData("", R.mipmap.ic_launcher, "应用${2 * i}", "推荐理由${2 * i}"))
            listAppCommendC.add(AppCommendData("", R.mipmap.ic_launcher, "应用${3 * i}", "推荐理由${3 * i}"))
        }
        list.add(AppCommendListData("应用推荐A", 0, listAppCommendA))
        list.add(AppCommendListData("应用推荐B", 0, listAppCommendB))
        list.add(AppCommendListData("应用推荐C", 1, listAppCommendC))
        list.add(AppCommendListData("应用推荐C", 2, listAppCommendC))
        list.add(AppCommendListData("应用推荐C", 0, listAppCommendC))
        list.add(AppCommendListData("应用推荐C", 0, listAppCommendC))
        list.add(AppCommendListData("应用推荐C", 0, listAppCommendC))


        mAdapter = AppCommendListAdapter(R.layout.item_app_commend_list, list)
        binding.revAppCommendList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.revAppCommendList.isNestedScrollingEnabled = false
        binding.revAppCommendList.adapter = mAdapter
    }

    class ImageBannerAdapter(data: MutableList<BannerData>) : BannerAdapter<BannerData, BannerImageHolder>(data) {
        private lateinit var context: Context
        override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerImageHolder {
            context=parent.context
            val imageView= ImageView(parent.context)
            imageView.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            imageView.scaleType= ImageView.ScaleType.CENTER_CROP
            return BannerImageHolder(imageView)
        }

        override fun onBindView(holder: BannerImageHolder, data: BannerData, position: Int, size: Int) {
//        holder.imageView.setImageResource(data.imgSrc)
            val url="data:image/png;base64,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"
            Glide.with(context).load(url).centerCrop().into(holder.imageView)
        }
    }

    class AppCommendListAdapter(layoutRes: Int, data: MutableList<AppCommendListData>) : BaseRecyclerViewAdapter<AppCommendListData, BaseRecyclerViewHolder>(layoutRes, data) {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun convert(holder: BaseRecyclerViewHolder, item: AppCommendListData) {
            holder.setText(R.id.txt_title, item.commend)
//        val rev = holder.getView<RecyclerView>(R.id.rev_app_list)
//        val snapHelper= AppSnapHelper(context,rev)
//        snapHelper.attachToRecyclerView(rev)
//        rev.setHasFixedSize(true)
//        when(item.type){
//            0->rev.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            1->rev.layoutManager= GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
//            2->rev.layoutManager= GridLayoutManager(context,3,LinearLayoutManager.HORIZONTAL,false)
//        }
//        var mAdapter = AppCommendAdapter(R.layout.card_item_248x206, item.appList)
//        rev.run {
//            isNestedScrollingEnabled = false
//            adapter = mAdapter
//        }
        }

        override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
            val rev = holder.getView<RecyclerView>(R.id.rev_app_list)
            val helper = CinemaPageScrollManager()
            helper.setUpRecyclerView(rev)
            var mAdapter = AppCommendAdapter(R.layout.item_app_commend, data[position].appList)
            rev.run {
                isNestedScrollingEnabled = false
                adapter = mAdapter
            }

            mAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int) {
                    view.showSnackBar("${position}")
                    val intent= Intent(context,AppDetailActivity::class.java)
                    context.startActivity(intent)
                }
            })
            when(data[position].type){
                0->rev.layoutManager= CinemaHorizontalPageLayoutManager(1, 4)
                1->rev.layoutManager= CinemaHorizontalPageLayoutManager(2, 4)
                2->rev.layoutManager= CinemaHorizontalPageLayoutManager(1, 4)
            }
            mAdapter.data = data[position].appList
            mAdapter.notifyDataSetChanged()
            super.onBindViewHolder(holder, position)
        }
    }

    class AppCommendAdapter(layoutRes: Int, data: MutableList<AppCommendData>) : BaseRecyclerViewAdapter<AppCommendData, BaseRecyclerViewHolder>(layoutRes, data) {
        override fun convert(holder: BaseRecyclerViewHolder, item: AppCommendData) {
            holder.run {
                setText(R.id.txt_title, item.name)
                setText(R.id.txt_description, item.description)
            }
            val imageView=holder.getView<ImageView>(R.id.img_icon)
            val url="data:image/png;base64,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"
            Glide.with(context).load(url).centerCrop().placeholder(R.mipmap.ic_launcher).into(imageView)
        }
    }
}