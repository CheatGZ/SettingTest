package com.example.settingtest.View

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.settingtest.R
import com.example.settingtest.databinding.ViewLabelBinding

/**
 * @author zhangyongkang
 * @date 2020/11/8
 * com.example.settingtest.View
 */
class LabelView : ConstraintLayout {
    private var binding: ViewLabelBinding = ViewLabelBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LabelView)
        val imgIcon = typedArray.getDrawable(R.styleable.LabelView_imageSrc)
        val txtTitle = typedArray.getString(R.styleable.LabelView_titleText)
        val txtDescription = typedArray.getString(R.styleable.LabelView_descriptionText)
        val switchStatus = typedArray.getBoolean(R.styleable.LabelView_switchStatus, false)
        val labelType = typedArray.getInteger(R.styleable.LabelView_labelType, 0)
        val btnOne = typedArray.getString(R.styleable.LabelView_btnOne)
        val btnTwo = typedArray.getString(R.styleable.LabelView_btnTwo)


        binding.imgIcon.setImageDrawable(imgIcon)
        binding.txtTitle.text = txtTitle
        if (txtDescription == null || txtDescription.isEmpty()) {
            binding.txtDescription.visibility = View.GONE
        } else {
            binding.txtDescription.text = txtDescription
        }

        when (labelType) {
            LABEL_GOTO -> binding.layoutGoto.visibility = View.VISIBLE
            LABEL_SWITCH -> {
                binding.btnSwitch.visibility = View.VISIBLE
                binding.btnSwitch.isChecked = switchStatus
            }
            LABEL_BUTTON -> {
                if (btnOne != null && btnOne.isNotEmpty()) {
                    binding.btnOne.visibility = View.VISIBLE
                    binding.btnOne.text = btnOne
                    if (btnTwo != null && btnTwo.isNotEmpty()) {
                        binding.btnTwo.visibility = View.VISIBLE
                        binding.btnTwo.text = btnTwo
                    }
                }
            }
        }
        typedArray.recycle()
    }

    /**
     * 设置图标Icon
     */
    fun setImage(drawable: Drawable) {
        binding.imgIcon.setImageDrawable(drawable)
    }

    /** 设置标题Title */
    fun setTitleText(txtTitle: String) {
        binding.txtTitle.text = txtTitle
    }

    /** 设置副标题Description */
    fun setDescription(txtDescription: String) {
        binding.txtDescription.text = txtDescription
    }

    /** 设置notify icon */
    fun setNotifyIcon(drawable: Drawable) {
        binding.imgNotify.setImageDrawable(drawable)
    }

    /** 设置notiy text */
    fun setNotifyText(notifyText: String) {
        binding.txtNotify.text = notifyText
    }

    /** 设置开关Switch状态 */
    fun setSwitchStatus(switchStatus: Boolean) {
        binding.btnSwitch.isChecked = switchStatus
    }

    /** 获取开关Switch状态 */
    fun getSwitchStatus(): Boolean {
        return binding.btnSwitch.isChecked
    }

    /** 设置Goto点击监听 */
    fun setGotoClickListener(onClickListener: OnClickListener) {
        binding.layoutGoto.setOnClickListener(onClickListener)
    }

    /** 设置switch状态监听 */
    fun setSwitchStatusChangedListener(onCheckedChangeListener: () -> CompoundButton.OnCheckedChangeListener) {
        binding.btnSwitch.setOnCheckedChangeListener(onCheckedChangeListener.invoke())
    }

    /** 设置btnOne状态监听 */
    fun setBtnOneClickListener(onClickListener: OnClickListener) {
        binding.btnOne.setOnClickListener(onClickListener)
    }

    /** 设置BtnTwo状态监听 */
    fun setBtnTwoClickListener(onClickListener: OnClickListener) {
        binding.btnTwo.setOnClickListener(onClickListener)
    }


    companion object {
        /** NUll Label
         * 默认状态
         * 右侧显示为空，仅作为Label显示
         */
        const val LABEL_NULL = 0

        /** switch开关Label
         * 右侧显示为switch开关，
         * 不可跳转页面
         */
        const val LABEL_SWITCH = 1

        /** goto跳转Label
         * 右侧显示为跳转按钮
         * 可以点击整个label跳转
         * 可以仅点击跳转按钮部分跳转
         * 可以显示新消息通知Notify
         */
        const val LABEL_GOTO = 2

        /** btn点击Label
         * 右侧显示两个btn按钮
         * 从右向左分别为btnOne、btnTwo
         * 显示按钮请给按钮复制app:btnOne 或app:btnTwo
         * 如果仅有一个btn，请赋值app:btnOne
         */
        const val LABEL_BUTTON = 3
    }
}