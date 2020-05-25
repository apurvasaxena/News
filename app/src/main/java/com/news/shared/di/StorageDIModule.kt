package com.news.shared.di

import android.content.Context
import androidx.room.Room
import com.news.features.newsArticles.local.ArticlesRepository
import com.news.shared.persistense.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageDIModule(internal var ctx: Context) {

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): MyDatabase {
        return Room.databaseBuilder(ctx!!, MyDatabase::class.java, ctx.packageName)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun providesArticlesRepository(mydatabase: MyDatabase): ArticlesRepository {
        return ArticlesRepository(mydatabase.articlesDAO())
    }
}
