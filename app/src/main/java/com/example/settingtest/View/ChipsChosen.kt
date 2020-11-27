package com.example.settingtest.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.settingtest.databinding.ViewAppChosenBinding

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/23
 * com.example.settingtest.View
 */
class ChipsChosen : ConstraintLayout {
    private val binding = ViewAppChosenBinding.inflate(LayoutInflater.from(context), this, true)
    private var mPosition = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    fun setChipChosen(text: String, position: Int = 0) {
        binding.chipChosen.text = text
        if (position == 0) {
            binding.btnDelete.visibility = View.GONE
        } else {
            binding.btnDelete.visibility = View.VISIBLE
        }
        mPosition = position
    }

    fun setBtnDeleteClickListener(listener: () -> Unit) {
        binding.btnDelete.setOnClickListener { listener() }
        binding.chipChosen.setOnClickListener { listener() }
    }

    fun getChipChosenPosition(): Int {
        return this.mPosition
    }
}