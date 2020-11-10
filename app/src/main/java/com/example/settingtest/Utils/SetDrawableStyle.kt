package com.example.settingtest.Utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.widget.RadioButton
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/10
 * com.example.settingtest.Utils
 */
class SetDrawableStyle {
    companion object {
        fun setRoundDrawableTop(context: Context, resId: Int, view: RadioButton, drawableWidth: Int, newPaddingStart: Int = 0, newPaddingTop: Int = 0) {
            if (drawableWidth < 0) throw Exception("viewWidth must >0 !")
            val drawable = view.compoundDrawables
            drawable[1] = SetDrawableStyle.getRoundImageDrawable(context, resId)
//            val rec = Rect(view.paddingStart + drawableWidth / 4 - view.width / 2 + newPaddingStart, newPaddingTop, drawableWidth + view.paddingStart + drawableWidth / 4 - view.width / 2 + newPaddingStart, drawableWidth + newPaddingTop
//            )
            val rec = Rect(-view.paddingStart + newPaddingStart, newPaddingTop, drawableWidth -view.paddingStart  + newPaddingStart, drawableWidth + newPaddingTop
            )
            drawable[1].bounds = rec
            view.setCompoundDrawables(null, drawable[1], null, null)
        }

        fun setRoundDrawableTop(bitmap: Bitmap, view: RadioButton, drawableWidth: Int, newPaddingStart: Int = 0, newPaddingTop: Int = 0) {
            if (drawableWidth < 0) throw Exception("viewWidth must >0 !")
            val drawable = view.compoundDrawables
            drawable[1] = SetDrawableStyle.getRoundImageDrawable(bitmap)
            val rec = Rect(view.paddingStart + drawableWidth / 4 - view.width / 2 + newPaddingStart, newPaddingTop, drawableWidth + view.paddingStart + drawableWidth / 4 - view.width / 2 + newPaddingStart, drawableWidth + newPaddingTop
            )
            drawable[1].bounds = rec
            view.setCompoundDrawables(null, drawable[1], null, null)
        }


        /** 生成圆角图片 */
        fun getRoundCornerImageDrawable(context: Context, resId: Int, cornerRadius: Float): RoundedBitmapDrawable {
            val src = BitmapFactory.decodeResource(context.resources, resId)
            val roundBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), src)
            roundBitmapDrawable.cornerRadius = cornerRadius
            roundBitmapDrawable.setAntiAlias(true)
            return roundBitmapDrawable
        }

        fun getRoundCornerImageDrawable(bitmap: Bitmap, cornerRadius: Float): RoundedBitmapDrawable {
            val roundBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), bitmap)
            roundBitmapDrawable.cornerRadius = cornerRadius
            roundBitmapDrawable.setAntiAlias(true)
            return roundBitmapDrawable
        }

        /** 生成圆型图片 */
        fun getRoundImageDrawable(context: Context, resId: Int): RoundedBitmapDrawable {
            val src = BitmapFactory.decodeResource(context.resources, resId)
            val dst: Bitmap = if (src.width >= src.height) {
                Bitmap.createBitmap(src, src.width /
                        2 - src.height /
                        2,
                        0, src.height, src.height)
            } else {
                Bitmap.createBitmap(src,
                        0, src.height /
                        2 - src.width /
                        2, src.width, src.width)
            }
            val roundBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), dst)
            roundBitmapDrawable.cornerRadius = (dst.width / 2).toFloat()
            roundBitmapDrawable.setAntiAlias(true)
            return roundBitmapDrawable
        }

        fun getRoundImageDrawable(bitmap: Bitmap): RoundedBitmapDrawable {
            val dst: Bitmap = if (bitmap.width >= bitmap.height) {
                Bitmap.createBitmap(bitmap, bitmap.width /
                        2 - bitmap.height /
                        2,
                        0, bitmap.height, bitmap.height)
            } else {
                Bitmap.createBitmap(bitmap,
                        0, bitmap.height /
                        2 - bitmap.width /
                        2, bitmap.width, bitmap.width)
            }
            val roundBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), dst)
            roundBitmapDrawable.cornerRadius = (dst.width / 2).toFloat()
            roundBitmapDrawable.setAntiAlias(true)
            return roundBitmapDrawable
        }
    }
}