package com.example.settingtest.View

import com.chad.library.adapter.base.listener.*

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/9
 * com.example.settingtest.View
 */
open interface BaseListenerImp {
    /**
     * Register a callback to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param listener The callback that will be invoked.
     */
    open fun setOnItemClickListener(listener: OnItemClickListener?)
    open fun setOnItemLongClickListener(listener: OnItemLongClickListener?)
    open fun setOnItemChildClickListener(listener: OnItemChildClickListener?)
    open fun setOnItemChildLongClickListener(listener: OnItemChildLongClickListener?)
    open fun setGridSpanSizeLookup(spanSizeLookup: GridSpanSizeLookup?)
}
