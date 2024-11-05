package com.example.softwareengineering.UI.screen

//夜色主题
//val DarkColorPalette = AppColors(
//    primary = yellow1,
//    lightPrimary = lightThemeColor,
//    secondary = secondaryColor,
//    unfocused = grey2,
//    background = black2,
//    titleBar = grey6,
//    divider = black4,
//    textPrimary = white4,
//    textSecondary = grey2,
//    card = grey6,
//    secondBackground = themeBackground,
//    onCard = grey5,
//    list = grey5,
//    info = info,
//    warn = warn,
//    success = green3,
//    error = red2,
//    primaryBtnBg = black1,
//    forbiddenBtnBg = grey1,
//    secondBtnBg = grey5,
//    label = grey4
//)

//白天主题
val LightColorPalette = AppColors(
    primary = primaryColor,
    transparentPrimary = transparentPrimaryColor,
    lightPrimary = lightThemeColor,
    secondary = secondaryColor,
    unfocused = grey1,
    background = white,
    themeBackground = themeBackgroundColor,
    heavyBackground = heavyBackgroundColor,
    titleBar = grey4,
    divider = white3,
    textPrimary = black3,
    textSecondary = grey1,
    card = cardColor,
    lightCard = lightCardColor,
    onCard = white1,
    list = white,
    info = info,
    warn = warn,
    success = green3,
    error = red2,
    primaryBtnBg = orange1,
    negativeBtnBg = LightGray,
    forbiddenBtnBg = white5,
    secondBtnBg = secondBtnBgColor,
    label = grey4,
    selected = orange1
)

data class AppColors(
    val primary: Int,//主色
    val transparentPrimary : Int, //用于渐变的透明主色
    val lightPrimary : Int, //亮主色
    val secondary: Int,//次级主色
    val unfocused: Int,//无焦点颜色
    val background: Int,//默认背景色
    val themeBackground : Int, //主题背景颜色
    val heavyBackground : Int, //较重的背景颜色
    val titleBar: Int,//标题栏渐变主色
    val divider: Int,//分割线颜色
    val textPrimary: Int,//文字主色
    val textSecondary: Int,//文字次要色，一般为不重要文字颜色，例如描述
    val card: Int,//卡片颜色
    val lightCard : Int, //浅色卡片颜色
    val onCard: Int,//卡片内卡片颜色
    val list: Int,//列表颜色，一般与卡片颜色一致
    val info: Int,//提示信息颜色
    val warn: Int,//警告信息颜色
    val success: Int,//成功信息颜色
    val error: Int,//错误信息颜色
    val primaryBtnBg: Int,//按钮主色
    val negativeBtnBg : Int,
    val forbiddenBtnBg: Int,//禁用按钮主色
    val secondBtnBg: Int,//按钮次级主色，一般用于渐变按钮
    val increase: Int = green2,//收入颜色
    val decrease: Int = red,//支出颜色
    val label: Int = grey4, //标签颜色
    val selected : Int,//被选取状态的颜色
    val transparent :Int = Transparent,
    val border : Int = black, //正常的边框
    val lightBorder : Int = grey3, //浅灰色的边框
    val message: Int = grey1,
    val directoryBorder : Int = green1
)
