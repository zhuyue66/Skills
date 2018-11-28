package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.OrderModel;

/**使用前请先确保OrderModel已经填入相关字段
 * Created by 2017 on 2017/12/6.
 */

public interface SendOrdersService {
    @GET("user/sendOrders?")
    Call<OrderModel> SendOrders(@Query("id") String userId);
}
