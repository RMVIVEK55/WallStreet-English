package com.wse.project.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wse.project.databinding.ItemPassengerBinding
import com.wse.project.networkdb.Datum
import com.wse.project.utils.Utils.loadImage

class PassengersAdapter :
    PagingDataAdapter<Datum, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengersViewHolder {
        return PassengersViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PassengersViewHolder(private val binding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindPassenger(item: Datum) = with(binding) {
            imageViewAirlinesLogo.loadImage(item!!.airline.get(0).logo!!)
            textViewHeadquarters.text = item.airline!!.get(0).headQuaters
            textWeb.text=item.airline!!.get(0).website
            textEstab.text=item.airline!!.get(0).established
            textName.text=item.airline!!.get(0).name
            textCountry.text=item.airline!!.get(0).country
            textViewNameWithTrips.text = "${item!!.name}, ${item!!.trips} Trips"
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Datum>() {
        override fun areItemsTheSame(oldItem: Datum, newItem: Datum): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Datum, newItem: Datum): Boolean {
            return oldItem == newItem
        }
    }
}