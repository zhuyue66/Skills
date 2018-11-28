package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.ArticleModel;
import zds.dc.org.zhaodashen.model.UserModel;

/**
 * Created by 2017 on 2017/12/6.
 */

public interface PostArtService {
    @GET("article/postArticle?")
    Call<ArticleModel> PostArticle(@Query("name") String articleName, @Query("topic") String topic
    ,@Query("content") String content, @Query("author") String authorId
            ,@Query("upload") String uploadTime);
}
