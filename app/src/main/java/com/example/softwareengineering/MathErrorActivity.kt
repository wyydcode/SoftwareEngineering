package com.example.softwareengineering
import MathErrorViewModel
import MathErrorViewModelFactory
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.softwareengineering.DatabaseHelper
import com.example.softwareengineering.R

class MathErrorActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dbHelper: DatabaseHelper
    private val viewModel: MathErrorViewModel by viewModels { MathErrorViewModelFactory(dbHelper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirty_question)

        dbHelper = DatabaseHelper(this)

        // 初始化 ViewPager 和适配器
        viewPager = findViewById(R.id.viewPager)

        // 观察错题列表的变化，并初始化适配器
        viewModel.mistakes.observe(this, Observer { mistakes ->
            val adapter = MathProblemAdapter(mistakes.toMutableList(), viewModel,dbHelper)
            viewPager.adapter = adapter
        })

        // 观察剩余错题数的变化
        viewModel.remainingMistakes.observe(this, Observer { mistakesNumber ->
            // 更新 UI 中的错题数，比如:
            // findViewById<TextView>(R.id.question_number_left).text = "剩余错题数: $mistakesNumber"
        })
    }
}
