package com.example.settingtest.View

import android.content.Context
import android.util.AttributeSet
import com.example.settingtest.R

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/9
 * com.example.settingtest.View
 */
class QiYuSpinner : androidx.appcompat.widget.AppCompatSpinner {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        this.dropDownVerticalOffset = top + paddingBottom+5
        this.setPopupBackgroundResource(R.drawable.shape_spinner_pop)
    }
}