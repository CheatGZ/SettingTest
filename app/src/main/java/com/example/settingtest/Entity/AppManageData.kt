package com.example.settingtest.Entity

import android.graphics.drawable.Drawable

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/4
 * SettingTest
 */
data class AppManageData(var id: String,
                         var drawable:Drawable,
                         var title: String,
                         var description: String,
                         var status: Boolean)