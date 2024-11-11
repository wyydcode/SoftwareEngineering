package com.example.softwareengineering

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MathErrorRecycleViewAdapter(private var errorList: List<Question>) :
    RecyclerView.Adapter<MathErrorRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionText: TextView = view.findViewById(R.id.question_text)
        val answerText: TextView = view.findViewById(R.id.answer_text)
        val userAnswerText: TextView = view.findViewById(R.id.uesr_answer_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_math_error, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = errorList[position]
        holder.questionText.text = "题目: ${question.operand1} ${question.operator} ${question.operand2} = ?"
        holder.userAnswerText.text = "我的答案:${question.userInput}"
        holder.answerText.text = "正确答案: ${question.answer}"
    }

    override fun getItemCount(): Int = errorList.size

    // 更新数据
    fun updateData(newErrorList: List<Question>) {
        errorList = newErrorList
        notifyDataSetChanged()
    }
}