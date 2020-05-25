package com.news.shared.utils

import android.app.Application

import com.news.shared.components.DaggerNetComponent
import com.news.shared.components.NetComponent
import com.news.shared.di.AppModule
import com.news.shared.di.SharedDIModule
import com.news.shared.di.StorageDIModule

class NewsApplication : Application() {
    var netComponent: NetComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        //DAGGER
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .sharedDIModule(SharedDIModule(this))
                .storageDIModule(StorageDIModule(this))
                .build()
    }

    companion object {
        @get:Synchronized
        var instance: NewsApplication? = null
    }
}