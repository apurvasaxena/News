import com.news.features.newsArticles.model.Articles
import com.news.features.newsArticles.model.ArticlesListResponse
import com.news.features.newsArticles.model.Source

object TestUtil {
    val articlesList: List<Articles>
        get() {
            return listOf(Articles(1, "Satoshi Nakaboto", "Bitcoin price", "Bitcoin price description", "2020-05-22T09:30:00Z", Source("1", "uk"), "Satoshi Nakaboto: ‘Bitcoin whale", "https://thenextweb.com/hardfork/2020/05/22/satoshi-nakaboto-bitcoin-whale-moves-400m-pays-2-50-fee/", "https://bitcoinist.com/wp-content/uploads/2020/05/shutterstock_577141831-1920x1440.jpg"))
        }

    val articlesListResponse: ArticlesListResponse
        get() {
            return ArticlesListResponse(articlesList, "ok",1)
        }
}
