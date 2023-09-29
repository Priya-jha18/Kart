package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.extensions.BaseActivity
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.activity_all_stores.*


class AllStoresActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_stores)

        ivBackBlack.setOnClickListener{
            onBackPressed()
        }
        setAdapter()
    }
    private fun setAdapter() {

        rvStoreItemList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvStoreDetail.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        var storeItemList: ArrayList<StoreItemData> = ArrayList()
        storeItemList.add(
            StoreItemData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png", "Car Wash")
        )
        storeItemList.add(
            StoreItemData("https://cdn-icons-png.flaticon.com/512/3143/3143643.png", "Meat")
        )
        storeItemList.add(
            StoreItemData("https://cdn-icons-png.flaticon.com/512/372/372973.png", "Milk")
        )
        storeItemList.add(
            StoreItemData("https://cdn-icons-png.flaticon.com/512/837/837560.png", "Egg")
        )
        rvStoreItemList.adapter = StoreItemAdapter(this, storeItemList)


        var storeCategoryList: ArrayList<StoreCategoryData> = ArrayList()
        storeCategoryList.add(
            StoreCategoryData(
                "https://cdn-icons-png.flaticon.com/512/2052/2052358.png",
                "Cars24",
                "CarServices",
                "30Min",
                "20Km"
            )
        )
        storeCategoryList.add(
            StoreCategoryData(
                "https://cdn-icons-png.flaticon.com/512/3143/3143643.png",
                "NazeerFoods",
                "MuttonServices",
                "30Min",
                "20Km"
            )
        )
        storeCategoryList.add(
            StoreCategoryData(
                "https://cdn-icons-png.flaticon.com/512/372/372973.png",
                "Mother Dairy",
                "Milkservices",
                "30Min",
                "20Km"
            )
        )
        storeCategoryList.add(
            StoreCategoryData(
                "https://cdn-icons-png.flaticon.com/512/837/837560.png",
                "EgguGo",
                "EggServices",
                "30Min",
                "20Km"
            )
        )
        rvStoreDetail.adapter = StoreCategoryAdapter(this, storeCategoryList)
    }

}






