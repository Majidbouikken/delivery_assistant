package com.example.deliveryassistant.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryassistant.R
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.view.ProductsFragment
import com.google.common.io.Resources
import java.lang.reflect.Array.getInt

class ProductAdapter(private val context: Context, var data: List<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_list_product, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.prodctName.text = data[position].name
        holder.prodctPrice.text = data[position].unitPrice.toString()
    }
}

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val prodctName = view.findViewById(R.id.product_name) as TextView
    val prodctPrice = view.findViewById(R.id.product_price) as TextView
}