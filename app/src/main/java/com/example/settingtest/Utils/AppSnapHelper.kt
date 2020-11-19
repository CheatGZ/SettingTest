package com.example.settingtest.Utils

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.*

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/18
 * com.example.settingtest.Utils
 */
class AppSnapHelper(context: Context?, recyclerView: RecyclerView) : SnapHelper() {

    companion object {
        const val PAGE_ITEM_NUM = 4
    }

    private val INVALID_DISTANCE = 1f
    private val MILLISECONDS_PER_INCH = 40f
    private var mHorizontalHelper: OrientationHelper? = null
    private var mRecyclerView: RecyclerView = recyclerView

    override fun calculateDistanceToFinalSnap(@NonNull layoutManager: RecyclerView.LayoutManager, @NonNull targetView: View): IntArray {
        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager)!!)
        } else {
            out[0] = 0
        }
        return out
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding()
    }

    override fun createSnapScroller(layoutManager: RecyclerView.LayoutManager?): LinearSmoothScroller? {
        return if (layoutManager !is RecyclerView.SmoothScroller.ScrollVectorProvider) {
            null
        } else object : LinearSmoothScroller(mRecyclerView?.context) {
            override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
                val snapDistances: IntArray = calculateDistanceToFinalSnap(mRecyclerView.getLayoutManager()!!, targetView)
                val dx = snapDistances[0]
                val dy = snapDistances[1]
                val time: Int = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)))
                if (time > 0) {
                    action.update(dx, dy, time, mDecelerateInterpolator)
                }
            }

            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                return MILLISECONDS_PER_INCH / displayMetrics!!.densityDpi
            }
        }
    }

    override fun calculateScrollDistance(velocityX: Int, velocityY: Int): IntArray {
        return super.calculateScrollDistance(velocityX, velocityY)
    }

    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        if (layoutManager !is RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return RecyclerView.NO_POSITION
        }
        val itemCount: Int = layoutManager.itemCount
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION
        }
        val currentView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val currentPosition: Int = layoutManager.getPosition(currentView)
        Log.d("kangkang", "currentPosition $currentPosition  velocityX $velocityX")
        if (currentPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION
        }
        val vectorProvider: RecyclerView.SmoothScroller.ScrollVectorProvider = layoutManager
        // deltaJumps sign comes from the velocity which may not match the order of children in
        // the LayoutManager. To overcome this, we ask for a vector from the LayoutManager to
        // get the direction.
        val vectorForEnd: PointF = vectorProvider.computeScrollVectorForPosition(itemCount - 1)
                ?: // cannot get a vector for the given position.
                return RecyclerView.NO_POSITION

        //在松手之后,列表最多只能滚多一屏的item数
        val deltaThreshold: Int = layoutManager.width / getHorizontalHelper(layoutManager)!!.getDecoratedMeasurement(currentView) + 1
        var hDeltaJump: Int
        if (layoutManager.canScrollHorizontally()) {
            hDeltaJump = estimateNextPositionDiffForFling(layoutManager,
                    getHorizontalHelper(layoutManager), velocityX, 0)
            if (hDeltaJump > deltaThreshold) {
                hDeltaJump = deltaThreshold
            }
            if (hDeltaJump < -deltaThreshold) {
                hDeltaJump = -deltaThreshold
            }
            if (vectorForEnd.x < 0) {
                hDeltaJump = -hDeltaJump
            }
        } else {
            return RecyclerView.NO_POSITION
        }
        var targetPos = currentPosition + hDeltaJump
        if (targetPos < 0) {
            targetPos = 0
        }
        if (targetPos >= itemCount) {
            targetPos = itemCount - 1
        }
        return targetPos
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return findStartView(layoutManager, getHorizontalHelper(layoutManager))
    }

    private fun findStartView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper?): View? {
        return if (layoutManager is LinearLayoutManager) {
            val firstChildPosition: Int = (layoutManager).findFirstVisibleItemPosition()

            if (firstChildPosition == RecyclerView.NO_POSITION) {
                return null
            }
            if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1) {
                return null
            }
            val firstChildView: View = layoutManager.findViewByPosition(firstChildPosition)!!
            if (helper!!.getDecoratedEnd(firstChildView) >= helper.getDecoratedMeasurement(firstChildView) / 2 && helper.getDecoratedEnd(firstChildView) > 0) {
                firstChildView
            } else {
                layoutManager.findViewByPosition(firstChildPosition)
            }
        } else if (layoutManager is LinearLayoutManager) {
            val firstChildPosition: Int = (layoutManager).findFirstVisibleItemPosition()

            if (firstChildPosition == RecyclerView.NO_POSITION) {
                return null
            }
            if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1) {
                return null
            }
            val firstChildView: View = layoutManager.findViewByPosition(firstChildPosition)!!
            if (helper!!.getDecoratedEnd(firstChildView) >= helper.getDecoratedMeasurement(firstChildView) / 2 && helper.getDecoratedEnd(firstChildView) > 0) {
                firstChildView
            } else {
                layoutManager.findViewByPosition(firstChildPosition)
            }
        } else {
            null
        }
    }


    private fun estimateNextPositionDiffForFling(layoutManager: RecyclerView.LayoutManager,
                                                 helper: OrientationHelper?, velocityX: Int, velocityY: Int): Int {
        val distances = calculateScrollDistance(velocityX, velocityY)
        val distancePerChild = computeDistancePerChild(layoutManager, helper)
        if (distancePerChild <= 0) {
            return 0
        }
        val distance = distances[0]
        return if (distance > 0) {
            Math.floor(distance / distancePerChild.toDouble()).toInt()
        } else {
            Math.ceil(distance / distancePerChild.toDouble()).toInt()
        }
    }

    private fun computeDistancePerChild(layoutManager: RecyclerView.LayoutManager,
                                        helper: OrientationHelper?): Float {
        var minPosView: View? = null
        var maxPosView: View? = null
        var minPos = Int.MAX_VALUE
        var maxPos = Int.MIN_VALUE
        val childCount: Int = layoutManager.getChildCount()
        if (childCount == 0) {
            return INVALID_DISTANCE
        }
        for (i in 0 until childCount) {
            val child: View = layoutManager.getChildAt(i)!!
            val pos: Int = layoutManager.getPosition(child)
            if (pos == RecyclerView.NO_POSITION) {
                continue
            }
            if (pos < minPos) {
                minPos = pos
                minPosView = child
            }
            if (pos > maxPos) {
                maxPos = pos
                maxPosView = child
            }
        }
        if (minPosView == null || maxPosView == null) {
            return INVALID_DISTANCE
        }
        val start: Int = Math.min(helper!!.getDecoratedStart(minPosView),
                helper!!.getDecoratedStart(maxPosView))
        val end: Int = Math.max(helper.getDecoratedEnd(minPosView),
                helper.getDecoratedEnd(maxPosView))
        val distance = end - start
        return if (distance == 0) {
            INVALID_DISTANCE
        } else 1f * distance / (maxPos - minPos + 1)
    }


    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper? {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return mHorizontalHelper
    }

}