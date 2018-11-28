package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.ArticleModel;
import zds.dc.org.zhaodashen.model.OrderModel;

/**
 * Created by Aaron on 2018/3/4.
 */

public interface DemandPublish {
    @GET("order/postOrder?")
    Call<OrderModel> demandPublish(@Query("name") String name,@Query("details") String details
            ,@Query("price") String price
            ,@Query("serve") String serveways
            ,@Query("firstCategory") String firstCategory
            ,@Query("secondCategory") String secondCategory
            ,@Query("releaseDate") String startDate
            ,@Query("appointDate") String endDate
            ,@Query("releaser") String releaser);
}
