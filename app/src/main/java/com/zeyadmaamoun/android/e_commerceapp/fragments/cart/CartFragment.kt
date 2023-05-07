package com.zeyadmaamoun.android.e_commerceapp.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zeyadmaamoun.android.e_commerceapp.R
import com.zeyadmaamoun.android.e_commerceapp.databinding.FragmentCartBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CartFragment : Fragment(){

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cartProds.observe(viewLifecycleOwner){
            viewModel.getTotalCost(it)
        }

        val adapter = CartProductsAdapter(viewModel)
        binding.cartRecyclerView.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cartViewModel = viewModel
    }
}