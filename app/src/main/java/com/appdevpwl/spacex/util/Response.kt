package com.appdevpwl.spacex.util

import com.appdevpwl.spacex.data.capsules.Capsule

data class Response <out T>(val status: Status, val data:T?, val message:String?){

    enum class Status{
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object{
        fun <T> success(data:T): Response<T> {
            return Response(Status.SUCCESS,data, null)
        }
        fun <T> loading(data:T?): Response<T>{
            return Response(Status.LOADING, data, null)
        }
        fun <T> error(message: String, data: T? = null): Response<T> {
            return Response(Status.ERROR, data, message)
        }
    }
}