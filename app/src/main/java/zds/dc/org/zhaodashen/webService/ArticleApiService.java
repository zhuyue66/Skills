package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.ArticleModel;
import zds.dc.org.zhaodashen.model.SkillModel;

/**
 * Created by Aaron on 2018/3/5.
 */

public interface ArticleApiService {
    @GET("article/postArticle?")
    Call<ArticleModel> articlePublish(@Query("name") String name
            , @Query("topic") String srvObject
            , @Query("content") String content
            , @Query("author") String author
            , @Query("upload") String upload);
}
