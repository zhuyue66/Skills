package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.ArticleModel;

/**
 * Created by 2017 on 2017/12/6.
 */

public interface RemoveArtService {
    @GET("article/removeArticle?")
    Call<ArticleModel> RemoveArticle(@Query("articleId") String articleId);
}
