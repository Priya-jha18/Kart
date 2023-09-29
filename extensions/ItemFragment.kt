package com.example.servicekartcustomer.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.*
import com.example.servicekartcustomer.dashboard.ItemStoreAdapter
import com.example.servicekartcustomer.dashboard.ItemStoreData
import com.example.servicekartcustomer.myOrders.RecommendedAdapter
import com.example.servicekartcustomer.myOrders.RecommendedData
import kotlinx.android.synthetic.main.fragment_item.*

class ItemFragment : Fragment() {

    var listItem: ArrayList<ItemStoreData> = ArrayList()
    var listRecom: ArrayList<RecommendedData> = ArrayList()


    // Required empty public constructor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listItem.add(ItemStoreData("Britania bread", "$220", "20kg", "Add", R.drawable.milk))
        listItem.add(ItemStoreData("Groceries", "$80", "20gm", "Add", R.drawable.groceries))
        listItem.add(ItemStoreData("Meat", "$220", "20kg", "Add", R.drawable.meat))
        listItem.add(ItemStoreData("Food", "$220", "20kg", "Add", R.drawable.food))

        recycleItem.layoutManager = LinearLayoutManager(requireContext())
        recycleItem.adapter = ItemStoreAdapter(this, listItem)
    

        listItem.add(
            RecommendedData(
                "24/7 Store",
                "Dairy Milk Services | 30 Min | 3 Km",
                R.drawable.xmas
            )
        )
        listItem.add(
            RecommendedData(
                "Mother Dairy",
                "Milk Services | 30 Min | 5 Km",
                R.drawable.milk
            )
        )
        listItem.add(
            RecommendedData(
                "Car Cleaning",
                "Carwash Services | 30 Min | 15 Km",
                R.drawable.food
            )
        )
        recycleview2.layoutManager = LinearLayoutManager(requireContext())
        recycleview2.adapter = RecommendedAdapter(this, listRecom)
    }
}

private fun <E> ArrayList<E>.add(element: RecommendedData) {

}




