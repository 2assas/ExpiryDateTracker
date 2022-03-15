package com.example.thirdwayv_expirydatatracker.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ExpiryTrackerViewModelFactory : ViewModelProvider.Factory {

    lateinit var application: Application

    lateinit var dependencies: Interactors

    fun inject(application: Application, dependencies: Interactors) {
        ExpiryTrackerViewModelFactory.application = application
        ExpiryTrackerViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (ExpiryTrackerViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(application, dependencies)
        } else {
            throw IllegalStateException("ViewModel must extend ExpiryTrackerViewModel")
        }
    }
}
