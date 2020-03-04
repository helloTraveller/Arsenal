package com.jzy.rand.utilities

import android.content.Context
import com.jzy.rand.data.AppDatabase
import com.jzy.rand.data.GardenPlantingRepository
import com.jzy.rand.data.PlantRepository
import com.jzy.rand.viewmodels.GardenPlantingListViewModelFactory
import com.jzy.rand.viewmodels.PlantDetailViewModelFactory
import com.jzy.rand.viewmodels.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context.applicationContext).gardenPlantingDao())
    }

    fun provideGardenPlantingListViewModelFactory(context: Context): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(
            getPlantRepository(context),
            getGardenPlantingRepository(context),
            plantId
        )
    }

}