package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;

/**
 * Created by Aaron on 2018/4/3.
 */

public interface AlterPortrait {
    @GET("/user/alterPortrait?")
    Call<UserModel> AlterPortrait(@Query("id") String id, @Query("alterPortrait") String portraitUrl);
}
