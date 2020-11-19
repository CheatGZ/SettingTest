package com.example.settingtest.Entity

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Entity
 */
data class AppCommendListData(val commend: String,val type:Int, val appList: MutableList<AppCommendData>)

data class AppCommendData(val appId: String, val imgSrc: Int, val name: String, val description: String)