package com.news.shared.api

import com.google.gson.Gson
import com.news.shared.interfaces.DataInterface
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

class ApiFailure {
    fun onApiFailure(callback: DataInterface.Callback<*>, e: Throwable) {
        if (e is HttpException) {
            val responseBody = e.response()!!.errorBody()
            var errormodel: ErrorResponse? = null
            try {
                val json = responseBody!!.string()
                errormodel = Gson().fromJson(json, ErrorResponse::class.java)
            } catch (e1: IOException) {
                e1.printStackTrace()
                callback.onFailure()
            }
                if (errormodel?.message != null) {
                    errormodel!!.message?.let { callback.onFailureWithMessage(it) }
                } else {
                    callback.onFailure()
                }
        } else if (e is ConnectException || e is UnknownHostException) {
            callback.onNoNetworkFailure()
        } else {
            callback.onFailure()
        }
    }
}