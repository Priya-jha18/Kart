package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pushNotifications()
    }

    private fun pushNotifications(){
        btn_PushNotifications.setOnCheckedChangeListener { ButtonView, isChecked ->
            if (isChecked) {
                Toast.makeText(requireContext(), "Notifications On", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Notifications Off", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

