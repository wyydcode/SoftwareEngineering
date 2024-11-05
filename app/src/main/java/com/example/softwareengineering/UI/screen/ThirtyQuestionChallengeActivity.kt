package com.example.softwareengineering.UI.screen

import MathProblemAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.softwareengineering.Question
import com.example.softwareengineering.R

class ThirtyQuestionChallengeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MathProblemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirty_question)

        recyclerView = findViewById(R.id.thirty_questions) // 确保你的布局中有这个ID
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 生成30道口算题
        val problems = generateMathProblems()
        adapter = MathProblemAdapter(problems)
        recyclerView.adapter = adapter
    }

    private fun generateMathProblems(): List<Question> {
        val questions = List(30) {
            Question.generateQuestion("/") // 示例生成30道除法题
        }
        return questions
    }
}
