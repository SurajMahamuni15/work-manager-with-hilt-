package com.example.workmanagerdemo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.workmanagerdemo.api.ProductApi
import com.example.workmanagerdemo.repository.ProductRepository
import com.example.workmanagerdemo.worker.ProductWorkerManager
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

//, Configuration.Provider

@HiltAndroidApp
class MainApplication : Application() {
    lateinit var productRepository: ProductRepository
    override fun onCreate() {
        super.onCreate()
        initializeRepo()
        setupWorker()
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest =
            PeriodicWorkRequest.Builder(ProductWorkerManager::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }


    private fun initializeRepo() {
        val apiService = NetworkModule().getRetrofitApi()
        productRepository = ProductRepository(apiService)
    }

//    @Inject
//    lateinit var workFactory: CustomWorkFactory
//
//    override fun getWorkManagerConfiguration() =
//        Configuration.Builder()
//            .setMinimumLoggingLevel(Log.DEBUG)
//            .setWorkerFactory(workFactory)
//            .build()

//
//    class CustomWorkFactory @Inject constructor(private val productApi: ProductApi) :
//        WorkerFactory() {
//        override fun createWorker(
//            appContext: Context,
//            workerClassName: String,
//            workerParameters: WorkerParameters
//        ): ListenableWorker = ProductWorkerManager(productApi,appContext, workerParameters)
//
//    }
}