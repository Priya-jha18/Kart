package com.example.servicekartcustomer.dashboard


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.UserAddress
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import kotlinx.android.synthetic.main.activity_summary.view.*
import kotlinx.android.synthetic.main.item_address.view.*


class AddressAdapter(val context: Context, private  val userAddress: List<UserAddress>) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userAddress[position])

    }

    override fun getItemCount(): Int {
        return userAddress.size
    }


    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_address, parent, false)) {

        fun bind(addressAdapter: UserAddress) {

            itemView.tvAddressType.text = addressAdapter.addressType
            itemView.tvName.text=addressAdapter.name
            itemView.tvHomeAddress.text= "${addressAdapter.flatNo}${addressAdapter.address}${addressAdapter.pincode}${addressAdapter.phoneNumber}"
            itemView.tvNumber.text= addressAdapter.phoneNumber
        }
    }
}


