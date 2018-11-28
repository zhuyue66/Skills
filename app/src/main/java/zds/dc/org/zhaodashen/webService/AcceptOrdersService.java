package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.OrderModel;

/**
 * Created by 2017 on 2017/12/6.
 */

public interface AcceptOrdersService {
    @GET("user/acceptOrders?")
    Call<OrderModel> AcceptOrders(@Query("id") String userId);
}
