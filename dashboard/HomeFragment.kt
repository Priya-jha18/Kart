package com.example.servicekartcustomer.dashboard

import BannerOne
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.adapter.AdViewPageAdapter
import com.example.servicekartcustomer.dashboard.adapter.DailyAdapter
import com.example.servicekartcustomer.dashboard.adapter.InstantAdapter
import com.example.servicekartcustomer.dashboard.adapter.OfferAdapter
import com.example.servicekartcustomer.dashboard.model.DailyData
import com.example.servicekartcustomer.dashboard.model.InstantData
import com.example.servicekartcustomer.dashboard.model.OfferData
import com.example.servicekartcustomer.extensions.SetGetData
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var handler: Handler
    val nameList: ArrayList<BannerOne> = ArrayList()
    private lateinit var getHomeViewModel: DashBoardViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHomeViewModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)
        getHomeViewModel.Home(SetGetData.getUserId(requireContext()))

        setAdapter()
        viewPager2Ad()
        initCLicks()
        dashboardSetUpModel()
    }

    private fun dashboardSetUpModel() {
        getHomeViewModel.getHomeData.observe(requireActivity()) {
            val data = it.result.bannerOne
            nameList.clear()
            for (i in data.indices) {
                nameList.add(it.result.bannerOne[i])
//                Glide.with(requireContext())
//                    .load(nameList)
                    /*.into(vpAdvertisement)*/
            }
            Glide.with(requireContext())
                .load(it.result.bannerOne[0].imageUrl[0])
                .into(ivAdFirst)
            Glide.with((requireContext()))
                .load(it.result.bannerOne[0].imageUrl[0])
                .into(ivAdSecond)

            Glide.with((requireContext()))
                .load(it.result.bannerOne[0].imageUrl[0])
                .into(ivAdThird)

            Glide.with((requireContext()))
                .load(it.result.bannerOne[0].imageUrl[0])
                .into(ivAdFourth)
            Glide.with((requireContext()))
                .load(it.result.bannerOne[0].imageUrl[0])
                .into(ivAdFifth)
            Glide.with((requireContext()))
                .load(it.result.bannerOne[0].imageUrl[0])
                .into(ivAdSixth)

        }
        getHomeViewModel.responseError.observe(requireActivity()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        getHomeViewModel.responseError.observe(requireActivity()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


    private val runnable =
        Runnable { vpAdvertisement?.currentItem = vpAdvertisement.currentItem + 1 }

    override fun onPause() {
        super.onPause()
        handler.postDelayed(runnable, 3000)
    }

    private fun initCLicks() {
        tvViewAllDaily.setOnClickListener {
            startActivity(Intent(requireContext(), AllStoresActivity::class.java))
        }
        tvViewAllInstant.setOnClickListener {
            startActivity(Intent(requireContext(), Categories::class.java))
        }

        /* smooth scrolling */
        btnDailyServices.setOnClickListener {
            scrollToView(scrollView, tvDailyServices)
        }
        btnInstantServices.setOnClickListener {
            scrollToView(scrollView, tvInstantServices)
        }
        btnTopOffers.setOnClickListener {
            scrollToView(scrollView, tvTopOffers)
        }
    }

    private fun setAdapter() {
        val dailyList: ArrayList<DailyData> = ArrayList()
        val instantList: ArrayList<InstantData> = ArrayList()
        val offerList: ArrayList<OfferData> = ArrayList()

        rvDailyServices.layoutManager = GridLayoutManager(requireContext(), 2)
        rvInstantServices.layoutManager = GridLayoutManager(requireContext(), 2)
        rvTopOffers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        dailyList.add(
            DailyData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png", "Car Wash")
        )
        dailyList.add(
            DailyData("https://cdn-icons-png.flaticon.com/512/3143/3143643.png", "Meat")
        )
        dailyList.add(
            DailyData("https://cdn-icons-png.flaticon.com/512/372/372973.png", "Milk")
        )
        dailyList.add(
            DailyData("https://cdn-icons-png.flaticon.com/512/837/837560.png", "Egg")
        )
        instantList.add(
            InstantData(
                "https://cdn-icons-png.flaticon.com/512/837/837560.png",
                "Vegetables"
            )
        )
        instantList.add(
            InstantData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png", "Car Wash")
        )
        instantList.add(
            InstantData("https://cdn-icons-png.flaticon.com/512/3143/3143643.png", "Meat")
        )
        instantList.add(
            InstantData("https://cdn-icons-png.flaticon.com/512/372/372973.png", "Milk")
        )
        instantList.add(
            InstantData("https://cdn-icons-png.flaticon.com/512/837/837560.png", "Egg")
        )
        instantList.add(
            InstantData(
                "https://cdn-icons-png.flaticon.com/512/837/837560.png",
                "Drinking Water"
            )
        )
        offerList.add(
            OfferData(
                "https://cdn-icons-png.flaticon.com/512/1139/1139982.png",
                "Mother daily Full cream milk 500ml",
                "₹20"
            )
        )
        offerList.add(
            OfferData(
                "https://cdn-icons-png.flaticon.com/512/1139/1139982.png",
                "Mother daily Full cream milk 500ml",
                "₹20"
            )
        )
        offerList.add(
            OfferData(
                "https://cdn-icons-png.flaticon.com/512/1139/1139982.png",
                "Mother daily Full cream milk 500ml",
                "₹20"
            )
        )
        offerList.add(
            OfferData(
                "https://cdn-icons-png.flaticon.com/512/1139/1139982.png",
                "Mother daily Full cream milk 500ml",
                "₹20"
            )
        )
        offerList.add(
            OfferData(
                "https://cdn-icons-png.flaticon.com/512/1139/1139982.png",
                "Mother daily Full cream milk 500ml",
                "₹20"
            )
        )
        rvDailyServices.adapter = DailyAdapter(requireContext(), dailyList)
        rvInstantServices.adapter = InstantAdapter(requireContext(), instantList)
        rvTopOffers.adapter = OfferAdapter(requireContext(), offerList)
    }

    private fun viewPager2Ad() {

        handler = Handler(Looper.myLooper()!!)

        vpAdvertisement.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })

//        nameList.add(R.drawable.banne)
//        nameList.add(R.drawable.banner)
//        nameList.add(R.drawable.banne)
//        nameList.add(R.drawable.banner)
//        nameList.add(R.drawable.banne)

        vpAdvertisement.adapter = AdViewPageAdapter(nameList,requireContext(), vpAdvertisement)
        vpAdvertisement.offscreenPageLimit = 4
        vpAdvertisement.clipToPadding = false
        vpAdvertisement.clipChildren = false
        vpAdvertisement.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


    }

    /*     smooth scroll to a view  */
    private fun scrollToView(scrollViewParent: ScrollView, view: View) {
        // Get deepChild Offset
        val childOffset = Point()
        getDeepChildOffset(scrollViewParent, view.parent, view, childOffset)
        // Scroll to child.
        scrollViewParent.smoothScrollTo(0, childOffset.y)

    }

    private fun getDeepChildOffset(
        mainParent: ViewGroup,
        parent: ViewParent,
        child: View,
        accumulatedOffset: Point
    ) {
        val parentGroup = parent as ViewGroup
        accumulatedOffset.x += child.left
        accumulatedOffset.y += child.top
        if (parentGroup == mainParent) {
            return
        }
        getDeepChildOffset(mainParent, parentGroup.parent, parentGroup, accumulatedOffset)
    }

    /* transform the viewPager */
/*    private fun setupTransformer(){
    val transformer = CompositePageTransformer()
    transformer.addTransformer(MarginPageTransformer(40))
    transformer.addTransformer{ page, position ->
        val r = 1 - kotlin.math.abs(position)
        page.scaleY = 0.85f + r * 0.14f
    }
    vpAdvertisement.setPageTransformer(transformer)
}*/

}







