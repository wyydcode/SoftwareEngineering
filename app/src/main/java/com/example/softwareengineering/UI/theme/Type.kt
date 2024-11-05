package com.example.softwareengineering.UI.screen

import android.content.Context
import android.graphics.Typeface
import android.text.TextPaint
import androidx.core.content.res.ResourcesCompat
import com.example.softwareengineering.R


object AppTypography {

    private var myTextTypeface: Typeface? = null
    private var timerTypeface: Typeface? = null

    // 初始化方法，接收 Context 参数
    fun initialize(context: Context) {
        myTextTypeface = ResourcesCompat.getFont(context, R.font.fangzhengzhunyuan )
        timerTypeface = ResourcesCompat.getFont(context,R.font.fangzhengzhunyuan )
    }

    val large: TextStyle = TextStyle(
        textSize = 28f,
        typeface = myTextTypeface
    )

    val big: TextStyle = TextStyle(
        textSize = 24f,
        typeface = myTextTypeface
    )

    val medium: TextStyle = TextStyle(
        textSize = 20f,
        typeface = myTextTypeface
    )

    val small: TextStyle = TextStyle(
        textSize = 18f,
        typeface = myTextTypeface
    )

    val tiny: TextStyle = TextStyle(
        textSize = 16f,
        typeface = myTextTypeface
    )

    val mainTextStyle: TextStyle = TextStyle(
        textSize = 24f,
        typeface = myTextTypeface
    )

    val bigTitle: TextStyle = TextStyle(
        textSize = 30f,
        typeface = myTextTypeface
    )

    val timer: TextStyle = TextStyle(
        textSize = 70f,
        typeface = timerTypeface
    )

    val midTitle: TextStyle = TextStyle(
        textSize = 26f,
        typeface = myTextTypeface
    )

    val backTitle: TextStyle = TextStyle(
        textSize = 24f,
        typeface = myTextTypeface
    )

    val smallTitle: TextStyle = TextStyle(
        textSize = 18f,
        typeface = myTextTypeface
    )

    val cardTitle: TextStyle = TextStyle(
        textSize = 24f,
        typeface = myTextTypeface
    )

    val settingCardItem: TextStyle = TextStyle(
        textSize = 20f,
        typeface = myTextTypeface
    )

    val body: TextStyle = TextStyle(
        textSize = 20f,
        typeface = myTextTypeface
    )

    val lightBody: TextStyle = TextStyle(
        textSize = 20f,
        typeface = myTextTypeface,
        fontWeight = Typeface.NORMAL // 可选权重
    )

    val dialogTitle = big
    val dialogMessage = medium

    val textFieldTint = tiny

    val cardSmallTitle: TextStyle = TextStyle(
        textSize = 16f,
        typeface = myTextTypeface
    )

    val cardItemBody: TextStyle = TextStyle(
        textSize = 16f,
        typeface = myTextTypeface
    )

    val label: TextStyle = TextStyle(
        textSize = 18f,
        typeface = myTextTypeface
    )

    val buttonTextMedium: TextStyle = TextStyle(
        textSize = 24f,
        typeface = myTextTypeface
    )

    val buttonTextBig: TextStyle = TextStyle(
        textSize = 32f,
        typeface = myTextTypeface
    )

    val buttonTextSmall: TextStyle = TextStyle(
        textSize = 12f,
        typeface = myTextTypeface,
        fontWeight = Typeface.NORMAL // 可选权重
    )

    val description: TextStyle = TextStyle(
        textSize = 14f,
        typeface = myTextTypeface,
        color = grey1 // 可选颜色
    )
}

object MarkTypography {
    val h1: TextStyle = TextStyle(
        textSize = 26f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    )

    val h2: TextStyle = TextStyle(
        textSize = 24f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    )

    val h3: TextStyle = TextStyle(
        textSize = 22f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    )

    val h4: TextStyle = TextStyle(
        textSize = 20f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    )

    val h5: TextStyle = TextStyle(
        textSize = 18f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    )

    val h6: TextStyle = TextStyle(
        textSize = 16f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    )

    val body1: TextStyle = TextStyle(
        textSize = 16f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    )

    val body2: TextStyle = TextStyle(
        textSize = 16f,
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    )
}

// 定义 TextStyle 类
data class TextStyle(
    val textSize: Float,
    val typeface: Typeface? = null,
    val fontWeight: Int? = null, // 添加可选字体权重
    val color: Int? = null // 添加可选颜色
) {
    fun applyTo(paint: TextPaint) {
        paint.textSize = textSize
        typeface?.let { paint.typeface = it }
        fontWeight?.let { paint.setTypeface(Typeface.create(typeface, it)) }
        color?.let { paint.color = it }
    }
}
