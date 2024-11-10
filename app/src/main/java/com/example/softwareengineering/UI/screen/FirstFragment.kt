package com.example.softwareengineering.UI.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.softwareengineering.viewModel.FirstViewModel
import com.example.softwareengineering.R

class FirstFragment : Fragment() {

    private val viewModel: FirstViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val start30: ImageButton = view.findViewById(R.id.start_30) // 30题挑战按钮
        val startExercise: ImageButton = view.findViewById(R.id.start_exercise) // 正常口算练习按钮
        val degreeSpinner: Spinner = view.findViewById(R.id.degree_choose_spinner)
        val amountChoose: TextView = view.findViewById(R.id.amount_choose)

        // 获取四个复选框
        val checkboxAdd: CheckBox = view.findViewById(R.id.checkbox_add)
        val checkboxSubtract: CheckBox = view.findViewById(R.id.checkbox_subtract)
        val checkboxMultiply: CheckBox = view.findViewById(R.id.checkbox_multiply)
        val checkboxDivide: CheckBox = view.findViewById(R.id.checkbox_divide)


        // 设置难度选择的 Spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.difficulty_levels, // 在 res/values/strings.xml 中定义的数组资源
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            degreeSpinner.adapter = adapter
        }

        // 在按钮点击时执行题目练习等功能
        start30.setOnClickListener {
            // 根据参数启动正常口算练习
            // 获取选中的运算符
            val operators = mutableListOf<String>()
            if (checkboxAdd.isChecked) operators.add("+")
            if (checkboxSubtract.isChecked) operators.add("-")
            if (checkboxMultiply.isChecked) operators.add("×")
            if (checkboxDivide.isChecked) operators.add("÷")

            // 获取选择的难度和题目数量
            val degree = degreeSpinner.selectedItemPosition + 1 // 获取选择的难度，假设从1开始
            val amountText = amountChoose.text.toString()
            Log.d("FirstFragment", "amountText: $amountText")
            val amount = if (amountText.isEmpty()) 30 else amountText.toInt()

            // 将选中的运算符列表和其他参数传递给下一个Activity
            val intent = Intent(requireContext(), ThirtyQuestionChallengeActivity::class.java).apply {
                putExtra("degree", degree)
                putExtra("amount", amount)
                if(!operators.isEmpty()){
                    putStringArrayListExtra("operators", ArrayList(operators)) // 传递运算符
                }else{
                    putStringArrayListExtra("operators", ArrayList(listOf("+","-","*","/"))) // 传递运算符
                }
            }
            startActivity(intent)
        }

        startExercise.setOnClickListener {

        }
    }
    private fun convertDegreeToInt(degreeText: String): Int {
        return when (degreeText) {
            "一年级" -> 1
            "二年级" -> 2
            "三年级" -> 3
            else -> 0 // 处理无效输入
        }
    }
}
