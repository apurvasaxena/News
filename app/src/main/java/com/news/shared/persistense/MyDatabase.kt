package com.news.shared.persistense


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.news.BuildConfig
import com.news.features.newsArticles.local.ArticlesDAO
import com.news.features.newsArticles.model.Articles
import com.news.features.newsArticles.model.SourceConverter

@Database(entities = [Articles::class], version = BuildConfig.VERSION_CODE, exportSchema = true)
@TypeConverters(SourceConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun articlesDAO(): ArticlesDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}