package com.example.deliveryassistant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deliveryassistant.R
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.utils.MyNumberFormat

class ProductAdapter(private val context: Context) :
    RecyclerView.Adapter<ProductViewHolder>() {

    var data = ArrayList<Product>()

    fun setListData(data: ArrayList<Product>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_list_product, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.prodctName.text = data[position].name
        holder.prodctPrice.text = MyNumberFormat.thousandSeparator(data[position].unit_price)
        holder.prodctQuantity.text = data[position].quantity.toString()

        Glide.with(context)
            .load(data[position].img_url)
            .circleCrop()
            .placeholder(R.drawable.circle)
            .into(holder.prodctImg)
    }
}

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val prodctName = view.findViewById(R.id.product_name) as TextView
    val prodctPrice = view.findViewById(R.id.product_price) as TextView
    val prodctQuantity = view.findViewById(R.id.product_quantity) as TextView
    val prodctImg = view.findViewById(R.id.product_img) as ImageView
}
