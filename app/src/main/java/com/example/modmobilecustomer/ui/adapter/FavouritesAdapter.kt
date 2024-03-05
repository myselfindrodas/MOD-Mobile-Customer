package com.example.modmobilecustomer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.example.modmobilecustomer.R
import com.example.modmobilecustomer.data.model.stockmodel.Data
import com.squareup.picasso.Picasso

class FavouritesAdapter(val context: Context, var onItemClickListener: FavouritesItemClickListener)
    : RecyclerView.Adapter<FavouritesAdapter.classViewHolder>() {

    private val inflater : LayoutInflater
    var favouriteItems: ArrayList<Data> = ArrayList()

    init {
        inflater = LayoutInflater.from(context)
    }

    interface FavouritesItemClickListener {
        fun favouritesremoveItemOnClick(position: Int, list: ArrayList<Data>, view: View, type:String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouritesAdapter.classViewHolder {
        val view = inflater.inflate(R.layout.favourite_list, parent, false)
        return classViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouritesAdapter.classViewHolder, position: Int) {
        with(holder) {

            Picasso.get()
                .load(favouriteItems[position].originalImage1)
                .error(R.drawable.modmobileph)
                .placeholder(R.drawable.phone_image)
                .into(ivMobileImageInFavourites)



            if (favouriteItems[position].custPrice.isNullOrEmpty()) {
                tvOffInFavourites.text = "0 %"
            } else {
                val pricedifference = favouriteItems[position].mRP.toFloat()
                    .minus(favouriteItems[position].custPrice.toFloat())
                val offpercent = (pricedifference.div(favouriteItems[position].mRP.toFloat()) * 100)
                tvOffInFavourites.text = String.format("%.2f", offpercent) + " %"
            }


            tvMobileNameInFavourites.text = favouriteItems[position].modelName
/*            if (favouriteItems[position].modelName.isNullOrEmpty()) {
                tvMobileNameInFavourites.text = " "
//                favItem.visibility = View.GONE
//                itemView.layoutParams = RecyclerView.LayoutParams(0, 0)

            } else {
//                favItem.visibility = View.VISIBLE
                tvMobileNameInFavourites.text = favouriteItems[position].modelName
            }*/
//            if (favouriteItems[position].ratingNoInFavourites.isNullOrEmpty()) {
//                ratingNoInFavourites.text = " "
//            } else {
//                ratingNoInFavourites.text = favouriteItems[position].ratingNoInFavourites
//            }
            if (favouriteItems[position].stockQty.isNullOrEmpty()) {
                stockInFavourites.text = ""
            } else {
                stockInFavourites.text = favouriteItems[position].stockQty+" In Stock"
            }


            if (favouriteItems[position].stockType.isNullOrEmpty()) {
                tvMobileQualityInFavourites.text = " "
            } else {
                tvMobileQualityInFavourites.text = favouriteItems[position].stockType
            }

            if (favouriteItems[position].memory.isNullOrEmpty()) {
                tvMobileStorageInFavourites.text = " "
            } else {
                tvMobileStorageInFavourites.text = favouriteItems[position].memory
            }
            if (favouriteItems[position].custPrice.isNullOrEmpty()) {
                tvSellingPrice.text = "₹ 0"
            } else {
                tvSellingPrice.text = "₹ "+favouriteItems[position].custPrice
            }

            if (favouriteItems[position].mRP.isNullOrEmpty()) {
                tvMobileRealPriceInFavourites.text = "₹ 0"
            } else {
                tvMobileRealPriceInFavourites.text = "₹ "+favouriteItems[position].mRP
            }


            try {
                tvSavePriceOnMobileInFavourites.text = "Save ₹ "+favouriteItems[position].mRP.toDouble().minus(favouriteItems[position].custPrice.toDouble())


            }catch (e:Exception){

            }


            itemView.setOnClickListener {

                onItemClickListener.favouritesremoveItemOnClick(position, favouriteItems, it, "details")

//                val navController = Navigation.findNavController(it)
//                navController.navigate(R.id.nav_cart)
            }


            rlRemove.setOnClickListener {

                onItemClickListener.favouritesremoveItemOnClick(position, favouriteItems, it, "remove")
            }
        }
    }

    override fun getItemCount(): Int {
        return favouriteItems.size
    }

    fun updateData( mFavouriteItems: List<Data>) {
        favouriteItems = mFavouriteItems as ArrayList<Data>
        notifyDataSetChanged()
    }

    inner class classViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivMobileImageInFavourites: ImageView
        var tvOffInFavourites: TextView
        var tvMobileNameInFavourites: TextView
        var ratingNoInFavourites: TextView
        var stockInFavourites: TextView
        var tvMobileQualityInFavourites: TextView
        var tvMobileStorageInFavourites: TextView
        var tvSellingPrice: TextView
        var tvMobileRealPriceInFavourites: TextView
        var tvSavePriceOnMobileInFavourites: TextView
        var rlAddToCart: RelativeLayout
        var rlRemove: RelativeLayout
        var favItem: CardView

        init {
            ivMobileImageInFavourites = itemView.findViewById(R.id.ivMobileImageInFavourites)
            tvOffInFavourites = itemView.findViewById(R.id.tvOffInFavourites)
            tvMobileNameInFavourites = itemView.findViewById(R.id.tvMobileNameInFavourites)
            ratingNoInFavourites = itemView.findViewById(R.id.ratingNoInFavourites)
            stockInFavourites = itemView.findViewById(R.id.stockInFavourites)
            tvMobileQualityInFavourites = itemView.findViewById(R.id.tvMobileQualityInFavourites)
            tvMobileStorageInFavourites = itemView.findViewById(R.id.tvMobileStorageInFavourites)
            tvSellingPrice = itemView.findViewById(R.id.tvSellingPrice)
            tvMobileRealPriceInFavourites = itemView.findViewById(R.id.tvMobileRealPriceInFavourites)
            tvSavePriceOnMobileInFavourites = itemView.findViewById(R.id.tvSavePriceOnMobileInFavourites)
            rlAddToCart = itemView.findViewById(R.id.rlAddToCart)
            rlRemove = itemView.findViewById(R.id.rlRemove)
            favItem = itemView.findViewById(R.id.favItem)
        }
    }
}