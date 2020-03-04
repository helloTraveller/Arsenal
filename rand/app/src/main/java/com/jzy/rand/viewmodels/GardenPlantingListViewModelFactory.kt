package com.jzy.rand.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jzy.rand.data.GardenPlantingRepository

class GardenPlantingListViewModelFactory(private val repository: GardenPlantingRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GardenPlantingListViewModel(repository) as T
    }

}