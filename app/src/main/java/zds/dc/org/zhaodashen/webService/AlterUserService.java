package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;

/**修改用户信息
 * Created by 2017 on 2017/12/6.
 */

public interface AlterUserService {

    //http://123.207.36.208/user/alterUser?id=11&name=Aaron&gender=男&nickName=Aaron&intro=Hello&qq=2777404

    @GET("user/alterUser?")
    Call<UserModel> AlterUser(@Query("id") String id, @Query("name") String name
    ,@Query("gender") String gender, @Query("nickName") String nickName
    ,@Query("intro") String intro, @Query("qq") String qq);
}
