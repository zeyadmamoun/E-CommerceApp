package com.zeyadmaamoun.android.e_commerceapp.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.zeyadmaamoun.android.e_commerceapp.R
import com.zeyadmaamoun.android.e_commerceapp.databinding.FragmentHomeBinding
import com.zeyadmaamoun.android.e_commerceapp.utils.ConnectivityObserver
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by inject()
    private val connectivityObserver: ConnectivityObserver by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //here we observe the connection status and upon it we make a decision.
        lifecycleScope.launch {
            connectivityObserver.observe().collectLatest { status ->
                if (status == ConnectivityObserver.ConnectionStatus.Connected) {
                    viewModel.getProducts()
                } else {
                    Snackbar.make(requireActivity(), binding.coordinator, "No Internet Connection", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

        val adapter = ProductsListAdapter {
            Log.i("HomeFragment", "${it.title} is clicked")
        }

        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        onCategoryChanged()
    }

    //here we take the text of the chip when selected and do the filtering on that text
    //the text of the chip must be the same as the products category in spelling.
    private fun onCategoryChanged() {
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            binding.apply {
                if (checkedIds.isEmpty()) {
                    viewModel!!.changeCategory("all")
                } else {
                    val chip = root.findViewById<Chip>(checkedIds[0])
                    viewModel!!.changeCategory(chip.text.toString())
                }
            }
        }
    }
}