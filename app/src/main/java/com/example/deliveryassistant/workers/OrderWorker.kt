package com.example.deliveryassistant.workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.utils.ConnectivityStatus
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.SettableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val key1 = "barcode"

class OrderWorker(ctx: Context, workParameters: WorkerParameters) :
    ListenableWorker(ctx, workParameters) {
    lateinit var future: SettableFuture<Result>

    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create<Result>()
        val barcode = inputData.getString(key1)
        if (ConnectivityStatus.isOnline()) if (!barcode.isNullOrBlank()) lateUpdateOrder(
            Order(
                0,
                "",
                "",
                "",
                "",
                "",
                barcode,
                "",
                0,
                "",
                "",
                0.0,
                0.0
            )
        )
        return future
    }

    fun lateUpdateOrder(order: Order) {
        val call = RetrofitService.endpoint.updateOrder(order)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>?, response:
                Response<String>?
            ) {
                if (response != null) if (response.isSuccessful) {
                    future.set(Result.success())
                } else {
                    future.set(Result.retry())
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
            }
        })
    }
}