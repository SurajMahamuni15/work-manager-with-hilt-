package com.example.workmanagerdemo.api

import com.example.workmanagerdemo.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET


interface ProductApi {

    @GET("1")
    suspend fun getProduct() : Response<ProductModel>
}