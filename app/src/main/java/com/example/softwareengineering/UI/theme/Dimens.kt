package com.example.softwareengineering.UI.screen

import android.view.ViewGroup
import android.graphics.drawable.GradientDrawable
import android.view.View

object Gap {
    const val LARGE = 32 // 超大边距
    const val ZERO = 0 // 零边距
    const val BIG = 16 // 大型边距
    const val MID = 8 // 中型边距
    const val SMALL = 4 // 小型边距
    const val TINY = 2 // 超小型边距
}

object Border {
    const val ZERO = 0 // 零边框
    const val BIG = 4 // 大型边框
    const val MID = 2 // 中型边框
    const val SMALL = 1 // 小型边框
    const val TINY = 0.5 // 超小型边框
}

object Elevation {
    const val ZERO = 0 // 零
    const val BIG = 12 // 大型阴影
    const val MID = 4 // 中型阴影
    const val SMALL = 2 // 小型阴影
    const val TINY = 0.5 // 超小型阴影
}

const val STROKE_SIZE = 20f // 进度条粗细

object ImageSize {
    const val XXLARGE = 64 // 超大图标
    const val XLARGE = 56 // 大图标
    const val LARGE = 48 // 大图标
    const val BIG = 40 // 正常图标
    const val NORMAL = 32 // 正常图标
    const val MID = 24 // 正常图标
    const val SMALL = 16 // 小图标
    const val XSMALL = 12 // 小图标
}

const val BADGE_HEIGHT = 18 // 徽标高度

// 示例函数，用于设置视图的边距
fun setMargin(view: View, size: Int) {
    val params = view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(size, size, size, size)
    view.layoutParams = params
}

// 示例函数，用于设置视图的边框
fun setBorder(view: View, size: Float) {
    val drawable = GradientDrawable().apply {
        setStroke(size.toInt(), android.graphics.Color.BLACK) // 可以根据需要设置颜色
    }
    view.background = drawable
}

// 示例函数，用于设置视图的阴影
fun setElevation(view: View, elevation: Float) {
    // 设置视图的阴影
    view.translationZ = elevation
}
