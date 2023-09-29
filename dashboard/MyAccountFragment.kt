package com.example.servicekartcustomer.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.authentication.EditProfileActivity
import com.example.servicekartcustomer.databinding.FragmentMyAccountBinding
import com.example.servicekartcustomer.extensions.IntentConstants
import com.example.servicekartcustomer.extensions.SetGetData
import com.example.servicekartcustomer.myOrders.SavedCardsFragment
import kotlinx.android.synthetic.main.fragment_my_account.*


class MyAccountFragment : Fragment() {
    private lateinit var binding: FragmentMyAccountBinding
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  if (type == "Profile") {
            getProfileViewModel.getProfile(SetGetData.getUserId(this))*/


        }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_account,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initclicks()
       showData()

    }

    override fun onResume() {
            super.onResume()
            SetGetData.getName(requireContext())
            SetGetData.getProfilePic(requireContext())
        SetGetData.getPhone(requireContext())
        }


    private fun showData() {
        Glide.with(this)
            .load(SetGetData.getProfilePic(requireContext()))
            .into(binding.ivMyProfilePic)
        binding.tvMyName.text = SetGetData.getName(requireContext())
        binding.tvMyMobileNumber.text = SetGetData.getPhone(requireContext())
        binding.tvMyMail.text = SetGetData.getEmail(requireContext())
    }





    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.MainFrame, fragment)
            .commit()
    }

    private fun initclicks() {
        tvMyAddress.setOnClickListener {
            startActivity(
                Intent(requireContext(), MyAddress::class.java)
            )
        }
        tvCards.setOnClickListener {
            replaceFragment(SavedCardsFragment())
        }
        tvSettings.setOnClickListener {
            replaceFragment(SettingFragment())
        }
        btnEditProfile.setOnClickListener {
            val intent = (Intent(requireContext(), EditProfileActivity::class.java))

            intent.putExtra(IntentConstants.FROM_DASHBOARD, "Profile")
            startActivity(intent)

        }

    }

    /*private fun getProfileModel() {


    }*/
}
