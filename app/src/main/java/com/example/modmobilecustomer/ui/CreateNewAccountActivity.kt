package com.example.modmobilecustomer.ui

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import com.example.modmobilecustomer.R
import com.example.modmobilecustomer.base.BaseActivity
import com.example.modmobilecustomer.data.ApiClient
import com.example.modmobilecustomer.data.ApiHelper
import com.example.modmobilecustomer.data.model.addtocartmodel.AddtoCartItem
import com.example.modmobilecustomer.data.model.login_model.LoginRequestModel
import com.example.modmobilecustomer.data.model.registrationsuccessmodel.Recipient
import com.example.modmobilecustomer.data.model.registrationsuccessmodel.RegistrationSuccessRequest
import com.example.modmobilecustomer.data.model.signupmodel.SignupRequest
import com.example.modmobilecustomer.data.model.statemodel.StateListRequest
import com.example.modmobilecustomer.data.modelfactory.CommonModelFactory
import com.example.modmobilecustomer.data.modelfactory.CommonModelFactory2
import com.example.modmobilecustomer.databinding.ActivityCreateNewAccountBinding
import com.example.modmobilecustomer.utils.Shared_Preferences
import com.example.modmobilecustomer.utils.Status
import com.example.modmobilecustomer.utils.Utilities
import com.example.modmobilecustomer.viewmodel.CommonViewModel
import com.example.modmobilecustomer.viewmodel.CommonViewModel2
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CreateNewAccountActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateNewAccountBinding
    private lateinit var viewModel: CommonViewModel
    private lateinit var viewModel2: CommonViewModel2
    var spinnerZoneNameArray = ArrayList<String>()
    var spinnerZoneIdArray = ArrayList<String>()
    var selectedZoneName = ""
    var selectedZoneId = ""
    var currentDate = ""

    override fun resourceLayout(): Int {
        return R.layout.activity_create_new_account
    }

    override fun initializeBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityCreateNewAccountBinding
    }

    override fun setFunction() {

        val vm: CommonViewModel by viewModels {
            CommonModelFactory(ApiHelper(ApiClient.apiService))
        }

        viewModel = vm


        val vm2: CommonViewModel2 by viewModels {
            CommonModelFactory2(ApiHelper(ApiClient.apiService2))
        }

        viewModel2 = vm2

        currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        Log.d(TAG, "date--->"+currentDate)


        binding.zoneSpinner.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {


                if (position > 0) {
                    selectedZoneId = spinnerZoneIdArray[binding.zoneSpinner.selectedItemPosition]
                    selectedZoneName = spinnerZoneNameArray[binding.zoneSpinner.selectedItemPosition]

                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
        })

        zonelist()

        binding.topBarCreateNewAccount.tvTopBar.setText("Back")

        binding.topBarCreateNewAccount.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.etMobileNo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.etMobileNo.text.toString().matches(Regex("^0"))) {
                    // Not allowed
//                    Toast.makeText(mainActivity, "not allowed", Toast.LENGTH_LONG).show()
                    binding.etMobileNo.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {





            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.etName.text.toString().isNullOrEmpty()){
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
            }else if (binding.etMobileNo.text.toString().trim().isEmpty() || binding.etMobileNo.text.toString().trim().length < 10){
                Toast.makeText(this,"Enter Valid Credential", Toast.LENGTH_SHORT).show()
            }else if(binding.etEmailId.text.toString().isEmpty() || !Utilities.isEmail(binding.etEmailId.text.toString().trim())){
                Toast.makeText(this,"Enter your Email",Toast.LENGTH_SHORT).show()
            } else if (binding.zoneSpinner.selectedItem.toString().isEmpty()) {
                Toast.makeText(this, "Select Area Zone", Toast.LENGTH_SHORT).show()
            }  else{

                Signup()
//
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
            }
        }
