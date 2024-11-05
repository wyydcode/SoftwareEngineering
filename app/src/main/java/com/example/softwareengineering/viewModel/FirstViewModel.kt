package com.example.softwareengineering.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.softwareengineering.Question


class FirstViewModel : ViewModel() {
    // 使用 MutableLiveData 存储题目信息
    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    init {
        // 示例数据
        _questions.value = listOf(
            Question("+", 2, 3, 5),
            Question("-", 7, 4, 3),
            // 可以添加更多题目
        )
    }
}
