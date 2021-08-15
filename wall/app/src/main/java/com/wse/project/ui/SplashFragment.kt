package com.wse.project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wse.project.R
import com.wse.project.databinding.FragmentSplashBinding
import com.wse.project.utils.NetworkUtils
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mBinding = FragmentSplashBinding.inflate(inflater, container, false)
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            mBinding.textNet.visibility = View.GONE
            lifecycleScope.launchWhenResumed {
                delay(3000)

                findNavController().navigate(R.id.action_splashFragment_to_passengersFragment)
            }
        } else {
            mBinding.textNet.visibility = View.VISIBLE
        }

        return mBinding.root

    }

}