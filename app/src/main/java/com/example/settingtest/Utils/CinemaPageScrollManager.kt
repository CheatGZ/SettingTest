package com.example.settingtest.Utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 协助处理recyclerview 翻页滚动
 * 仅实现了横向翻页效果
 */
class CinemaPageScrollManager(private val cardBPreShowWidth:Int? = 0) {
    companion object{
        const val TAG = "CinemaPageScrollManager"
        const val ORIENTATION_HORIZONTAL = 1
        const val ORIENTATION_VERTICAL = 2
        const val ORIENTATION_NULL = 0
    }

    private lateinit var mRecyclerView: RecyclerView
    //横向纵向偏移距离
    private var offsetX = 0
    private var offsetY = 0

    //滑动起始位置
    private var startX = 0
    private var startY = 0

    private var mOrientation = ORIENTATION_HORIZONTAL
    private var mAnimator: ValueAnimator? = null

    /**
     * 为recyclerview 绑定翻页滑动
     */
    fun setUpRecyclerView(recyclerView: RecyclerView){
        mRecyclerView = recyclerView
        registListener()
    }

    /**
     * 滚动到指定位置
     * @param position
     */
    fun scrollToPosition(position:Int){
        if(mAnimator == null){
            flingListener.onFling(0,0)
        }else{
            var startPoint = if (mOrientation == ORIENTATION_VERTICAL) offsetY else offsetX
            var endPoint = 0
            if(mOrientation == ORIENTATION_VERTICAL){
                endPoint = mRecyclerView.height * position
            }else{
                endPoint = mRecyclerView.width * position
            }

            if(startPoint != endPoint){
                mAnimator!!.setIntValues(startPoint,endPoint)
                mAnimator!!.start()
            }
        }
    }

    private var firstTouch = true
    private var flingLeft = false
    private var flingtop = false
    private lateinit var flingListener:RecyclerView.OnFlingListener
    private lateinit var touchListener:View.OnTouchListener
    private lateinit var scrollListener:RecyclerView.OnScrollListener
    private fun registListener(){

        flingListener = object : RecyclerView.OnFlingListener(){
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {

                if (mOrientation == ORIENTATION_NULL) {
                    return false
                }
                //获取开始滚动时所在页面的index
                //获取开始滚动时所在页面的index
                var p: Int = getStartPageIndex()

                //记录滚动开始和结束的位置

                //记录滚动开始和结束的位置
                var endPoint = 0
                var startPoint = 0

                //如果是垂直方向

                //如果是垂直方向
                if (mOrientation == ORIENTATION_VERTICAL) {
                    startPoint = offsetY
                    if (velocityY < 0) {
                        p--
                    } else if (velocityY >= 0) {
                        p++
                    }
                    //更具不同的速度判断需要滚动的方向
                    //注意，此处有一个技巧，就是当速度为0的时候就滚动会开始的页面，即实现页面复位
                    endPoint = p * mRecyclerView.height
                } else {
                    startPoint = offsetX
                    if (velocityX < 0) {
                        p--
                    } else if (velocityX > 0) {
                        p++
                    } else if (velocityX == 0) {
                        if (flingLeft) {
                            p--
                        } else {
                            p++
                        }
                    }
                    Log.d(TAG, " next page:$p")
                    endPoint = p * (mRecyclerView.width - cardBPreShowWidth!!)
                }
                if (endPoint < 0) {
                    endPoint = 0
                }

                //使用动画处理滚动
                //使用动画处理滚动
                if (mAnimator == null) {
                    mAnimator = ValueAnimator.ofInt(startPoint, endPoint)
                    mAnimator!!.setDuration(300)
                    mAnimator!!.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
                        val nowPoint = animation.animatedValue as Int
                        if (mOrientation == ORIENTATION_VERTICAL) {
                            val dy = nowPoint - offsetY
                            //这里通过RecyclerView的scrollBy方法实现滚动。
                            mRecyclerView.scrollBy(0, dy)
                        } else {
                            val dx = nowPoint - offsetX
                            mRecyclerView.scrollBy(dx, 0)
                        }
                    })
                    mAnimator!!.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            //回调监听

                            //修复双击item bug
                            mRecyclerView.stopScroll()
                            startY = offsetY
                            startX = offsetX

                        }
                    })
                } else {
                    mAnimator!!.cancel()
                    mAnimator!!.setIntValues(startPoint, endPoint)
                }

                mAnimator!!.start()

                return true
            }

        }
        touchListener = object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                //手指按下的时候记录开始滚动的坐标
                if (firstTouch) {
                    //第一次touch可能是ACTION_MOVE或ACTION_DOWN,所以使用这种方式判断
                    firstTouch = false
                    startY = offsetY
                    startX = offsetX
                }
                if (event!!.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
                    firstTouch = true
                }
                return false
            }
        }
        scrollListener = object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if(newState == 1){
                    return
                }

                if(newState == 0 && mOrientation != ORIENTATION_NULL){
                    val move: Boolean
                    var vX = 0
                    var vY = 0
                    if (mOrientation == ORIENTATION_VERTICAL) {
                        val absY = Math.abs(offsetY - startY)
                        //如果滑动的距离超过屏幕的一半表示需要滑动到下一页
                        move = absY > recyclerView.height / 2
                        vY = 0
                        if (move) {
                            vY = if (offsetY - startY < 0) -1000 else 1000
                        }
                    } else {
                        val absX = Math.abs(offsetX - startX)
                        move = absX > recyclerView.width / 2
                        if (move) {
                            //vX = offsetX - startX < 0 ? -1000 : 1000;
                            vX =
                                if (offsetX - startX < 0) -(recyclerView.width - cardBPreShowWidth!!) else (recyclerView.width - cardBPreShowWidth!!)
                        }

                    }
                    flingListener.onFling(vX, vY)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //滚动结束记录滚动的偏移量
                offsetY += dy
                offsetX += dx
                flingLeft = dx < 0
                flingtop = dy < 0
            }
        }

        mRecyclerView.setOnFlingListener(flingListener)

        mRecyclerView.setOnScrollListener(scrollListener)

        mRecyclerView.setOnTouchListener(touchListener)
    }

    /**
     * 获取touch时 所在页面下标
     */
    private fun getStartPageIndex():Int{

        var p = 0
        if (mRecyclerView.height == 0 || mRecyclerView.width == 0) {
            //没有宽高无法处理
            return p
        }
        p = if (mOrientation == ORIENTATION_VERTICAL) {
            startY / mRecyclerView.height
        } else {
            startX / (mRecyclerView.width - cardBPreShowWidth!!)
        }
        return p
    }

}