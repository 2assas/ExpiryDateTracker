package com.example.thirdwayv_expirydatatracker.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class ExpiryTrackerViewModel(
    application: Application,
    protected val interactors: Interactors
) :
    AndroidViewModel(application) {
    protected val application: ExpiryDateTrackerApplication = getApplication()
}
