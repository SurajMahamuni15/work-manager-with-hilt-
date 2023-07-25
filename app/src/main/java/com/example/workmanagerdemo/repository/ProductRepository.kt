package com.example.workmanagerdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workmanagerdemo.api.ProductApi
import com.example.workmanagerdemo.model.ProductModel
import javax.inject.Inject


class ProductRepository @Inject constructor(private val productApi: ProductApi) {

    private var _productData = MutableLiveData<ProductModel>()
    private val productData: LiveData<ProductModel> get() = _productData

    suspend fun getProduct(): Boolean {
        val result = productApi.getProduct()
        if (result.isSuccessful && result.body() != null) {
            _productData.postValue(result.body())
            return true
        }
        return false
    }
}