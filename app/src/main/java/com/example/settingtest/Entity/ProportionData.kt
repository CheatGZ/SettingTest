package com.example.settingtest.Entity

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/6
 * SettingTest
 */
data class ProportionData(val id: String, val name: String, val proportion: Float, val color: Int) {
    /**
     * proportion 颜色
     */
    companion object {
        val COLOR_GREEN = 0
        val COLOR_BLUE = 1
        val COLOR_ORANGE = 2
        val COLOR_WHITE = 3
        val COLOR_RED = 4
        val COLOR_gray = 5
    }
}