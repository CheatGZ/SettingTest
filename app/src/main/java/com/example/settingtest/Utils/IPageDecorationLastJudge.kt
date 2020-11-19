package com.example.settingtest.Utils

interface IPageDecorationLastJudge {
    /**
     * Is the last row in one page
     *
     * @param position
     * @return
     */
    fun isLastRow(position: Int): Boolean

    /**
     * Is the last Colum in one row;
     *
     * @param position
     * @return
     */
    fun isLastColumn(position: Int): Boolean

    fun isPageLast(position: Int): Boolean
}