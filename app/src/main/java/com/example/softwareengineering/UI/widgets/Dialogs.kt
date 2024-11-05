package com.example.softwareengineering.UI.widgets

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button

class CustomDialog(context: Context, layoutResId: Int) : Dialog(context) {
    init {
        setContentView(layoutResId)
        setCancelable(false) // 模态弹窗：不允许点击dialog外的部分取消，阻止原页面交互

        // 你可以在这里添加更多的初始化逻辑，例如绑定按钮等
    }

    fun setOnButtonClickListener(buttonId: Int, listener: View.OnClickListener) {
        findViewById<Button>(buttonId)?.setOnClickListener {
            listener.onClick(it)
            dismiss() // 关闭弹窗
        }
    }
}
