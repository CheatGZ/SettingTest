package com.example.settingtest.Entity

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/19
 * com.example.settingtest.Entity
 */
data class AppImageData(val type: Int, val res: Int) {
    companion object {
        const val VIDEO = 0
        const val IMAGE = 1
    }
}