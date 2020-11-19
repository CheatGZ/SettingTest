package com.example.settingtest.Utils

import android.graphics.Rect
import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
/**
 * @author xupeng
 * 自定义LayoutManger用于card布局与翻页
 *
 */
class CinemaHorizontalPageLayoutManager : RecyclerView.LayoutManager ,IPageDecorationLastJudge{
    companion object{
        const val TAG = CinemaConstant.TAG+"CinemaPageLayoutManager"
    }

    private var rows: Int = 0
    private var columns: Int = 0
    private var onePageSize = 0 //每一页显示内容
    private var cardBPreShow = false //是否预显示第二页的部分内容
    //card B 第二页预加载宽度
    private var cardBItemPreShowWidth = 280

    /**
     * @param rows 需要显示的行数
     * @param columns 需要显示的列数
     * @param isCardB 是否是cardB
     * @preShowWidth cardB 第二页显示的宽度
     */
    constructor(rows:Int, columns:Int, isCardB:Boolean? = false, preShowWidth:Int?=0){
        this.rows = rows
        this.columns = columns
        this.onePageSize = rows * columns
        this.cardBPreShow = isCardB!!
        this.cardBItemPreShowWidth = preShowWidth!!
    }

    private var offsetX = 0
    private var offsetY = 0
    private var totalWidth = 0
    private var totalHeight = 0
    private val allItemFrames = SparseArray<Rect>()
    private var itemWidthUsed:Int = 0
    private var itemHeightUsed:Int = 0
    private var itemWidth = 0
    private var itemHeight = 0
    private var pageSize = 0

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams? {
        return null
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        detachAndScrapAttachedViews(recycler!!)
        var newOffsetX = offsetX + dx
        var result = dx
        if(newOffsetX > totalWidth){
            result = totalWidth - offsetX
        }else if(newOffsetX < 0){
            result = 0 - offsetX
        }
        offsetX += result
        offsetChildrenHorizontal(-result)
        recycleAndFillItems(recycler,state!!)
        return result
    }

    /**
     * 重新绘制children
     */
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        var itemCount = itemCount
        var isPreLayout = state!!.isPreLayout
        if(itemCount == 0){
            removeAndRecycleAllViews(recycler!!)
        }
        if(isPreLayout){
            return
        }

        //计算每个item的平均宽高
        itemWidth = getUsableWidth() / columns
        itemHeight = getUsableHeight() / rows
        //计算已经消耗的宽高
        itemWidthUsed = (columns - 1) * itemWidth
        itemHeightUsed = (rows - 1) * itemHeight
        computePageSize(state)
        //可滚动的最大值,只有遇到cardB的时候会控制recyclerview的最大长度
        totalWidth = (pageSize - 1)* (width - cardBItemPreShowWidth)

        //分离view
        detachAndScrapAttachedViews(recycler!!)
        var count = getItemCount()
            var p = 0
            while (p < pageSize) {
                var r = 0
                while (r < rows) {
                    var c = 0
                    while (c < columns) {
                        val index = p * onePageSize + r * columns + c
                        if (index == count) {
                            //跳出多重循环
                            c = columns
                            r = rows
                            p = pageSize
                            break
                        }
                        val view = recycler.getViewForPosition(index)
                        addView(view)
                        //测量item
                        measureChildWithMargins(view, itemWidthUsed, itemHeightUsed)
                        val width = getDecoratedMeasuredWidth(view)
                        val height = getDecoratedMeasuredHeight(view)
                        //记录显示范围
                        var rect = allItemFrames[index]
                        if (rect == null) {
                            rect = Rect()
                        }
                        val x = p * (getUsableWidth() - cardBItemPreShowWidth) + c * itemWidth
                        val y = r * itemHeight
                        rect!![x, y, width + x] = height + y
                        allItemFrames.put(index, rect)
                        c++
                    }
                    r++
                }
                //每一页循环以后就回收一页的View用于下一页的使用
                removeAndRecycleAllViews(recycler)
                p++
            }

