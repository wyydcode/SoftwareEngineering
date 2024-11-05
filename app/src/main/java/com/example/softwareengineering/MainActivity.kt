// MainActivity.kt
package com.example.softwareengineering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.softwareengineering.UI.screen.MyFragmentPagerAdapter

class MainActivity : AppCompatActivity() {

        private lateinit var mViewPager: ViewPager
        private lateinit var mRadioGroup: RadioGroup
        private lateinit var tab1: RadioButton
        private lateinit var tab2: RadioButton
        private lateinit var tab3: RadioButton

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                initView()

                mRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                        when (checkedId) {
                                R.id.first_fragment -> mViewPager.currentItem = 0
                                R.id.second_fragment -> mViewPager.currentItem = 1
                                R.id.third_fragment -> mViewPager.currentItem = 2
                        }
                }

                mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                        override fun onPageSelected(position: Int) {
                                tab1.isChecked = position == 0
                                tab2.isChecked = position == 1
                                tab3.isChecked = position == 2
                        }

                        override fun onPageScrollStateChanged(state: Int) {}
                })

                // 设置适配器
                mViewPager.adapter = MyFragmentPagerAdapter(supportFragmentManager)
        }

        private fun initView() {
                mViewPager = findViewById(R.id.viewpager)
                mRadioGroup = findViewById(R.id.rg_tab)
                tab1 = findViewById(R.id.first_fragment)
                tab2 = findViewById(R.id.second_fragment)
                tab3 = findViewById(R.id.third_fragment)
        }
}
