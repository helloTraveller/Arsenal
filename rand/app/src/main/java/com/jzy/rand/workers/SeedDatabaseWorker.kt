package com.jzy.rand.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.jzy.rand.data.AppDatabase
import com.jzy.rand.data.Plant
import com.jzy.rand.utilities.PLANT_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
   override suspend fun doWork(): Result = coroutineScope {
       try {
           applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
               JsonReader(inputStream.reader()).use { jsonReader ->
                   val plantType = object : TypeToken<List<Plant>>(){}.type
                   val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
                   val database = AppDatabase.getInstance(applicationContext)
                   database.plantDao().insertAll(plantList)
                   Result.success()
               }
           }
       } catch(ex: Exception) {
           Log.e(TAG, "Error seeding database", ex)
           Result.failure()
       }
   }

   companion object {
       private val TAG = SeedDatabaseWorker::class.java.simpleName
   }

}