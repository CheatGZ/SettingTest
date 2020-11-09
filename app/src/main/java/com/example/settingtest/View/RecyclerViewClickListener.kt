package com.example.settingtest.View

import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.GridLayoutManager

/**
 * @author zhangyongkang
 * @date 2020/11/09
 * com.example.settingtest.View
 */

interface OnItemClickListener {

    fun onItemClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int)
}

interface OnItemLongClickListener {

    fun onItemLongClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int): Boolean
}

interface OnItemChildClickListener {

    fun onItemChildClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int)
}

interface OnItemChildLongClickListener {
    fun onItemChildLongClick(adapter: BaseRecyclerViewAdapter<*, *>, view: View, position: Int): Boolean
}
interface OnItemChildCheckChangedListener {

    fun onItemChildCheckChanged(adapter: BaseRecyclerViewAdapter<*, *>, view: CompoundButton, position: Int)
}

interface GridSpanSizeLookup {
    fun getSpanSize(gridLayoutManager: GridLayoutManager, viewType: Int, position: Int): Int
}

