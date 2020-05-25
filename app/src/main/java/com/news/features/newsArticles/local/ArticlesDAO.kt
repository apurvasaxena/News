package com.news.features.newsArticles.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.news.features.newsArticles.model.Articles

@Dao
interface ArticlesDAO {

    @Query("SELECT * FROM Articles")
    fun getArticlesList(): LiveData<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticles(articleslist: List<Articles>)

    @Query("DELETE FROM Articles")
    suspend fun deleteArticlesList()
}
