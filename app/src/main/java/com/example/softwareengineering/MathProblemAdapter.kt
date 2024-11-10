package com.example.softwareengineering
import MathErrorViewModel
import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.softwareengineering.Question
import com.example.softwareengineering.ThirtyQuestionChallengeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MathProblemAdapter(
    private val questions: MutableList<Question>,  // 使用 MutableList 以便修改
    private val viewModel: Any, // 使用泛型类型，以支持不同类型的 ViewModel
    val dbHelper: DatabaseHelper
) : RecyclerView.Adapter<MathProblemAdapter.MathProblemViewHolder>() {

    class MathProblemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionNumberText: TextView = itemView.findViewById(R.id.question_number_text)
        val questionText: TextView = itemView.findViewById(R.id.question_text)
        val answerInput: EditText = itemView.findViewById(R.id.answer_input)
        val correctIcon: ImageView = itemView.findViewById(R.id.correct_icon)
        val incorrectIcon: ImageView = itemView.findViewById(R.id.incorrect_icon)
        val submitButton: Button = itemView.findViewById(R.id.submit_button)
        val remainingMistakesText: TextView = itemView.findViewById(R.id.question_number_left) // 剩余错题数
        var errorMode: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathProblemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_math_problem, parent, false)
        return MathProblemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MathProblemViewHolder, position: Int) {
        val question = questions[position]
        // 设置题号和题目内容分行显示
        holder.questionNumberText.text = "第${position + 1}/${questions.size}题"
        holder.questionText.text = "${question.operand1} ${question.operator} ${question.operand2} ="

        // 清空输入框和图标
        holder.answerInput.setText("")
        holder.correctIcon.visibility = View.GONE
        holder.incorrectIcon.visibility = View.GONE
        holder.errorMode = viewModel is MathErrorViewModel
        // 设置是否显示剩余错题数
        if (holder.errorMode) {
            // 计算剩余错题数，统计 isCorrect 为 false 的题目数量
            val remainingMistakes = questions.count { !it.isCorrect }

            // 显示剩余错题数
            holder.remainingMistakesText.visibility = View.VISIBLE
            holder.remainingMistakesText.text = "剩余错题数: $remainingMistakes"
        } else {
            holder.remainingMistakesText.visibility = View.GONE
        }

        if (question.isAnswered) {
            holder.answerInput.isEnabled = false // 锁定输入框
            holder.answerInput.setText(question.userInput.toString()) // 显示用户输入的答案

            // 如果答案错误，标红输入框并显示正确答案
            if (!question.isCorrect) {
                // 设置输入框文字为红色标识错误
                holder.answerInput.setTextColor(Color.RED)  // 红色背景

                // 显示正确答案
                val correctAnswerText = "正确答案: ${question.answer}"
                holder.correctIcon.visibility = View.GONE // 隐藏正确图标
                holder.incorrectIcon.visibility = View.VISIBLE
                holder.incorrectIcon.setImageResource(R.drawable.ic_incorrect)

                // 显示正确答案文本（新增的 TextView）
                holder.remainingMistakesText.visibility = View.VISIBLE
                holder.remainingMistakesText.text = correctAnswerText
            } else {
                // 答案正确，显示正确图标
                holder.correctIcon.visibility = View.VISIBLE
                holder.incorrectIcon.visibility = View.GONE
            }
        } else {
            holder.answerInput.isEnabled = true // 允许输入
            holder.remainingMistakesText.visibility = View.GONE
        }
        // 设置提交按钮点击事件
        holder.submitButton.setOnClickListener {
            val answer = holder.answerInput.text.toString().toIntOrNull()
            if (answer != null) {
                question.isAnswered = true
                question.isCorrect = (answer == question.answer)
                // 判断答案并更新视图
                if (viewModel is MathErrorViewModel) {
                    viewModel.checkAnswer(question, answer)
                    holder.remainingMistakesText.text = "剩余错题数: ${viewModel.getRemainingMistakes()}"
                } else if (viewModel is ThirtyQuestionChallengeViewModel) {
                    viewModel.checkAnswer(question, answer)
                }

                // 更新数据并通知适配器
                questions[position] = question.copy(userInput = answer)
                notifyItemChanged(position)  // 更新该位置的视图
                if (!question.isCorrect) {
                    // 使用协程插入错题
                    CoroutineScope(Dispatchers.Main).launch {
                        dbHelper.insertOrUpdateMistake(question)
                        Log.d(TAG, "onBindViewHolder:${question.operand1} ${question.operator} ${question.operand2} = ${question.answer} ")
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = questions.size
}
