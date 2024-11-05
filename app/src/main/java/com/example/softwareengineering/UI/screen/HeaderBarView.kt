package com.example.softwareengineering.UI.screen

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.softwareengineering.R

/**
 * 自定义标题栏视图类，用于实现带左、中、右布局的标题栏。
 * 支持设置文本、图标、颜色、大小等属性，并支持点击事件的回调。
 */
class HeaderBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    // 左、中、右区域的布局和控件
    private var layout_left: ConstraintLayout? = null
    private var layout_right: ConstraintLayout? = null
    private var tv_left: TextView? = null
    private var tv_title: TextView? = null
    private var tv_title2: TextView? = null
    private var tv_right: TextView? = null
    private var iv_left: ImageView? = null
    private var iv_right: ImageView? = null
    private var mClick: onViewClick? = null  // 点击事件接口

    init {
        // 加载布局文件 headbar_view.xml 并初始化视图
        LayoutInflater.from(context).inflate(R.layout.headbar_view, this)
        initView() // 初始化各个视图控件

        // 解析自定义属性
        val array = context.obtainStyledAttributes(attrs, R.styleable.HeaderBarView, defStyleAttr, 0)
        val count = array.indexCount
        for (i in 0 until count) {
            val attr = array.getIndex(i)
            // 根据属性类型设置相应的视图属性
            when (attr) {
                R.styleable.HeaderBarView_headerLeftTextColor -> tv_left!!.setTextColor(
                    array.getColor(attr, Color.BLACK)
                )

                R.styleable.HeaderBarView_headerLeftDrawble -> iv_left!!.setImageResource(
                    array.getResourceId(attr, 0)
                )

                R.styleable.HeaderBarView_headerLeftTextSize -> // 设置左侧文本大小
                    tv_left!!.setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        array.getDimensionPixelSize(attr, 0).toFloat()
                    )

                R.styleable.HeaderBarView_headerLeftText -> tv_left!!.text = array.getString(attr)

                R.styleable.HeaderBarView_headerCenterTextColor -> tv_title!!.setTextColor(
                    array.getColor(attr, Color.BLACK)
                )

                R.styleable.HeaderBarView_headerCenterTitle -> tv_title!!.text = array.getString(attr)

                R.styleable.HeaderBarView_headerCenterTextSize -> tv_title!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    array.getDimensionPixelSize(attr, 0).toFloat()
                )

                R.styleable.HeaderBarView_headerCenterText2Color -> tv_title2!!.setTextColor(
                    array.getColor(attr, Color.BLACK)
                )

                R.styleable.HeaderBarView_headerCenterTitle2 -> tv_title2!!.text = array.getString(attr)

                R.styleable.HeaderBarView_headerCenterText2Size -> tv_title2!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    array.getDimensionPixelSize(attr, 0).toFloat()
                )

                R.styleable.HeaderBarView_headerCenterTitle2Visibility -> {
                    val visible = array.getString(attr)
                    setVisibility(tv_title2, visible) // 设置标题2的可见性
                }

                R.styleable.HeaderBarView_headerCenterTitle2DrawableLeft -> {
                    // 获取并设置标题2左侧的图标
                    var drawableLeft: Drawable? = array.getDrawable(attr)
                    drawableLeft?.setBounds(
                        0, 0, drawableLeft.minimumWidth, drawableLeft.minimumHeight
                    )
                    tv_title2!!.setCompoundDrawables(drawableLeft, null, null, null)
                }

                R.styleable.HeaderBarView_headerRightDrawable -> iv_right!!.setImageResource(
                    array.getResourceId(attr, 0)
                )

                R.styleable.HeaderBarView_headerRightText -> tv_right!!.text = array.getString(attr)

                R.styleable.HeaderBarView_headerRightTextColor -> tv_right!!.setTextColor(
                    array.getColor(attr, Color.BLACK)
                )

                R.styleable.HeaderBarView_headerRightTextSize -> tv_right!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    array.getDimensionPixelSize(attr, 0).toFloat()
                )

                R.styleable.HeaderBarView_headerCenterLeftDrawbleVisibility -> {
                    val visible2 = array.getString(attr)
                    setVisibility(iv_left, visible2) // 设置左图标的可见性
                }

                R.styleable.HeaderBarView_headerCenterRightDrawableVisibility -> {
                    val visible3 = array.getString(attr)
                    setVisibility(iv_right, visible3) // 设置右图标的可见性
                }
            }
        }
        array.recycle()

        // 设置左、右布局的点击事件
        layout_left!!.setOnClickListener { view -> mClick?.leftClick(view) }
        layout_right!!.setOnClickListener { view -> mClick?.rightClick(view) }
    }

    /**
     * 根据 visible 字符串控制视图的可见性
     * @param view 需要设置可见性的视图
     * @param visible 可见性值，可为 "gone"、"visible"、"invisible"
     */
    private fun setVisibility(view: View?, visible: String?) {
        val finalVisible = visible ?: "visible"
        view?.visibility = when (finalVisible) {
            "gone" -> GONE
            "visible" -> VISIBLE
            "invisible" -> INVISIBLE
            else -> VISIBLE
        }
    }

    /**
     * 初始化视图控件，绑定布局中的 ID
     */
    private fun initView() {
        tv_left = findViewById(R.id.tv_left)
        tv_title = findViewById(R.id.tv_title)
        tv_title2 = findViewById(R.id.tv_title_2)
        tv_right = findViewById(R.id.tv_right)
        iv_left = findViewById(R.id.iv_left)
        iv_right = findViewById(R.id.iv_right)
        layout_left = findViewById(R.id.layout_left)
        layout_right = findViewById(R.id.layout_right)
    }

    /**
     * 设置点击事件监听器
     * @param click 自定义的点击事件接口实例
     */
    fun setOnViewClick(click: onViewClick?) {
        mClick = click
    }

    /**
     * 设置标题文本
     */
    fun setTitle(title: String?) {
        if (!TextUtils.isEmpty(title)) {
            tv_title?.text = title
        }
    }

    /**
     * 设置左侧标题文本
     */
    fun setLeftText(title: String?) {
        if (!TextUtils.isEmpty(title)) {
            tv_left?.text = title
        }
    }

    /**
     * 设置右侧标题文本
     */
    fun setRightText(title: String?) {
        if (!TextUtils.isEmpty(title)) {
            tv_right?.text = title
        }
    }

    /**
     * 设置标题文本大小
     */
    fun setTitleSize(size: Int) {
        tv_title?.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
    }

    /**
     * 设置左侧标题文本大小
     */
    fun setLeftTextSize(size: Int) {
        tv_left?.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
    }

    /**
     * 设置右侧标题文本大小
     */
    fun setRightTextSize(size: Int) {
        tv_right?.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
    }

    /**
     * 设置左侧图标资源
     */
    fun setLeftDrawable(res: Int) {
        iv_left?.setImageResource(res)
    }

    /**
     * 设置右侧图标资源
     */
    fun setRightDrawable(res: Int) {
        iv_right?.setImageResource(res)
    }

    /**
     * 点击事件接口，用于外部设置左、右区域的点击回调
     */
    interface onViewClick {
        fun leftClick(view: View?)
        fun rightClick(view: View?)
    }
}
