package com.example.softwareengineering.UI.screen


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.softwareengineering.MathErrorActivity

import com.example.softwareengineering.R


class ThirdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.third_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mathError = view.findViewById<ImageView>(R.id.math_error)

        mathError.setOnClickListener {
            // 启动 MathErrorActivity
            val intent = Intent(requireContext(), MathErrorActivity::class.java)
            startActivity(intent)
        }
    }
}