package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.servicekartcustomer.extensions.BaseActivity
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.activity_categories.*

class Categories : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        setAdapter()

        ivBackBlack.setOnClickListener{
            onBackPressed()
    }
    }
    private fun setAdapter(){

        var categoryList: ArrayList<CategoryData> = ArrayList()
        rvItem.layoutManager = GridLayoutManager(this, 2)

        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png", "Car Wash")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/3143/3143643.png", "Meat")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/372/372973.png", "Milk")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/837/837560.png", "Egg")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png", "Car Wash")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/3143/3143643.png", "Meat")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/372/372973.png", "Milk")
        )
        categoryList.add(
            CategoryData("https://cdn-icons-png.flaticon.com/512/837/837560.png", "Egg")
        )
        rvItem.adapter = CategoryAdapter(this, categoryList)

    }
}