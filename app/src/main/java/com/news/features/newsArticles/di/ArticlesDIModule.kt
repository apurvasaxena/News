package com.news.features.newsArticles.di

import com.news.features.newsArticles.local.ArticlesRepository
import com.news.features.newsArticles.remote.ArticlesDataImpl
import com.news.features.newsArticles.remote.ArticlesDataInterface
import com.news.features.newsArticles.viewModel.ArticlesViewModelFactory
import com.news.shared.api.ApiFailure
import com.news.shared.api.ApiService

import dagger.Module
import dagger.Provides

@Module
class ArticlesDIModule {

    @Provides
    fun provideAllNewsDataInterface(apiService: ApiService, apiFailure: ApiFailure): ArticlesDataInterface {
        return ArticlesDataImpl(apiService, apiFailure)
    }

    @Provides
    fun provideAllProgrammesViewModelFactory(data: ArticlesDataInterface,
                                             articlesrepo: ArticlesRepository): ArticlesViewModelFactory {
        return ArticlesViewModelFactory(data, articlesrepo)
    }
}