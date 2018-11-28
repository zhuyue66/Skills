package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.OrderModel;
import zds.dc.org.zhaodashen.model.SkillModel;

/**
 * Created by Aaron on 2018/3/5.
 */

public interface SkillPublishService {
    @GET("skill/postSkill?")
    Call<SkillModel> skillPublish(@Query("name") String name, @Query("object") String srvObject
            , @Query("serveStyle") String skillWays
            , @Query("serveTime") String skillTime
            , @Query("releaser") String poster
            , @Query("postDate") String postTime
            , @Query("details") String detail
            , @Query("firstCategory") String firstC
            , @Query("secondCategory") String price);
}
