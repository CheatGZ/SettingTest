package com.example.settingtest.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.settingtest.R

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/11
 * com.qiyu.vraio.settings.itemDecoration
 */
class MyItemDecoration(val context: Context, val orientation: Int, val lastItemDecoration: Boolean = true) : RecyclerView.ItemDecoration() {
    private var mDividerHeight: Int = 1
    private var mPaint: Paint = Paint()
    private var margin: Int = 0

    init {
        mPaint.isAntiAlias = true
        mPaint.color = context.getColor(R.color.purple_700)
    }

    fun setMargin(margin: Int): MyItemDecoration {
        this.margin = margin
        return this
    }

    fun setColor(color: Int): MyItemDecoration {
        mPaint.color = context.getColor(color)
        return this
    }

    fun setDividerHeight(height: Int): MyItemDecoration {
        this.mDividerHeight = height
        return this
    }

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (orientation == RecyclerView.HORIZONTAL) {
            outRect.right = mDividerHeight//指相对itemView右侧的偏移量
        } else if (orientation == RecyclerView.VERTICAL) {
            outRect.bottom = mDividerHeight//指相对itemView下方的偏移量
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(view)
            if (!lastItemDecoration) {
                if (index == childCount - 1) {
                    continue
                }
            }

            if (orientation == RecyclerView.HORIZONTAL) {
                val dividerTop = view.top - mDividerHeight
                val dividerLeft = view.left + mDividerHeight
                val dividerBottom = view.bottom
                val dividerRight = view.right + mDividerHeight + margin;
                c.drawRect(
                        dividerLeft.toFloat(),
                        dividerTop.toFloat(),
                        dividerRight.toFloat(),
                        dividerBottom.toFloat(),
                        mPaint
                )
            } else if (orientation == RecyclerView.VERTICAL) {
                val dividerTop = view.top - mDividerHeight
                val dividerLeft = view.left + mDividerHeight
                val dividerBottom = view.bottom + mDividerHeight + margin;
                val dividerRight = view.right
                c.drawRect(
                        dividerLeft.toFloat(),
                        dividerTop.toFloat(),
                        dividerRight.toFloat(),
                        dividerBottom.toFloat(),
                        mPaint
                )
            }
        }
    }
}