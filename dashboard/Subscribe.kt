package com.example.servicekartcustomer.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.servicekartcustomer.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_subscribe.*

class Subscribe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe)

        val iconList = arrayListOf<String>("Daily", "Alternate Days", "Select Days")




        tlSetDays.addTab(tlSetDays.newTab().setText("Daily"))
        tlSetDays.addTab(tlSetDays.newTab().setText("Alternate Days"))
        tlSetDays.addTab(tlSetDays.newTab().setText("Select Days"))


        val adapter = AdapterSetMode(supportFragmentManager, tlSetDays.tabCount)
        vpSetDays.adapter = adapter
        tlSetDays.setupWithViewPager(vpSetDays)
        for (i in 0 until tlSetDays.tabCount) {
            tlSetDays.getTabAt(i)?.text = iconList[i]
        }
        tlSetDays.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vpSetDays.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    class AdapterSetMode(fm: FragmentManager, private val count: Int) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return count
        }

        override fun getItem(p0: Int): Fragment {
            when (p0) {
                0 -> return Daily()
                1 -> return AlternateDaysFragment()
                else -> return SelectDaysFragment()
            }
        }

    }
}
