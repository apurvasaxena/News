package com.news.features.newsArticles

import LiveDataTestUtil
import TestUtil
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.news.features.newsArticles.local.ArticlesDAO
import com.news.shared.persistense.MyDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.io.IOException

class ArticlesRepositoryTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var articlesDao: ArticlesDAO
    private lateinit var db: MyDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, MyDatabase::class.java).build()
        articlesDao = db.articlesDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_retrieved_articles_equal_to_inseted_articles() = runBlocking {
        var articleslist = TestUtil.articlesList
        // Given Articles have been inserted into the DB
        articlesDao.saveArticles(articleslist)
        // When getting the Articles via the DAO
        val articlesFromDb = LiveDataTestUtil.getValue(articlesDao.getArticlesList())
        // Then the retrieved Articles matches the original article object
        assertEquals(articleslist, articlesFromDb)
    }

    @Test
     fun should_Flush_All_Data() = runBlocking {
        //when articles deleted from DAO
        articlesDao.deleteArticlesList()
        // Then article list size should be 0 from DAO
        Assert.assertEquals(LiveDataTestUtil.getValue(articlesDao.getArticlesList()).size, 0)
    }

}
