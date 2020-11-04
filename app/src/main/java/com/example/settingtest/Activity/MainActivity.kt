package com.example.settingtest.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.settingtest.Adapter.ExpAdapter
import com.example.settingtest.Node.ExpFirstNode
import com.example.settingtest.Node.ExpSecondNode
import com.example.settingtest.Node.NodeType
import com.example.settingtest.R
import com.example.settingtest.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var mAdapter: ExpAdapter? = null
    private var lists: List<BaseNode>? = null
    private var count = 0
    private var expandFlag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        //        setSupportActionBar(binding.toolbar);
        initView()
    }

    private fun initView() {
        lists = entity
        mAdapter = ExpAdapter(lists)
        count = lists!![0].childNode!!.size
        expandFlag = (lists!![0] as ExpFirstNode).status
        if (expandFlag) {
            mAdapter!!.expand(0)
        } else {
            mAdapter!!.collapse(0)
        }
        mAdapter!!.setItemSwitchListener { view: View, viewType: Int, isChecked: Boolean, position: Int ->
            if (!view.isPressed) {
                return@setItemSwitchListener
            }
            if (position == 0) {
                expandFlag = if (isChecked) {
                    mAdapter!!.expand(position)
                    true
                } else {
                    mAdapter!!.collapse(position)
                    false
                }
                (lists!![position] as ExpFirstNode).status = expandFlag
            } else {
                if (expandFlag) {
                    if (viewType == NodeType.VIEW_TYPE_SECOND) {
                        (lists!![0].childNode!![position - 1] as ExpSecondNode).status = isChecked
                    } else {
                        (lists!![position - count] as ExpFirstNode).status = isChecked
                    }
                } else {
                    (lists!![position - count] as ExpFirstNode).status = isChecked
                }
            }
        }
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding!!.revSetting.layoutManager = linearLayoutManager
        binding!!.revSetting.adapter = mAdapter
        binding!!.txtTitle.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    //总的 list，里面放的是 FirstNode
    private val entity: List<BaseNode>
        private get() {
            //总的 list，里面放的是 FirstNode
            val list: MutableList<BaseNode> = ArrayList()
            val secondNodeLst: MutableList<BaseNode> = ArrayList()
            val secondData1 = ExpSecondNode()
            secondData1.setting = "停用虚拟围栏"
            secondData1.settingDescription = "功能说明"
            secondData1.status = false
            val secondData2 = ExpSecondNode()
            secondData2.setting = "安装第三方APK"
            secondData2.settingDescription = "功能说明"
            secondData2.status = false
            secondNodeLst.add(secondData1)
            secondNodeLst.add(secondData2)
            val firstData1 = ExpFirstNode(secondNodeLst)
            firstData1.setting = "开发者模式"
            firstData1.settingDescription = "功能说明"
            firstData1.status = true
            val firstData2 = ExpFirstNode()
            firstData2.setting = "未知来源应用安装"
            firstData2.settingDescription = "功能说明"
            firstData2.status = false
            val firstData3 = ExpFirstNode()
            firstData3.setting = "自动唤醒、熄灭屏幕"
            firstData3.settingDescription = "功能说明"
            firstData3.status = false
            list.add(firstData1)
            list.add(firstData2)
            list.add(firstData3)
            return list
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}