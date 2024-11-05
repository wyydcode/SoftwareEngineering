package com.example.softwareengineering.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.softwareengineering.R
import com.example.softwareengineering.TextBookItem

class SecondViewModel : ViewModel() {
    private val _itemList = MutableLiveData<List<TextBookItem>>()
    val itemList: LiveData<List<TextBookItem>> get() = _itemList

    init {
        loadItems()
    }

    private fun loadItems() {
        // 设置示例数据
        _itemList.value = listOf(
            TextBookItem(R.drawable.icon_exampaper_course_chinese, "语文教材"),
            TextBookItem(R.drawable.icon_exampaper_course_math, "数学教材"),
            TextBookItem(R.drawable.icon_exampaper_course_english, "英语教材"),
        )
    }
}
