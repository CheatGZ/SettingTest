package com.example.settingtest.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.settingtest.R

/**
 * @author zhangyongkang
 * @date 2020/11/8
 * com.example.settingtest.View
 */
class LabelView(context: Context, labelType: Int) : ConstraintLayout(context) {
//    private var binding: ViewLabelBinding =ViewLabelBinding.inflate(LayoutInflater.from(context), this,true)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_label_new, this, true)
//        if (labelType == LABEL_SWITCH) {
//            binding.layoutGoto.visibility = View.GONE
//        } else if (labelType == LABEL_GOTO) {
//            binding.layoutGoto.visibility = View.GONE
//        }
    }

    constructor(context: Context, attributeSet: AttributeSet) : this(context, labelType = 0) {
//        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LabelSwitch)
//        val imgIcon = typedArray.getDrawable(R.styleable.LabelSwitch_imageSrc)
//        val txtTitle = typedArray.getString(R.styleable.LabelSwitch_titleText)
//        val txtDescription = typedArray.getString(R.styleable.LabelSwitch_descriptionText)
//        val switchStatus = typedArray.getBoolean(R.styleable.LabelSwitch_switchStatus, false)
//        val labelType = typedArray.getInteger(R.styleable.LabelSwitch_labelType, 0)
//
//        binding.imgIcon.setImageDrawable(imgIcon)
//        binding.txtTitle.text = txtTitle
//        if (txtTitle == null || txtTitle.isEmpty()) {
//            binding.txtDescription.visibility = View.GONE
//        } else {
//            binding.txtDescription.text = txtDescription
//        }
//
//        if (labelType == 0) {
//            binding.layoutGoto.visibility = View.VISIBLE
//            binding.btnSwitch.isChecked = switchStatus
//        } else if (labelType == 1) {
//            binding.layoutGoto.visibility = View.VISIBLE
//        }
//        typedArray.recycle()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int):this(context, labelType = 0){

    }
//
//
//    /**
//     * 设置图标Icon
//     */
//    private fun setImage(drawable: Drawable) {
//        binding.imgIcon.setImageDrawable(drawable)
//    }
//
//    /** 设置标题Title */
//    private fun setTitleText(txtTitle: String) {
//        binding.txtTitle.text = txtTitle
//    }
//
//    /** 设置副标题Description */
//    private fun setDescription(txtDescription: String) {
//        binding.txtDescription.text = txtDescription
//    }
//
//    /** 设置notify icon */
//    private fun setNotifyIcon(drawable: Drawable) {
//        binding.imgNotify.setImageDrawable(drawable)
//    }
//
//    /** 设置notiy text */
//    private fun setNotifyText(notifyText: String) {
//        binding.txtNotify.text = notifyText
//    }
//
//    /** 设置开关Switch状态 */
//    private fun setSwitchStatus(switchStatus: Boolean) {
//        binding.btnSwitch.isChecked = switchStatus
//    }
//
//    /** 获取开关Switch状态 */
//    private fun getSwitchStatus(switchStatus: Boolean): Boolean {
//        return binding.btnSwitch.isChecked
//    }
//
//    /** 设置Goto点击监听 */
//    private fun setGotoClickListener(onClickListener: OnClickListener) {
//        binding.layoutGoto.setOnClickListener(onClickListener)
//    }
//
//    /** 设置switch状态监听 */
//    private fun setSwitchStatusChangedListener(onCheckedChangeListener: CompoundButton.OnCheckedChangeListener) {
//        binding.btnSwitch.setOnCheckedChangeListener(onCheckedChangeListener)
//    }
//
//    companion object {
//        /** switch开关Label */
//        const val LABEL_SWITCH = 0
//
//        /** goto跳转Label */
//        const val LABEL_GOTO = 1
//    }
}