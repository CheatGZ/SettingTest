package com.example.settingtest.Utils

import android.content.Context
import android.util.TypedValue




/**
 * @author zhangyongkang01_sx
 * @date 2020/11/10
 * com.example.settingtest.Utils
 */
class UnitConversion {
    companion object{
        fun px2dp(context: Context, pxValue: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()).toInt()
        }

        fun px2sp(context: Context, pxValue: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, context.getResources().getDisplayMetrics()).toInt()
        }

        fun dp2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        fun sp2px(context: Context, spValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }
    }
}