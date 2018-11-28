package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;

/**
 * Created by 2017 on 2017/12/4.
 */

public interface RegisterApiService {
    @GET("user/register?")
    Call<UserModel> Register(@Query("account") String account, @Query("authCode") String password);
}
