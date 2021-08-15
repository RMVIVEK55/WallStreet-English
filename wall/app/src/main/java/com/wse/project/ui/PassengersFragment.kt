package com.wse.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.filter

import com.wse.project.databinding.FragmentPassengersBinding
import com.wse.project.utils.InitializationObject.provideViewModelFactory
import com.wse.project.utils.NetworkUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PassengersFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    private lateinit var mBinding: FragmentPassengersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentPassengersBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(
            requireActivity(),
            provideViewModelFactory()
        )[MainViewModel::class.java]
        initializeView()
        return mBinding.root
    }

fun initializeView()
{
    val passengersAdapter = PassengersAdapter()

    if(NetworkUtils.isNetworkAvailable(requireActivity())) {
        mBinding.recyclerView.adapter = passengersAdapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter(requireActivity()) { passengersAdapter.retry() },
            footer = PassengersLoadStateAdapter(requireActivity()) { passengersAdapter.retry() }
        )

    lifecycleScope.launch {
        mViewModel.passengers.collectLatest { pagedData ->
            passengersAdapter.submitData(pagedData)
        }
    }
}}
}