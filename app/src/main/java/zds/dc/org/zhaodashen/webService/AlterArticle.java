package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.ArticleModel;

/**
 * Created by 2017 on 2017/12/6.
 */

public interface AlterArticle {
    @GET("article/alterArticle?")
    Call<ArticleModel> AlterArticles(@Query("name") String name,@Query("content") String content
    ,@Query("update") String updateTime
    ,@Query("id") String articleId);
}
