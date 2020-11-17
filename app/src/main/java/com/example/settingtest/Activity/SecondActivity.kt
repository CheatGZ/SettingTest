package com.example.settingtest.Activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settingtest.Adapter.AppCommendListAdapter
import com.example.settingtest.Adapter.ImageBannerAdapter
import com.example.settingtest.Entity.AppCommendData
import com.example.settingtest.Entity.AppCommendListData
import com.example.settingtest.Entity.BannerData
import com.example.settingtest.R
import com.example.settingtest.databinding.ActivitySecondBinding
import com.youth.banner.indicator.CircleIndicator

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Activity
 */
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var mAdapter: AppCommendListAdapter
    private lateinit var listBanner:MutableList<BannerData>
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
            if(scrollY>350){
                binding.btnTop.visibility= View.VISIBLE
            }else{

                binding.btnTop.visibility= View.GONE
            }
        }
        binding.btnTop.setOnClickListener {
            binding.scrollView.smoothScrollTo(0,0)
        }


        listBanner=ArrayList()
        listBanner.add(BannerData(R.mipmap.ic_launcher))
        listBanner.add(BannerData(R.mipmap.ic_question))
        listBanner.add(BannerData(R.mipmap.ic_default))
        binding.banner.isHorizontalScrollBarEnabled=true
        binding.banner.addBannerLifecycleObserver(this)
                .setAdapter(ImageBannerAdapter(listBanner)).indicator = CircleIndicator(this)

        list = ArrayList()
        listAppCommendA = ArrayList()
        listAppCommendB = ArrayList()
        listAppCommendC = ArrayList()
        for (i in 1..20) {
            listAppCommendA.add(AppCommendData("", R.mipmap.ic_launcher, "应用$i", "推荐理由$i"))
            listAppCommendB.add(AppCommendData("", R.mipmap.ic_launcher, "应用${2 * i}", "推荐理由${2*i}"))
            listAppCommendC.add(AppCommendData("", R.mipmap.ic_launcher, "应用${3 * i}", "推荐理由${3 * i}"))
        }
        list.add(AppCommendListData("应用推荐A",listAppCommendA))
        list.add(AppCommendListData("应用推荐B",listAppCommendB))
        list.add(AppCommendListData("应用推荐C",listAppCommendC))
        list.add(AppCommendListData("应用推荐C",listAppCommendC))
        list.add(AppCommendListData("应用推荐C",listAppCommendC))
        list.add(AppCommendListData("应用推荐C",listAppCommendC))
        list.add(AppCommendListData("应用推荐C",listAppCommendC))


        mAdapter = AppCommendListAdapter(R.layout.item_app_commend_list, list)
        binding.revAppCommendList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.revAppCommendList.isNestedScrollingEnabled = false
        binding.revAppCommendList.adapter = mAdapter
    }
}