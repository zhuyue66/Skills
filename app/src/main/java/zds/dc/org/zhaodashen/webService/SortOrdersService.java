package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import zds.dc.org.zhaodashen.model.bean.HomePageBean;

/**
 * @author zhuyue66
 * @date 2018/3/6
 */

public interface SortOrdersService {
    @GET("order/sortOrders")
    Call<HomePageBean.HPOrderModel> SortOrders();
}
