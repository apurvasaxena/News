package com.news.features.newsArticles.di

import com.news.features.newsArticles.view.MainActivity
import com.news.shared.components.NetComponent
import com.news.shared.interfaces.CustomScopeInterface

import dagger.Component

@CustomScopeInterface.CustomScope
@Component(dependencies = [NetComponent::class], modules = [ArticlesDIModule::class])
interface ArticlesComponent {
    fun inject(activity: MainActivity)
}
