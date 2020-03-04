package com.jzy.rand.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jzy.rand.data.GardenPlantingRepository
import com.jzy.rand.data.PlantAndGardenPlantings

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

   val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
       gardenPlantingRepository.getPlantedGardens()

}