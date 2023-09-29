package com.example.servicekartcustomer.myOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.fragment_saved_cards.*

class SavedCardsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var savedCardList: ArrayList<CardData> = ArrayList()
        super.onCreate(savedInstanceState)
        rvSavedCArds.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )
        savedCardList.add(
            CardData("2903 2955 3443 2343","Demo Singh","02","2022")
        )

        rvSavedCArds.adapter = SavedCardsAdapter(requireContext(), savedCardList)
    }
}



