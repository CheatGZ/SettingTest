package com.example.settingtest.View

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.settingtest.R
import com.example.settingtest.databinding.ViewLabelNewBinding

/**
 * @author zhangyongkang
 * @date 2020/11/8
 * com.example.settingtest.View
 */
class LabelView(context: Context, labelType: Int) : ConstraintLayout(context) {
    private var binding: ViewLabelNewBinding =ViewLabelNewBinding.inflate(LayoutInflater.from(context), this,true)

    init {
        if (labelType == 0) {
            binding.btnSwitch.visibility = View.VISIBLE
        } else{
            binding.layoutGoto.visibility = View.VISIBLE
        }
    }

    constructor(context: Context, attributeSet: AttributeSet) : this(context,labelType = -1) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LabelView)
        val imgIcon = typedArray.getDrawable(R.styleable.LabelView_imageSrc)
        val txtTitle = typedArray.getString(R.styleable.LabelView_titleText)
        val txtDescription = typedArray.getString(R.styleable.LabelView_descriptionText)
        val switchStatus = typedArray.getBoolean(R.styleable.LabelView_switchStatus, false)
        val labelType = typedArray.getInteger(R.styleable.LabelView_labelType, 0)

        binding.imgIcon.setImageDrawable(imgIcon)
        binding.txtTitle.text = "txtTitle"
        if (txtDescription == null || txtDescription.isEmpty()) {
            binding.txtDescription.visibility = View.GONE
        } else {
            binding.txtDescription.text = txtDescription
        }
        binding.btnSwitch.isChecked = switchStatus
        if (labelType == 0) {
            binding.layoutGoto.visibility = View.VISIBLE
        } else {
            binding.layoutGoto.visibility = View.VISIBLE
        }
        typedArray.recycle()
    }

    /**
     * 设置图标Icon
     */
    private fun setImage(drawable: Drawable) {
        binding.imgIcon.setImageDrawable(drawable)
    }

    /** 设置标题Title */
    private fun setTitleText(txtTitle: String) {
        binding.txtTitle.text = txtTitle
    }

    /** 设置副标题Description */
    private fun setDescription(txtDescription: String) {
        binding.txtDescription.text = txtDescription
    }

    /** 设置notify icon */
    private fun setNotifyIcon(drawable: Drawable) {
        binding.imgNotify.setImageDrawable(drawable)
    }

    /** 设置notiy text */
    private fun setNotifyText(notifyText: String) {
        binding.txtNotify.text = notifyText
    }

    /** 设置开关Switch状态 */
    private fun setSwitchStatus(switchStatus: Boolean) {
        binding.btnSwitch.isChecked = switchStatus
    }

    /** 获取开关Switch状态 */
    private fun getSwitchStatus(switchStatus: Boolean): Boolean {
        return binding.btnSwitch.isChecked
    }

    /** 设置Goto点击监听 */
    private fun setGotoClickListener(onClickListener: OnClickListener) {
        binding.layoutGoto.setOnClickListener(onClickListener)
    }

    /** 设置switch状态监听 */
    private fun setSwitchStatusChangedListener(onCheckedChangeListener: CompoundButton.OnCheckedChangeListener) {
        binding.btnSwitch.setOnCheckedChangeListener(onCheckedChangeListener)
    }

    companion object {
        /** switch开关Label */
        const val LABEL_SWITCH = 0

        /** goto跳转Label */
        const val LABEL_GOTO = 1
    }
}