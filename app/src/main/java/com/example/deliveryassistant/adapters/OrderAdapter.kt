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
import com.example.deliveryassistant.view.ProductsFragment
import com.google.common.io.Resources
import java.lang.reflect.Array.getInt

class OrderAdapter(private val context: Context, var data: List<Order>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_list_order, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.orderLayout.setPadding(
            when (position) {
                0 -> context.resources.getDimensionPixelOffset(R.dimen.start_padding)
                else -> context.resources.getDimensionPixelOffset(R.dimen.padding)
            }, context.resources.getDimensionPixelOffset(R.dimen.padding), when (position) {
                data.size -> context.resources.getDimensionPixelOffset(R.dimen.padding)
                else -> 0
            }, context.resources.getDimensionPixelOffset(R.dimen.padding)
        )

        holder.orderNumber.text = data[position].id.toString()
        holder.orderName.text = data[position].name
        holder.orderEmail.text = data[position].email
        holder.orderPhoneNumber.text = data[position].phoneNumber
        holder.orderAddress.text = data[position].address

        holder.orderProductButton.setOnClickListener {
            val intent = Intent(context, ProductsFragment::class.java)
            intent.putExtra("id", data[position].id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
        // holder.textQuantite.text = holder.quantite.toString()

        // affichage du nom et l'image par type, smartphone ou pack
        /*if (data[position].getType() == "SmartPhone") {
            holder.icProduit.setImageResource(R.drawable.ic_phone)
            holder.textProduit.text =
                (data[position] as SmartPhone).brand + " " + (data[position] as SmartPhone).model + " - " + (data[position] as SmartPhone).color
        } else {
            holder.icProduit.setImageResource(R.drawable.ic_pack)
            holder.textProduit.text = data[position].name
        }

        // les evenements add et min
        holder.icAdd.setOnClickListener {
            if (holder.quantite != data[position].qte) {
                holder.quantite++
                holder.textQuantite.text = holder.quantite.toString()
            }
        }
        holder.icMin.setOnClickListener {
            if (holder.quantite != 0) {
                holder.quantite--
                holder.textQuantite.text = holder.quantite.toString()
            }
        }

        holder.itemView.setOnClickListener {
            if (data[position].getType() == "SmartPhone") {
                val bundle = bundleOf("smartPhone" to (data[position] as SmartPhone))
                (context as Activity)!!.findNavController(R.id.navFragment)
                    .navigate(R.id.action_productListFragment_to_ProductDetailFragment, bundle)
            }
        }*/
    }

    // separateur de millier
    // fun separateurMillier(num: Long): String = NumberFormat.getInstance(Locale.FRANCE).format(num)
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val orderNumber = view.findViewById(R.id.order_number) as TextView
    val orderName = view.findViewById(R.id.order_name) as TextView
    val orderEmail = view.findViewById(R.id.order_email) as TextView
    val orderPhoneNumber = view.findViewById(R.id.order_phone_number) as TextView
    val orderAddress = view.findViewById(R.id.order_address) as TextView
    val orderLayout = view.findViewById(R.id.order_layout) as ConstraintLayout
    val orderProductButton = view.findViewById(R.id.order_product_button) as ImageView
}
