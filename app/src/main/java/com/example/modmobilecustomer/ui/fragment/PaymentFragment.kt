package com.example.modmobilecustomer.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import com.example.modmobilecustomer.R
import com.example.modmobilecustomer.data.ApiClient
import com.example.modmobilecustomer.data.ApiHelper
import com.example.modmobilecustomer.data.modelfactory.CommonModelFactory
import com.example.modmobilecustomer.databinding.FragmentPaymentBinding
import com.example.modmobilecustomer.ui.MainActivity
import com.example.modmobilecustomer.viewmodel.CommonViewModel

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var viewModel: CommonViewModel
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)
        mainActivity = activity as MainActivity
        val root = binding.root
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }
        viewModel = vm
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        binding.topBarPayment.tvTopBar.setText("Payment")

        binding.topBarPayment.ivBack.setOnClickListener {
            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        mainActivity.setBottomNavigationVisibility(false)

        binding.llGooglePay.setOnClickListener {

        }

        binding.llPhonepe.setOnClickListener {

        }

        binding.llUpi.setOnClickListener {

        }

        binding.llByCards.setOnClickListener {

        }

        binding.llNetBanking.setOnClickListener {

        }

        binding.llCashOnDelivery.setOnClickListener {

        }

    }
}