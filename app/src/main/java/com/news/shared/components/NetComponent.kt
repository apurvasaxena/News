package com.news.shared.components


import com.news.features.newsArticles.local.ArticlesRepository
import com.news.shared.api.ApiFailure
import com.news.shared.api.ApiService
import com.news.shared.di.AppModule
import com.news.shared.di.SharedDIModule
import com.news.shared.di.StorageDIModule
import com.news.shared.persistense.MyDatabase

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [AppModule::class, SharedDIModule::class, StorageDIModule::class])
interface NetComponent {
    fun apiService(): ApiService
    fun apiFailure(): ApiFailure
    fun myDatabase(): MyDatabase

    fun articlesRepository(): ArticlesRepository

}
