package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;

/**获取用户信息
 * Created by 2017 on 2017/12/6.
 */

public interface GainUserService {
    @GET("user/gainUser?")
    Call<UserModel> GainUser(@Query("id") String id);
}