//
//        zoneList = arrayOf("Retailer", "Distributor")
//        val zoneAdapter =
//            ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, zoneList)
//        binding.zoneSpinner.adapter = zoneAdapter
    }



    private fun zonelist(){

        if (Utilities.isNetworkAvailable(this)) {

            viewModel.statemaster(StateListRequest(state = "", token = Shared_Preferences.getToken().toString())).observe(this) {
                it?.let { resource ->
                    if (resource.data?.response?.status.equals("true")) {

                        spinnerZoneNameArray = ArrayList<String>()
                        spinnerZoneIdArray = ArrayList<String>()
                        spinnerZoneNameArray.add("Choose Area")
                        spinnerZoneIdArray.add("")


                        resource.data?.response?.data?.forEach { i ->

                            spinnerZoneNameArray.add(i.state)
                            spinnerZoneIdArray.add(i.stateCode)

                        }

                        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_spinner_item,
                            spinnerZoneNameArray
                        )
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.zoneSpinner.adapter = spinnerArrayAdapter


                    }


                }
            }

        } else {

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT)
                .show()

        }
    }


    private fun Signup(){


        if (Utilities.isNetworkAvailable(this)) {

            viewModel.signup(SignupRequest(
                address1 = "",
                address2 = "",
                city =  selectedZoneName,
                createDate = currentDate,
                customerGroupId = "Walk-in-Customer",
                email = binding.etEmailId.text.toString(),
                name = binding.etName.text.toString(),
                pancard = "N / A",
                pincode = "",
                state = selectedZoneName,
                telephone = binding.etMobileNo.text.toString(),
                token = Shared_Preferences.getToken().toString()

            )).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()

                            if (resource.data?.response?.data?.get(0)?.msg.equals("Customer Created")){

                                Toast.makeText(this, resource.data?.response?.data?.get(0)?.msg ?:"" , Toast.LENGTH_SHORT).show()
                                registrationsuccess()



                            }else{

                                val builder = AlertDialog.Builder(this)
//                                builder.setMessage(resource.data?.response?.data?.get(0)?.msg)
                                builder.setMessage("Already Customer Created")
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.yellow))
                                }
                                alert.show()


                            }

                        }

                        Status.ERROR -> {
                            hideProgressDialog()
                            //Log.d(ContentValues.TAG, "print-->" + resource.data?.status)
                            if (it.message!!.contains("401", true)) {
                                val builder = AlertDialog.Builder(this)
                                builder.setMessage("Invalid Employee Id / Password")
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.yellow))
                                }
                                alert.show()
                            } else
                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                        }

                        Status.LOADING -> {
                            showProgressDialog()
                        }

                    }

                }
            }


        } else {

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }

    }



    private fun registrationsuccess(){

        if (Utilities.isNetworkAvailable(this)) {

            val recipientslist = ArrayList<Recipient>()
            recipientslist.add(
                Recipient(
                    "91"+binding.etMobileNo.text.toString(),
                    "VALUE1",
                    "VALUE2"
                )
            )
            viewModel2.successmessage(
                RegistrationSuccessRequest(
               recipientslist,
               shortUrl = "1",
               templateId = "653f5fadd6fc05577c630282")
            ).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()

                            if (resource.data?.type.equals("success")){

                                Toast.makeText(this, "Registration Successfully Done" , Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, SplashActivity::class.java)
                                startActivity(intent)



                            }else{

                                val builder = AlertDialog.Builder(this)
                                builder.setMessage(resource.data?.type)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.yellow))
                                }
                                alert.show()


                            }

                        }

                        Status.ERROR -> {
                            hideProgressDialog()
                            //Log.d(ContentValues.TAG, "print-->" + resource.data?.status)
                            if (it.message!!.contains("401", true)) {
                                val builder = AlertDialog.Builder(this)
                                builder.setMessage("Invalid Employee Id / Password")
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.yellow))
                                }
                                alert.show()
                            } else
                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                        }

                        Status.LOADING -> {
                            showProgressDialog()
                        }

                    }

                }
            }


        } else {

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }
    }



    fun showProgressDialog() {
        binding.rlLoading.visibility = View.VISIBLE
    }

    fun hideProgressDialog() {
        binding.rlLoading.visibility = View.GONE
    }
}