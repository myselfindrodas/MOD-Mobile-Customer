package com.example.modmobilecustomer.ui.fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modmobilecustomer.R
import com.example.modmobilecustomer.data.ApiClient
import com.example.modmobilecustomer.data.ApiHelper
import com.example.modmobilecustomer.data.model.OrderStatusData
import com.example.modmobilecustomer.data.model.dashboardmodel.DashboardRequest
import com.example.modmobilecustomer.data.model.myoderlistmodel.OrderListRequest
import com.example.modmobilecustomer.data.model.qclistmodel.Data
import com.example.modmobilecustomer.data.model.qclistmodel.QCRequest
import com.example.modmobilecustomer.data.modelfactory.CommonModelFactory
import com.example.modmobilecustomer.databinding.FragmentQCDetailsBinding
import com.example.modmobilecustomer.ui.MainActivity
import com.example.modmobilecustomer.ui.adapter.OrderStatusAdapter
import com.example.modmobilecustomer.ui.adapter.QCListAdapter
import com.example.modmobilecustomer.utils.Shared_Preferences
import com.example.modmobilecustomer.utils.Status
import com.example.modmobilecustomer.utils.Utilities
import com.example.modmobilecustomer.viewmodel.CommonViewModel

class QCDetails : Fragment(), QCListAdapter.QCItemClickListener {

    private lateinit var viewModel: CommonViewModel
    private lateinit var mainActivity: MainActivity
    lateinit var binding:FragmentQCDetailsBinding
    var qcListAdapter:QCListAdapter?=null
    var imei = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_q_c_details, container, false)
        mainActivity = activity as MainActivity
        val root = binding.root
        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }
        viewModel = vm

        val intent = arguments
        if (intent != null && intent.containsKey("imei")) {
            imei = intent.getString("imei", "")
        }


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBarProductDetails.tvTopBar.text = "Back"

        binding.topBarProductDetails.ivBack.setOnClickListener {

            mainActivity.onBackPressedDispatcher.onBackPressed()
        }

        mainActivity.setBottomNavigationVisibility(false)

        qcListAdapter = QCListAdapter(mainActivity, this@QCDetails)
        binding.rvQClist.layoutManager = LinearLayoutManager(mainActivity)
        binding.rvQClist.adapter = qcListAdapter

        QClist()
    }


    private fun QClist(){

        if (Utilities.isNetworkAvailable(mainActivity)) {

            viewModel.QClist(QCRequest(imei = imei, token = Shared_Preferences.getToken().toString()))
                .observe(mainActivity) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mainActivity.hideProgressDialog()

                                if (resource.data?.response?.status.equals("true")) {

                                    qcListAdapter?.updateData(resource.data?.response?.data!!)
                                    if (resource.data?.response?.data!!.isEmpty()){
                                        binding.tvNOqcfound.visibility = View.VISIBLE
                                    }else{
                                        binding.tvNOqcfound.visibility = View.GONE

                                    }

                                }else{

                                    binding.tvNOqcfound.visibility = View.VISIBLE

                                }



                            }

                            Status.ERROR -> {
                                mainActivity.hideProgressDialog()
                                Log.d(ContentValues.TAG, "print-->" + resource.data?.response?.status)
                                binding.tvNOqcfound.visibility = View.VISIBLE


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

    override fun QCItemOnClick(position: Int, list: ArrayList<Data>, view: View) {



    }

}