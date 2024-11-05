package com.example.softwareengineering.UI.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

        // 在按钮点击时执行题目练习等功能
        start30.setOnClickListener {
                val intent = Intent(requireContext(), ThirtyQuestionChallengeActivity::class.java)
            startActivity(intent) // 启动新的 Activity
        }

        startExercise.setOnClickListener {
            // 启动正常口算练习
        }
    }
}
