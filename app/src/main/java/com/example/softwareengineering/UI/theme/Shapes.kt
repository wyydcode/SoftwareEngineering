package com.example.softwareengineering.UI.screen

import android.graphics.drawable.GradientDrawable
import android.view.View

object ShapeConstants {
    const val APP_SMALL_CORNER_RADIUS = 4f
    const val APP_MEDIUM_CORNER_RADIUS = 4f
    const val APP_LARGE_CORNER_RADIUS = 0f

    const val CARD_SMALL_CORNER_RADIUS = 10f
    const val CARD_MEDIUM_CORNER_RADIUS = 20f
    const val CARD_LARGE_CORNER_RADIUS = 40f

    const val ROUNDED_SMALL_CORNER_RADIUS = 30f
    const val ROUNDED_MEDIUM_CORNER_RADIUS = 60f
    const val ROUNDED_LARGE_CORNER_RADIUS = 100f

    const val TOP_HALF_SMALL_START_CORNER_RADIUS = 8f
    const val TOP_HALF_SMALL_END_CORNER_RADIUS = 8f
    const val TOP_HALF_MEDIUM_START_CORNER_RADIUS = 16f
    const val TOP_HALF_MEDIUM_END_CORNER_RADIUS = 16f
    const val TOP_HALF_LARGE_START_CORNER_RADIUS = 16f
    const val TOP_HALF_LARGE_END_CORNER_RADIUS = 16f

    object Size {
        const val SMALL = 0
        const val MEDIUM = 1
        const val LARGE = 2
    }
}

/**
 * 设置给定视图的背景形状为矩形，并根据传入的大小参数调整圆角半径。
 * @param view 需要设置形状的视图
 * @param size 大小参数，取值来自ShapeConstants.Size（如SMALL、MEDIUM、LARGE）
 */
fun setAppShape(view: View, size: Int) {
    var cornerRadius = when (size) {
        ShapeConstants.Size.SMALL -> ShapeConstants.APP_SMALL_CORNER_RADIUS
        ShapeConstants.Size.MEDIUM -> ShapeConstants.APP_MEDIUM_CORNER_RADIUS
        ShapeConstants.Size.LARGE -> ShapeConstants.APP_LARGE_CORNER_RADIUS
        else -> 0f
    }
    val shape = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = cornerRadius
        setColor(android.graphics.Color.WHITE) // 你可以根据需要设置颜色
    }
    view.background = shape
}

/**
 * 设置卡片的背景形状为矩形，并根据传入的大小参数调整圆角半径。
 * @param view 需要设置形状的视图
 * @param size 大小参数，取值来自ShapeConstants.Size
 */
fun setCardShape(view: View, size: Int) {
    var cornerRadius = when (size) {
        ShapeConstants.Size.SMALL -> ShapeConstants.CARD_SMALL_CORNER_RADIUS
        ShapeConstants.Size.MEDIUM -> ShapeConstants.CARD_MEDIUM_CORNER_RADIUS
        ShapeConstants.Size.LARGE -> ShapeConstants.CARD_LARGE_CORNER_RADIUS
        else -> 0f
    }
    val shape = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = cornerRadius
        setColor(android.graphics.Color.WHITE) // 你可以根据需要设置颜色
    }
    view.background = shape
}

/**
 * 设置给定视图的背景形状为矩形，并调整圆角半径，主要用于更具圆润风格的设计。
 * @param view 需要设置形状的视图
 * @param size 大小参数，取值来自ShapeConstants.Size
 */
fun setRoundedShape(view: View, size: Int) {
    var cornerRadius = when (size) {
        ShapeConstants.Size.SMALL -> ShapeConstants.ROUNDED_SMALL_CORNER_RADIUS
        ShapeConstants.Size.MEDIUM -> ShapeConstants.ROUNDED_MEDIUM_CORNER_RADIUS
        ShapeConstants.Size.LARGE -> ShapeConstants.ROUNDED_LARGE_CORNER_RADIUS
        else -> 0f
    }
    val shape = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = cornerRadius
        setColor(android.graphics.Color.WHITE) // 你可以根据需要设置颜色
    }
    view.background = shape
}

/**
 * 设置给定视图的背景为矩形，仅调整顶部的两个角为圆角，而底部角为直角。
 * @param view 需要设置形状的视图
 * @param size 大小参数，取值来自ShapeConstants.Size
 */
fun setCardShapesTopHalf(view: View, size: Int) {
    val shape = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        when (size) {
            ShapeConstants.Size.SMALL -> {
                cornerRadii = floatArrayOf(
                    ShapeConstants.TOP_HALF_SMALL_START_CORNER_RADIUS, ShapeConstants.TOP_HALF_SMALL_START_CORNER_RADIUS,
                    ShapeConstants.TOP_HALF_SMALL_END_CORNER_RADIUS, ShapeConstants.TOP_HALF_SMALL_END_CORNER_RADIUS,
                    0f, 0f, 0f, 0f // 其他角为直角
                )
            }
            ShapeConstants.Size.MEDIUM -> {
                cornerRadii = floatArrayOf(
                    ShapeConstants.TOP_HALF_MEDIUM_START_CORNER_RADIUS, ShapeConstants.TOP_HALF_MEDIUM_START_CORNER_RADIUS,
                    ShapeConstants.TOP_HALF_MEDIUM_END_CORNER_RADIUS, ShapeConstants.TOP_HALF_MEDIUM_END_CORNER_RADIUS,
                    0f, 0f, 0f, 0f // 其他角为直角
                )
            }
            ShapeConstants.Size.LARGE -> {
                cornerRadii = floatArrayOf(
                    ShapeConstants.TOP_HALF_LARGE_START_CORNER_RADIUS, ShapeConstants.TOP_HALF_LARGE_START_CORNER_RADIUS,
                    ShapeConstants.TOP_HALF_LARGE_END_CORNER_RADIUS, ShapeConstants.TOP_HALF_LARGE_END_CORNER_RADIUS,
                    0f, 0f, 0f, 0f // 其他角为直角
                )
            }
        }
        setColor(android.graphics.Color.WHITE) // 你可以根据需要设置颜色
    }
    view.background = shape
}

fun setRectangleShape(view: View) {
    val shape = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = 0f // 直角
        setColor(android.graphics.Color.WHITE) // 你可以根据需要设置颜色
    }
    view.background = shape
}