        recycleAndFillItems(recycler, state)
    }

    override fun onDetachedFromWindow(view: RecyclerView?, recycler: RecyclerView.Recycler?) {
        super.onDetachedFromWindow(view, recycler)
        offsetX = 0
        offsetY = 0
    }

    override fun computeHorizontalScrollRange(state: RecyclerView.State): Int {
        computePageSize(state)
        return pageSize * width
    }

    override fun computeHorizontalScrollOffset(state: RecyclerView.State): Int {
        return offsetX
    }

    override fun computeHorizontalScrollExtent(state: RecyclerView.State): Int {
        return width
    }

    /**
     * 重写测量函数，加入rows参数，针对主界面，当显示两行时，用于控制整个recycler的高度
     */
    override fun onMeasure(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        val view: View = recycler.getViewForPosition(0)
        measureChild(view, widthSpec, heightSpec)
        var measuredWidth = View.MeasureSpec.getSize(widthSpec)
        /*if(specType){
            measuredWidth = 800 //暂未使用
        }*/
        val measuredHeight = view.measuredHeight * rows //用于处理recylerview使用wrap_content设置高度的时候，两行显示
        setMeasuredDimension(measuredWidth, measuredHeight)
        //super.onMeasure(recycler, state, widthSpec, 206*rows)
    }

    /**
     * 计算页数
     */
    private fun computePageSize(state: RecyclerView.State) {
        pageSize =
            state.itemCount / onePageSize + if (state.itemCount % onePageSize == 0) 0 else 1
    }

    /**
     * 获取可用宽度
     */
    private fun getUsableWidth(): Int {
        val widht = width - paddingLeft - paddingRight
        return widht
    }

    /**
     * 获取可用高度
     * todo
     *      此处需要获取空间所占用的高度
     */
    private fun getUsableHeight(): Int {
        var height = getHeight() - getPaddingTop() - getPaddingBottom()
        return height
    }

    private fun recycleAndFillItems(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ) {
        if (state.isPreLayout) {
            return
        }
        val displayRect = Rect(
            paddingLeft + offsetX,
            paddingTop,
            width - paddingLeft - paddingRight + offsetX,
            height - paddingTop - paddingBottom
        )
        val childRect = Rect()
        for (i in 0..childCount-1) {
            val child = getChildAt(i)
            childRect.left = getDecoratedLeft(child!!)
            childRect.top = getDecoratedTop(child)
            childRect.right = getDecoratedRight(child)
            childRect.bottom = getDecoratedBottom(child)
            if (!Rect.intersects(displayRect, childRect)) {
                removeAndRecycleView(child, recycler)
            }
        }
        for (i in 0..itemCount-1) {
            //LogUtils.d(TAG," index:${i} ")
            if (Rect.intersects(displayRect, allItemFrames.get(i))) {
                val view = recycler.getViewForPosition(i)
                addView(view)
                measureChildWithMargins(view, itemWidthUsed, itemHeightUsed)
                val rect: Rect = allItemFrames.get(i)
                layoutDecorated(
                    view,
                    rect.left - offsetX,
                    rect.top,
                    rect.right - offsetX,
                    rect.bottom
                )
            }
        }
    }

    override fun isLastRow(position: Int): Boolean {
        if (position >= 0 && position < itemCount) {
            var indexOfPage: Int = position % onePageSize
            indexOfPage++
            if (indexOfPage > (rows - 1) * columns && indexOfPage <= onePageSize) {
                return true
            }
        }
        return false
    }

    override fun isLastColumn(position: Int): Boolean {
        var tmpPosition = position
        if (tmpPosition >= 0 && tmpPosition < itemCount) {
            tmpPosition++
            if (tmpPosition % columns == 0) {
                return true
            }
        }
        return false
    }

    override fun isPageLast(position: Int): Boolean {
        var tmpPosition = position
        tmpPosition++
        return tmpPosition % onePageSize == 0
    }

}