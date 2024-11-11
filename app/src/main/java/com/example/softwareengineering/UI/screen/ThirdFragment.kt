package com.example.softwareengineering.UI.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwareengineering.DatabaseHelper
import com.example.softwareengineering.MathErrorActivity
import com.example.softwareengineering.MathErrorRecycleViewAdapter
import com.example.softwareengineering.Question
import com.example.softwareengineering.R

class ThirdFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: MathErrorRecycleViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper = DatabaseHelper(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mathError = view.findViewById<Button>(R.id.math_error)
        val errorCountText = view.findViewById<TextView>(R.id.error_count)
        val recyclerView = view.findViewById<RecyclerView>(R.id.error_recycler_view)

        // 设置RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MathErrorRecycleViewAdapter(emptyList())
        recyclerView.adapter = adapter

        // 启动 MathErrorActivity
        mathError.setOnClickListener {
            val intent = Intent(requireContext(), MathErrorActivity::class.java)
            startActivity(intent)
        }

        // 初始加载错题记录
        loadErrors()
    }

    override fun onResume() {
        super.onResume()
        // 返回前台时重新加载错题记录
        loadErrors()
    }

    // 从数据库加载错题记录并更新UI
    private fun loadErrors() {
        val errorList = dbHelper.getAllMistakes()
        adapter.updateData(errorList)
        val errorCount = errorList.size
        view?.findViewById<TextView>(R.id.error_count)?.text = "错题数量：$errorCount"
    }
}
