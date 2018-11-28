package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;

/**
 * Created by 2017 on 2017/12/6.
 */

public interface CancelCollectUsService {
    @GET("user/cancelCollectUser?")
    Call<UserModel> CancelCollectUser(@Query("user") String userId,@Query("expert") String expertId);
}
