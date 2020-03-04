package com.jzy.rand.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.jzy.rand.data.Plant
import com.jzy.rand.data.PlantRepository

class PlantListViewModel internal constructor(plantRepository: PlantRepository) :
    ViewModel() {

    private val growZoneNumber = MutableLiveData<Int>(NO_GROW_ZONE)

    val plants: LiveData<List<Plant>> = growZoneNumber.switchMap {
        if (it == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }

}