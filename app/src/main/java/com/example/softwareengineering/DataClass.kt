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
    val answer: Int,          // 答案
    var isAnswered: Boolean = false,
    var isCorrect: Boolean = false,
    val userInput: Int = 0,        //用户输入
) {
    companion object {
        fun generateQuestion(operator: String, degree: Int): Question {
            // 设置每个难度级别的最大值，1级(1-10)，2级(1-100)，3级(1-1000)
            val maxRange = 10 * Math.pow(10.0, (degree - 1).toDouble()).toInt()

            return when (operator) {
                "/" -> {
                    val divisor = (1..maxRange).random()
                    val quotient = (1..maxRange / divisor).random() // 确保商不超过最大值
                    val dividend = divisor * quotient
                    Question("/", dividend, divisor, quotient)
                }

                "*" -> {
                    val operand1 = (1..maxRange).random()
                    val operand2 = (1..maxRange).random()
                    Question("*", operand1, operand2, operand1 * operand2)
                }

                "+" -> {
                    val operand1 = (1..maxRange).random()
                    val operand2 = (1..maxRange).random()
                    Question("+", operand1, operand2, operand1 + operand2)
                }

                "-" -> {
                    val operand1 = (1..maxRange).random()
                    val operand2 = (1..operand1).random() // operand2 应该小于等于 operand1
                    Question("-", operand1, operand2, operand1 - operand2)
                }

                else -> throw IllegalArgumentException("Invalid operator")
            }
        }
    }
}
