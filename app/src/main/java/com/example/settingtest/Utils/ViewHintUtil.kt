package com.example.settingtest.Utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * @author zhangyongkang01_sx
 * @date 2020/11/17
 * com.example.settingtest.Utils
 */
class ViewHintUtil {
    companion object {
        fun View.showSnackBar(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
            Snackbar.make(this, text, duration).show()
        }

        fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(context, this, duration).show()
        }
    }
}