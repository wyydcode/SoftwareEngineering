package com.example.softwareengineering

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThirtyQuestionChallengeViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

    // 保存所有生成的题目
    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    // 当前题目索引
    private val _currentQuestionIndex = MutableLiveData<Int>()
    val currentQuestionIndex: LiveData<Int> get() = _currentQuestionIndex

    // 初始化时生成题目
    fun generateMathProblems(degree: Int, amount: Int) {
        val operators = listOf("+", "-", "*", "/")
        val questions = List(amount) {
            val randomOperator = operators.random()
            Question.generateQuestion(randomOperator, degree)
        }
        _questions.value = questions
        _currentQuestionIndex.value = 0 // 从第一个问题开始
    }

    // 选择下一题
    fun nextQuestion() {
        val currentIndex = _currentQuestionIndex.value ?: return
        if (currentIndex < _questions.value?.size?.minus(1) ?: 0) {
            _currentQuestionIndex.value = currentIndex + 1
        }
    }

    // 选择上一题
    fun previousQuestion() {
        val currentIndex = _currentQuestionIndex.value ?: return
        if (currentIndex > 0) {
            _currentQuestionIndex.value = currentIndex - 1
        }
    }

    // 检查答案
    fun checkAnswer(question: Question, userAnswer: Int) {
        question.isAnswered = true
        question.isCorrect = (userAnswer == question.answer)
    }

    // 用于记录错题（可以存储到数据库）
    fun recordMistake(question: Question) {
        // 调用数据库保存错题逻辑（例如调用 DatabaseHelper）
    }

    // 其他需要的业务逻辑可以在这里添加
}
