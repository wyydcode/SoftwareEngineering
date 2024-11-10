package com.example.softwareengineering.UI.screen
import MathErrorViewModelFactory
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.softwareengineering.DatabaseHelper
import com.example.softwareengineering.MathProblemAdapter
import com.example.softwareengineering.Question
import com.example.softwareengineering.R
import com.example.softwareengineering.ThirtyQuestionChallengeViewModel

class ThirtyQuestionChallengeActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: MathProblemAdapter  // 移除泛型类型参数
    private lateinit var dbHelper: DatabaseHelper // 声明 DatabaseHelper 变量
    private val viewModel: ThirtyQuestionChallengeViewModel by viewModels{ MathErrorViewModelFactory(dbHelper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirty_question)
        dbHelper = DatabaseHelper(this) // 初始化 DatabaseHelper
        viewPager = findViewById(R.id.viewPager) // 确保你的布局中有这个ID

        // 从 Intent 获取传递的年级和题目数量
        val degree = intent.getIntExtra("degree", 1) // 默认值为 1
        val amount = intent.getIntExtra("amount", 30) // 默认值为 30
        // 获取传递的运算符列表
        val operators = intent.getStringArrayListExtra("operators")?.toList() ?: listOf("+", "-", "*", "/")

        // 生成问题
        val problems = generateMathProblems(degree, amount,operators)
        adapter = MathProblemAdapter(problems.toMutableList(), viewModel,dbHelper)  // 移除泛型参数
        viewPager.adapter = adapter

        // 监听页面变化
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // 页面选择时可以在这里处理任何逻辑
            }
        })
    }

    private fun generateMathProblems(degree: Int = 1,amount:Int = 30,operators:List<String> = listOf("+", "-", "*", "/")): List<Question> {
        val questions = List(amount) {
            val randomOperator = operators.random() // 随机选择运算符
            Question.generateQuestion(randomOperator,degree) // 生成对应运算符的题目
        }
        return questions
    }

}
