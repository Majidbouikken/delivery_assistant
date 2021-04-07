package com.example.deliveryassistant.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deliveryassistant.view.OrderMapActivity
import com.example.deliveryassistant.R
import com.example.deliveryassistant.constant.LAT
import com.example.deliveryassistant.constant.LNG
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.utils.DateParser
import com.example.deliveryassistant.utils.MyNumberFormat
import com.example.deliveryassistant.view.ProductActivity


class OrderAdapter(private val context: Context) :
    RecyclerView.Adapter<OrderViewHolder>() {

    var data = ArrayList<Order>()

    fun setListData(data: ArrayList<Order>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_list_order, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        // to get the right padding
        holder.orderLayout.setPadding(
            when (position) {
                0 -> context.resources.getDimensionPixelOffset(R.dimen.start_padding)
                else -> context.resources.getDimensionPixelOffset(R.dimen.padding)
            }, context.resources.getDimensionPixelOffset(R.dimen.padding), when (position) {
                (data.size - 1) -> context.resources.getDimensionPixelOffset(R.dimen.padding)
                else -> 0
            }, context.resources.getDimensionPixelOffset(R.dimen.padding)
        )

        // setting up data
        holder.orderNumber.text = data[position].id.toString()
        holder.orderName.text = (data[position].first_name + " " + data[position].last_name)
        holder.orderEmail.text = data[position].email
        holder.orderPhoneNumber.text = MyNumberFormat.phoneNumberFormat(data[position].phone_number)
        holder.orderAddress.text = data[position].address
        holder.orderPrice.text =
            MyNumberFormat.thousandSeparator(data[position].total_price.toLong())

        // order status (delayed or delivered)
        if (data[position].status == "delivered") {
            holder.orderStatus.setImageResource(R.drawable.ic_checkmark)
            holder.orderStatus.visibility = View.VISIBLE
        } else if (DateParser.dateToLong(data[position].date) < DateParser.dateToLong(
                DateParser.dateToString(
                    -2
                )
            )
        ) {
            holder.orderStatus.setImageResource(R.drawable.ic_alert)
            holder.orderStatus.visibility = View.VISIBLE
        } else {
            holder.orderStatus.visibility = View.INVISIBLE
        }


        // the image
        Glide.with(context)
            .load(data[position].avatar_url)
            .circleCrop()
            .placeholder(R.drawable.circle)
            .into(holder.orderAvatar)

        // show products listener
        holder.orderProductButton.setOnClickListener {
            val productsIntent = Intent(context, ProductActivity::class.java)
            productsIntent.putExtra("order_id", data[position].id.toString())
            productsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(productsIntent)
        }

        // show one map listener
        holder.orderMapButton.setOnClickListener {
            val mapIntent = Intent(context, OrderMapActivity::class.java)
            mapIntent.putExtra(LAT, data[position].lat.toString())
            mapIntent.putExtra(LNG, data[position].lng.toString())
            mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(mapIntent)
        }
    }
}

class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val orderNumber = view.findViewById(R.id.order_number) as TextView
    val orderName = view.findViewById(R.id.order_name) as TextView
    val orderEmail = view.findViewById(R.id.order_email) as TextView
    val orderPhoneNumber = view.findViewById(R.id.order_phone_number) as TextView
    val orderAddress = view.findViewById(R.id.order_address) as TextView
    val orderPrice = view.findViewById(R.id.order_price) as TextView
    val orderLayout = view.findViewById(R.id.order_layout) as ConstraintLayout
    val orderProductButton = view.findViewById(R.id.order_product_button) as ImageView
    val orderMapButton = view.findViewById(R.id.order_map_button) as ImageView
    val orderAvatar = view.findViewById(R.id.order_avatar) as ImageView
    val orderStatus = view.findViewById(R.id.order_ic_status) as ImageView
}
