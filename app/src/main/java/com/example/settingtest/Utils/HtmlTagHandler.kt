package com.example.settingtest.Utils

import android.graphics.Color
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import org.xml.sax.XMLReader
import java.lang.reflect.Field


/**
 * @author zhangyongkang01_sx
 * @date 2020/11/9
 * com.example.settingtest.Utils
 */
class HtmlTagHandler(private var tagName: String) : Html.TagHandler {
    private var startIndex = 0
    private var endIndex = 0

    val attributes: MutableMap<String, String> = HashMap()

    override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
        if (tag.equals(tagName, false)) {
            // 解析所有属性值
            parseAttributes(xmlReader);

            if (opening) {
                startHandleTag(tag, output, xmlReader);
            } else {
                endEndHandleTag(tag, output, xmlReader);
            }

        }
    }

    fun startHandleTag(tag: String?, output: Editable, xmlReader: XMLReader?) {
        startIndex = output.length
    }

    fun endEndHandleTag(tag: String?, output: Editable, xmlReader: XMLReader?) {
        endIndex = output.length

        // 获取属性值
        val color = attributes["color"]
        var size = attributes["size"]
        size = size!!.split("px".toRegex()).toTypedArray()[0]

        // 设置字体大小
        if (!TextUtils.isEmpty(size)) {
            output.setSpan(AbsoluteSizeSpan(size.toInt()), startIndex, endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        // 设置颜色
        if (!TextUtils.isEmpty(color)) {
            output.setSpan(ForegroundColorSpan(Color.parseColor(color)), startIndex, endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun parseAttributes(xmlReader: XMLReader) {
        try {
            val elementField: Field = xmlReader.javaClass.getDeclaredField("theNewElement")
            elementField.isAccessible = true
            val element: Any = elementField.get(xmlReader)
            val attsField: Field = element.javaClass.getDeclaredField("theAtts")
            attsField.isAccessible = true
            val atts: Any = attsField.get(element)
            val dataField: Field = atts.javaClass.getDeclaredField("data")
            dataField.isAccessible = true
            val data = dataField.get(atts) as Array<String>
            val lengthField: Field = atts.javaClass.getDeclaredField("length")
            lengthField.isAccessible = true
            val len = lengthField.get(atts) as Int
            for (i in 0 until len) {
                attributes[data[i * 5 + 1]] = data[i * 5 + 4]
            }
        } catch (e: Exception) {
        }
    }

}