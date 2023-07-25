package com.example.workmanagerdemo.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmanagerdemo.MainActivity
import com.example.workmanagerdemo.MainApplication
import com.example.workmanagerdemo.api.ProductApi
import com.example.workmanagerdemo.repository.ProductRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltWorker
class ProductWorkerManager @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    @Inject
    lateinit var repository: ProductRepository
    override fun doWork(): Result {
        repository = (context as MainApplication).productRepository
        CoroutineScope(Dispatchers.IO).launch {
//            repository.getProduct()
            Log.e("ProductWorkerManager", repository.getProduct().toString())
        }

        return Result.success()
    }
}