package com.demirli.a53thisorthatgame

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.demirli.a53thisorthatgame.model.Image

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MainRepository by lazy { MainRepository(application)}

    fun getAllImages() = repository.getAllImages()

    fun addImage(image: Image) = repository.addImage(image)



}