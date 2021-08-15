package com.wse.project.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wse.project.R
import com.wse.project.databinding.ViewLoadingBinding
import com.wse.project.utils.NetworkUtils
import com.wse.project.utils.Utils.visible

class PassengersLoadStateAdapter(val context: Context,
private val retry: () -> Unit
) : LoadStateAdapter<PassengersLoadStateAdapter.PassengerLoadStateViewHolder>() {

    inner class PassengerLoadStateViewHolder(
        private val binding: ViewLoadingBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if(NetworkUtils.isNetworkAvailable(context)){
                if (loadState is LoadState.Error) {
                    binding.textViewError.text = loadState.error.localizedMessage
                }
                binding.progressbar.visible(loadState is LoadState.Loading)
                binding.buttonRetry.visible(loadState is LoadState.Error)
                binding.textViewError.visible(loadState is LoadState.Error)
                binding.buttonRetry.setOnClickListener {
                    retry()
                }
                binding.textViewError.setOnClickListener {
                    retry()
                }

            }
            else
            {binding.progressbar.visibility
                binding.buttonRetry.setOnClickListener {
                    retry()
                }
                binding.textViewError.setText((context as Activity).resources.getString(R.string.no_internet))
                binding.textViewError.setOnClickListener {
                    retry()
                }
                binding.textViewError.visibility=View.VISIBLE
                binding.progressbar.visibility=View.VISIBLE
            }

        }
    }

    override fun onBindViewHolder(holder: PassengerLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PassengerLoadStateViewHolder(
        ViewLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}