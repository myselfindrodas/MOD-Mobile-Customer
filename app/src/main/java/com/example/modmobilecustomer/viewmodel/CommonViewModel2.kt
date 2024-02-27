package com.example.modmobilecustomer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.modmobilecustomer.data.Resource
import com.example.modmobilecustomer.data.model.addtocartmodel.AddtoCartRequest
import com.example.modmobilecustomer.data.model.couponmastermodel.CouponRequest
import com.example.modmobilecustomer.data.model.dashboardmodel.DashboardRequest
import com.example.modmobilecustomer.data.model.defaultaddressmodel.DefaultAddressRequest
import com.example.modmobilecustomer.data.model.favouritemodel.FavAddRemoveRequest
import com.example.modmobilecustomer.data.model.getfavouritemodel.FavListRequest
import com.example.modmobilecustomer.data.model.getimagemodel.GetProductImageRequest
import com.example.modmobilecustomer.data.model.invoicecancelmodel.InvoiceCancelRequest
import com.example.modmobilecustomer.data.model.invoicecreatemodel.InvoicecreateRequest
import com.example.modmobilecustomer.data.model.login_model.LoginRequestModel
import com.example.modmobilecustomer.data.model.manageaddressmodel.ManageAddressRequest
import com.example.modmobilecustomer.data.model.myoderlistmodel.OrderListRequest
import com.example.modmobilecustomer.data.model.orderlistdetailsmodel.OrderRequest
import com.example.modmobilecustomer.data.model.otpvalidationmodel.OtpValidationRequest
import com.example.modmobilecustomer.data.model.pincodemodel.PincodeRequest
import com.example.modmobilecustomer.data.model.pocreatemodel.POcreateRequest
import com.example.modmobilecustomer.data.model.qclistmodel.QCRequest
import com.example.modmobilecustomer.data.model.registrationsuccessmodel.RegistrationSuccessRequest
import com.example.modmobilecustomer.data.model.sentotpmodel.SentOtpRequest
import com.example.modmobilecustomer.data.model.signupmodel.SignupRequest
import com.example.modmobilecustomer.data.model.statemodel.StateListRequest
import com.example.modmobilecustomer.data.model.stockmodel.StockRequest
import com.example.modmobilecustomer.data.model.storagecolormodel.StorageColorRequest
import com.example.modmobilecustomer.data.model.tokenmodel.TokenRequest
import com.example.modmobilecustomer.data.model.viewaddressmodel.ViewAddressRequest
import com.example.modmobilecustomer.data.model.viewcartmodel.ViewCartRequest
import com.example.modmobilecustomer.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class CommonViewModel2(private val mainRepository: MainRepository) : ViewModel() {


    fun successmessage(requestBody: RegistrationSuccessRequest) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))

            try {
                emit(Resource.success(data = mainRepository.successmessage(requestBody)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }








    fun sentotp(template_id:String, mobile: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))

            try {
                emit(Resource.success(data = mainRepository.sentotp(template_id, mobile)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }



    fun otpvalidate(otp:String, mobile: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))

            try {
                emit(Resource.success(data = mainRepository.otpvalidate(otp, mobile)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }




    fun resendotp(retrytype:String, mobile: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))

            try {
                emit(Resource.success(data = mainRepository.resendotp(retrytype, mobile)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }

//    fun validateOTP(token: RequestBody, otpJSON: RequestBody) =
//        liveData(Dispatchers.IO) {
//            emit(Resource.loading(data = null))
//
//            try {
//                emit(Resource.success(data = mainRepository.validateOTP(token, otpJSON)))
//            } catch (e: Exception) {
//                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
//            }
//        }


}