package com.example.softwareengineering

data class TextBookItem(
    val imageResId: Int, // 网格项的图像资源 ID
    val title: String // 网格项的标题
)

// 定义题目数据类
data class Question(
    val operator: String,    // 运算符号
    val operand1: Int,       // 第一个运算数
    val operand2: Int,       // 第二个运算数
    val answer: Int          // 答案

) {
    companion object {
        fun generateQuestion(operator: String): Question {
            return when (operator) {
                "/" -> {
                    // 生成两个数，相乘得到被除数，作为 dividend
                    val divisor = (1..10).random()
                    val quotient = (1..10).random()
                    val dividend = divisor * quotient
                    Question("/", dividend, divisor, quotient)
                }

                "*" -> {
                    val operand1 = (1..10).random()
                    val operand2 = (1..10).random()
                    Question("*", operand1, operand2, operand1 * operand2)
                }

                "+" -> {
                    val operand1 = (1..50).random()
                    val operand2 = (1..50).random()
                    Question("+", operand1, operand2, operand1 + operand2)
                }

                "-" -> {
                    val operand1 = (1..50).random()
                    val operand2 = (1..operand1).random()
                    Question("-", operand1, operand2, operand1 - operand2)
                }

                else -> throw IllegalArgumentException("Invalid operator")
            }
        }
    }
}