package com.example.servicekartcustomer.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.*
import com.example.servicekartcustomer.dashboard.CategoriesAdapter
import com.example.servicekartcustomer.dashboard.CategoriesData
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {

    var listcat: ArrayList<CategoriesData> = ArrayList()


    // Required empty public constructor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))
        listcat.add(CategoriesData("milk","bread",R.drawable.milk,R.drawable.bread))

        recycle_categories.layoutManager = LinearLayoutManager(requireContext())
        recycle_categories.adapter = CategoriesAdapter(this, listcat)
    }
}




