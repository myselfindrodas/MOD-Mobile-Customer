package com.example.modmobilecustomer.data

import com.example.modmobilecustomer.data.model.addtocartmodel.AddtoCartRequest
import com.example.modmobilecustomer.data.model.addtocartmodel.AddtocartResponse
import com.example.modmobilecustomer.data.model.couponmastermodel.CouponRequest
import com.example.modmobilecustomer.data.model.couponmastermodel.CouponlistResponse
import com.example.modmobilecustomer.data.model.dashboardmodel.DashboardRequest
import com.example.modmobilecustomer.data.model.dashboardmodel.DashboardResponse
import com.example.modmobilecustomer.data.model.defaultaddressmodel.DefaultAddressRequest
import com.example.modmobilecustomer.data.model.defaultaddressmodel.DefaultAddressResponse
import com.example.modmobilecustomer.data.model.favouritemodel.FavAddRemoveRequest
import com.example.modmobilecustomer.data.model.favouritemodel.FavouriteAddRemoveResponse
import com.example.modmobilecustomer.data.model.getfavouritemodel.FavListRequest
import com.example.modmobilecustomer.data.model.getfavouritemodel.FavListResponse
import com.example.modmobilecustomer.data.model.getimagemodel.GetProductImageRequest
import com.example.modmobilecustomer.data.model.getimagemodel.GetProductImageResponse
import com.example.modmobilecustomer.data.model.invoicecancelmodel.InvoiceCancelRequest
import com.example.modmobilecustomer.data.model.invoicecancelmodel.InvoiceCancelledResponse
import com.example.modmobilecustomer.data.model.invoicecreatemodel.InvoicecreateRequest
import com.example.modmobilecustomer.data.model.invoicecreatemodel.InvoicecreateResponse
import com.example.modmobilecustomer.data.model.login_model.LoginRequestModel
import com.example.modmobilecustomer.data.model.login_model.LoginResponseModel
import com.example.modmobilecustomer.data.model.manageaddressmodel.ManageAddressRequest
import com.example.modmobilecustomer.data.model.manageaddressmodel.ManageAddressResponse
import com.example.modmobilecustomer.data.model.myoderlistmodel.OrderListRequest
import com.example.modmobilecustomer.data.model.myoderlistmodel.OrderListResponse
import com.example.modmobilecustomer.data.model.orderlistdetailsmodel.OrderRequest
import com.example.modmobilecustomer.data.model.orderlistdetailsmodel.OrderResponse
import com.example.modmobilecustomer.data.model.otpsentmodel.SentOTPResponse
import com.example.modmobilecustomer.data.model.otpvalidatemodel.OTPverifiedResponse
import com.example.modmobilecustomer.data.model.otpvalidationmodel.OtpValidateResponse
import com.example.modmobilecustomer.data.model.otpvalidationmodel.OtpValidationRequest
import com.example.modmobilecustomer.data.model.pincodemodel.PincodeRequest
import com.example.modmobilecustomer.data.model.pincodemodel.PincodeResponse
import com.example.modmobilecustomer.data.model.pocreatemodel.POcreateRequest
import com.example.modmobilecustomer.data.model.pocreatemodel.POcreateResponse
import com.example.modmobilecustomer.data.model.postreviewmodel.PostReviewRequest
import com.example.modmobilecustomer.data.model.postreviewmodel.PostreviewResponse
import com.example.modmobilecustomer.data.model.qclistmodel.QCRequest
import com.example.modmobilecustomer.data.model.qclistmodel.QClistResponse
import com.example.modmobilecustomer.data.model.registrationsuccessmodel.RegistrationSuccessRequest
import com.example.modmobilecustomer.data.model.registrationsuccessmodel.RegistrationSuccessResponse
import com.example.modmobilecustomer.data.model.reviewlistmodel.ReviewListResponse
import com.example.modmobilecustomer.data.model.reviewlistmodel.ReviewlistRequest
import com.example.modmobilecustomer.data.model.sentotpmodel.SentOtpRequest
import com.example.modmobilecustomer.data.model.sentotpmodel.SentOtpResponse
import com.example.modmobilecustomer.data.model.signupmodel.SignupRequest
import com.example.modmobilecustomer.data.model.signupmodel.SignupResponse
import com.example.modmobilecustomer.data.model.statemodel.StateListRequest
import com.example.modmobilecustomer.data.model.statemodel.StateResponse
import com.example.modmobilecustomer.data.model.stockmodel.StockRequest
import com.example.modmobilecustomer.data.model.stockmodel.StockResponse
import com.example.modmobilecustomer.data.model.storagecolormodel.StorageColorRequest
import com.example.modmobilecustomer.data.model.storagecolormodel.StorageColorResponse
import com.example.modmobilecustomer.data.model.tokenmodel.TokenRequest
import com.example.modmobilecustomer.data.model.tokenmodel.TokenResponse
import com.example.modmobilecustomer.data.model.viewaddressmodel.ViewAddressRequest
import com.example.modmobilecustomer.data.model.viewaddressmodel.ViewAddressResponse
import com.example.modmobilecustomer.data.model.viewcartmodel.ViewCartRequest
import com.example.modmobilecustomer.data.model.viewcartmodel.ViewcartResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {


    @POST("generateToken.php")
    suspend fun generatetoken(
        @Body requestBody: TokenRequest
    ):TokenResponse



    @POST("userRegistration_send_otp.php")
    suspend fun sentotp(
        @Body requestBody: SentOtpRequest
    ): SentOtpResponse



//    @POST("otp_validateRegistration.php")
//    suspend fun otpvalidate(
//        @Body requestBody: OtpValidationRequest
//    ): OtpValidateResponse



    @POST("login.php")
    suspend fun login(
        @Body requestBody: LoginRequestModel
    ): LoginResponseModel



    @POST("customer_register.php")
    suspend fun signup(
        @Body requestBody: SignupRequest
    ): SignupResponse



    @POST("dashboard.php")
    suspend fun dashboard(
        @Body requestBody: DashboardRequest
    ): DashboardResponse




    @POST("model_master_color_memory.php")
    suspend fun getcolorstorage(
        @Body requestBody: StorageColorRequest
    ): StorageColorResponse





    @POST("getStock_IMEI.php")
    suspend fun getstock(
        @Body requestBody: StockRequest
    ): StockResponse




    @POST("getorder_list_invoice_item_wise.php")
    suspend fun orderlist(
        @Body requestBody: OrderListRequest
    ): OrderListResponse



    @POST("check_pincode.php")
    suspend fun checkpincode(
        @Body requestBody: PincodeRequest
    ): PincodeResponse




    @POST("get_image.php")
    suspend fun getproductimage(
        @Body requestBody: GetProductImageRequest
    ): GetProductImageResponse



    @Multipart
    @POST("otp_validateRegistration.php")
    suspend fun validateOTP(
        @Part("token") token: RequestBody?,
        @Part("otpJSON") otpJSON: RequestBody?
    ): LoginResponseModel






    @POST("state_master.php")
    suspend fun statemaster(
        @Body requestBody: StateListRequest
    ): StateResponse




    @POST("order_list.php")
    suspend fun orderdetails(
        @Body requestBody: OrderRequest
    ): OrderResponse





    @POST("view_address.php")
    suspend fun viewaddress(
        @Body requestBody: ViewAddressRequest
    ): ViewAddressResponse





    @POST("manage_address.php")
    suspend fun manageaddress(
        @Body requestBody: ManageAddressRequest
    ): ManageAddressResponse





    @POST("setdefault_address.php")
    suspend fun defaultaddress(
        @Body requestBody: DefaultAddressRequest
    ): DefaultAddressResponse






    @POST("getfavouriteitems.php")
    suspend fun getfavouriteitems(
        @Body requestBody: FavListRequest
    ): StockResponse






    @POST("webgetRateReview.php")
    suspend fun getreview(
        @Body requestBody: ReviewlistRequest
    ): ReviewListResponse




    @POST("WebpostRateReview.php")
    suspend fun postreview(
        @Body requestBody: PostReviewRequest
    ): PostreviewResponse




    @POST("addfavouriteitems.php")
    suspend fun addfav(
        @Body requestBody: FavAddRemoveRequest
    ): FavouriteAddRemoveResponse




    @POST("add_to_cart.php")
    suspend fun addtocart(
        @Body requestBody: AddtoCartRequest
    ): AddtocartResponse





    @POST("POCreate.php")
    suspend fun pocreate(
        @Body requestBody: POcreateRequest
    ): POcreateResponse





    @POST("Invoice_creation.php")
    suspend fun invoicecreate(
        @Body requestBody: InvoicecreateRequest
    ): InvoicecreateResponse





    @POST("Invoice_cancel.php")
    suspend fun invoicecancel(
        @Body requestBody: InvoiceCancelRequest
    ): InvoiceCancelledResponse




    @POST("view_cart.php")
    suspend fun viewcart(
        @Body requestBody: ViewCartRequest
    ): ViewcartResponse





    @POST("coupon_master.php")
    suspend fun couponmaster(
        @Body requestBody: CouponRequest
    ): CouponlistResponse




    @POST("flow/")
    suspend fun successmessage(
        @Body requestBody: RegistrationSuccessRequest
    ): RegistrationSuccessResponse



    @POST("otp")
    suspend fun sentotp(
        @Query("template_id") template_id:String,
        @Query("mobile") mobile:String
    ): SentOTPResponse




    @POST("get_qc_deatils.php")
    suspend fun QClist(
        @Body requestBody: QCRequest
    ): QClistResponse




    @POST("otp/verify")
    suspend fun otpvalidate(
        @Query("otp") otp:String,
        @Query("mobile") mobile:String
    ): OTPverifiedResponse




    @POST("otp/retry")
    suspend fun resendotp(
        @Query("retrytype") retrytype:String,
        @Query("mobile") mobile:String
    ): OTPverifiedResponse

}