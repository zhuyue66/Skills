package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;

/**
 * Created by 2017 on 2017/12/4.
 */

public interface AlterPWApiService {
    @GET("user/alterPassword?")
    Call<UserModel> AlterPW(@Query("account") String account, @Query("password") String password);
}
