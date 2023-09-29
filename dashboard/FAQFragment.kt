package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.adapter.FAQAdapter
import com.example.servicekartcustomer.dashboard.model.FAQData
import kotlinx.android.synthetic.main.fragment_f_a_q.*

class FAQFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_f_a_q, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFaq.layoutManager = LinearLayoutManager(requireContext())
        setAdapter()
    }
    private fun setAdapter(){
        var faqlist: ArrayList<FAQData> = ArrayList()
        faqlist.add(
            FAQData("what is your name","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
        faqlist.add(
            FAQData("what is your name","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
        faqlist.add(
            FAQData("what is your name","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
        faqlist.add(
            FAQData("what is your name","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
        faqlist.add(
            FAQData("what is your name","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
        faqlist.add(
            FAQData("what is your name","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
        rvFaq.adapter = FAQAdapter(requireContext(),faqlist)
    }
}