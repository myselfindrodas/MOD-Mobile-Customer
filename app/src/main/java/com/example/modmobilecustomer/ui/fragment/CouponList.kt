package com.example.modmobilecustomer.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.modmobilecustomer.R
import com.example.modmobilecustomer.data.ApiClient
import com.example.modmobilecustomer.data.ApiHelper
import com.example.modmobilecustomer.data.model.couponmastermodel.CouponRequest
import com.example.modmobilecustomer.data.model.couponmastermodel.Data
import com.example.modmobilecustomer.data.modelfactory.CommonModelFactory
import com.example.modmobilecustomer.databinding.FragmentCouponListBinding
import com.example.modmobilecustomer.ui.MainActivity
import com.example.modmobilecustomer.ui.adapter.MyCouponAdapter
import com.example.modmobilecustomer.utils.Shared_Preferences
import com.example.modmobilecustomer.utils.Status
import com.example.modmobilecustomer.utils.Utilities
import com.example.modmobilecustomer.viewmodel.CommonViewModel

class CouponList : Fragment(), MyCouponAdapter.OnItemClickListener {

    lateinit var binding:FragmentCouponListBinding
    lateinit var mainActivity: MainActivity
    private var myCouponAdapter: MyCouponAdapter? = null
    private lateinit var viewModel: CommonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coupon_list, container, false)
        val root = binding.root
        mainActivity = activity as MainActivity

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        binding.topBarFavorities.ivBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()

        }

        binding.topBarFavorities.tvTopBar.text = "My Coupons"

        mainActivity.setBottomNavigationVisibility(false)

        myCouponAdapter = MyCouponAdapter(mainActivity, this@CouponList)
        binding.rvmyCoupons.adapter = myCouponAdapter
        binding.rvmyCoupons.layoutManager = GridLayoutManager(mainActivity, 1)
        myCouponlist()

    }

    override fun onClick(position: Int, view: View, mMycouponModelArrayList: ArrayList<Data>) {

        setFragmentResult("11", bundleOf("coupontitle" to mMycouponModelArrayList[position].couponCode, "discountprice" to mMycouponModelArrayList[position].couponAmt))
        findNavController().navigateUp()


    }



    private fun myCouponlist(){

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.couponmaster(CouponRequest(couponCode = "",
                customerGroupId = "Customer",
            token = Shared_Preferences.getToken().toString())).observe(mainActivity) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            mainActivity.hideProgressDialog()
                            resource.data?.let {itResponse->

                                if (itResponse.response.status.equals("true")) {

                                    myCouponAdapter?.updateData(itResponse.response.data)

                                } else {

                                    Toast.makeText(
                                        mainActivity,
                                        resource.data.response.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }


                        }
                        Status.ERROR -> {
                            mainActivity.hideProgressDialog()

                            Log.d(ContentValues.TAG, "print-->"+ it.message)
                            if (it.message!!.contains("404",true)) {
                                val builder = android.app.AlertDialog.Builder(mainActivity)
                                builder.setMessage("No Orders Found!")
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.blue))
                                }
                                alert.show()
                            }else
                                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()


                        }

                        Status.LOADING -> {
                            mainActivity.showProgressDialog()
                        }

                    }

                }
            }

        } else {
            Toast.makeText(mainActivity, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()
        }

    }


}