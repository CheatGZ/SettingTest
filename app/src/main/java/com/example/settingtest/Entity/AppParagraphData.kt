package com.example.settingtest.Entity

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/19
 * com.example.settingtest.Entity
 */
data class AppParagraphData(
        val userId: String,
        val time: String,
        var isCommended: Boolean,
        val Content: String,
        val interestCount: Int,
        val usefulCount: Int,
        val uselessCount: Int
)