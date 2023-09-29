package com.example.servicekartcustomer.extensions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.servicekartcustomer.dashboard.AboutStoreFragment
import com.example.servicekartcustomer.R
import com.google.android.material.tabs.TabLayout


class StoreDetailsActivity : AppCompatActivity() {
    lateinit var tablayoutStore: TabLayout
    lateinit var frame: FrameLayout
    lateinit var fragment: Fragment
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)

        tablayoutStore = findViewById<View>(R.id.tablayoutStore) as TabLayout
        frame = findViewById<View>(R.id.frame) as FrameLayout
        fragment = ItemFragment()
        frag()
    }
    private fun frag() {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment as ItemFragment)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
        tablayoutStore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Fragment fragment = null;
                when (tab.position) {
                    0 -> fragment = ItemFragment()
                    1 -> fragment = CategoriesFragment()
                    2 -> fragment = AboutStoreFragment()

                }
                val fm: FragmentManager = supportFragmentManager
                val ft: FragmentTransaction = fm.beginTransaction()
                ft.replace(R.id.frame, fragment)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })




    }

}