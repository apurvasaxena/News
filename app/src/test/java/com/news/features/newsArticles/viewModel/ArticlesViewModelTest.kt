package com.news.features.newsArticles.viewModel


import TestUtil
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.news.features.MockitoHelper
import com.news.features.newsArticles.local.ArticlesRepository
import com.news.features.newsArticles.model.Articles
import com.news.features.newsArticles.model.ArticlesListResponse
import com.news.features.newsArticles.remote.ArticlesDataInterface
import com.news.shared.interfaces.DataInterface
import com.news.shared.model.Response
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ArticlesViewModelTest {
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var responseLiveData: LiveData<Response>
    private val EXCEPTION_ERROR_MESSAGE = "Sorry, something went wrong"
    private val NETWORKING_ERROR_MESSAGE = "No internet connection at the moment"
    private val FOUR_HUNDRED_ERROR_MESSAGE = "Invalid request"

    @Spy
    private val articlesListLiveData: MutableLiveData<List<Articles>> = MutableLiveData()
    @Mock
    lateinit var  articledata:ArticlesDataInterface
    @Mock
    private lateinit var repo: ArticlesRepository
    @Mock
    lateinit var articleResponse: ArticlesListResponse
    @Captor
    lateinit var captor: ArgumentCaptor<DataInterface.Callback<ArticlesListResponse>>


    @Rule
    @JvmField
    public var instantExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField
    public  val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
//        DispatchersProvider.Default = Dispatchers.Unconfined
        `when`(articleResponse.articles).thenReturn(TestUtil.articlesList)
        `when`(repo.articleslist).thenReturn(articlesListLiveData)
        viewModel = ArticlesViewModel(articledata,repo)
        responseLiveData = viewModel.getapiresponse()
    }

    //SCENARIO -- SUCCESSFULLY FETCHING ARTICLES
    @Test
     fun `when successfully got News Then Check Observe should not null`(){
        //WHEN
        viewModel.getArticles()
        //AND
        succesfullyFetchDataFromBackend()
        //THEN
        assertEquals(articleResponse.articles.size, 1)

        }

    private fun succesfullyFetchDataFromBackend(){
        verify(articledata).getNews(ArgumentMatchers.anyString(), MockitoHelper.capture(captor))
        val callback = captor.value
        callback.onResponse(TestUtil.articlesListResponse)
    }

    //SCENARIO -- 400 ERROR WHILE FETCHING ARTICLES
    @Test
    fun `when four Hundred Error While Fetching Articles then display Error Message In View`(){
        //WHEN
        viewModel.getArticles()
        //AND
        fourHundredErrorWhileChangePin()
        //THEN
        var errorvalue = responseLiveData.value
        assertNotNull(errorvalue)
        errorvalue.let {
        assertEquals(responseLiveData.value!!.error,FOUR_HUNDRED_ERROR_MESSAGE)
        }
    }

    private fun fourHundredErrorWhileChangePin() {
        verify(articledata).getNews(ArgumentMatchers.anyString(), MockitoHelper.capture(captor))
        val callback = captor.value
        callback.onFailureWithMessage(FOUR_HUNDRED_ERROR_MESSAGE)
    }

    //SCENARIO -- EXCEPTION ERROR WHILE FETCHING ARTICLES
    @Test
    fun `when exception Error while fetching Articles then display Error Message In View`() {
        //WHEN
        viewModel.getArticles()
        //AND
        exceptionErrorWhileFetchingArticles()
        //THEN
        var errorvalue = responseLiveData.value
        assertNotNull(errorvalue)
        errorvalue.let {
            assertEquals(responseLiveData.value!!.error, EXCEPTION_ERROR_MESSAGE)
        }
        }

    private fun exceptionErrorWhileFetchingArticles() {
        verify(articledata).getNews(ArgumentMatchers.anyString(), MockitoHelper.capture(captor))
        val callback = captor.value
        callback.onFailure()
    }

    //SCENARIO -- NETWORKING ERROR WHILE FETCHING ARTICLES
    @Test
    fun `when network Error while fetching Articles then display Error Message In View`() {
        //WHEN
        viewModel.getArticles()
        //AND
        networkErrorWhileFetchingArticles()
        //THEN
        var errorvalue = responseLiveData.value
        assertNotNull(errorvalue)
        errorvalue.let {
            assertEquals(responseLiveData.value!!.error, NETWORKING_ERROR_MESSAGE)
        }  }

    private fun networkErrorWhileFetchingArticles() {
        verify(articledata).getNews(ArgumentMatchers.anyString(), MockitoHelper.capture(captor))
        val callback = captor.value
        callback.onNoNetworkFailure()
    }

    //SCENARIO -- FETCHING ARTICLES ONLY WHEN NO LOCAL DATA
    @Test
    fun `call backend webservice to get articles if not locally present`() = runBlocking {
        //WHEN
        articlesListLiveData.value = listOf()
        //AND
        viewModel.getarticlesresponse().observeForever { }
        //AND
        viewModel.fetchArticlesFromLocalOrRemote()
        //THEN
        verify(articledata).getNews(ArgumentMatchers.anyString(), MockitoHelper.capture(captor))
        return@runBlocking
    }

    //SCENARIO -- NEVER FETCH ARTICLES WHEN LOCAL DATA ALREADY PRESENT
    @Test
    fun `retrieves local articles without calling webservice from backend`() = runBlocking {
        //WHEN
        articlesListLiveData.value = TestUtil.articlesList
        //AND
        viewModel.getarticlesresponse().observeForever { }
        //AND
        viewModel.fetchArticlesFromLocalOrRemote()
        //THEN
        verify(articledata, never()).getNews(ArgumentMatchers.anyString(), MockitoHelper.capture(captor))
        return@runBlocking
    }

}