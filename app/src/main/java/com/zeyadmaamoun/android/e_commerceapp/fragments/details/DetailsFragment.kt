package com.zeyadmaamoun.android.e_commerceapp.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.zeyadmaamoun.android.e_commerceapp.R
import com.zeyadmaamoun.android.e_commerceapp.databinding.FragmentDetailsBinding
import com.zeyadmaamoun.android.e_commerceapp.fragments.home.LoadingStatus
import org.koin.android.ext.android.inject

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by inject()

    private lateinit var loadingSnackbar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingSnackbar = Snackbar.make(requireActivity(),binding.layout,"Loading .....",Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setDuration(Snackbar.LENGTH_INDEFINITE)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.detailsViewModel = viewModel

        viewModel.getProductById(args.productId)
        checkLoadingDataStatus()
    }

    // here we observe the status of data coming from getProductById().
    private fun checkLoadingDataStatus(){
        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                LoadingStatus.Loading -> {
                    loadingSnackbar.show()
                }
                LoadingStatus.Success -> {
                    loadingSnackbar.dismiss()
                }
                else -> {
                    loadingSnackbar.dismiss()
                    Snackbar.make(requireActivity(),binding.detailsCoordinator,"Loading Failed", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry") { viewModel.getProductById(args.productId) }
                        .show()
                }
            }
        }
    }
}