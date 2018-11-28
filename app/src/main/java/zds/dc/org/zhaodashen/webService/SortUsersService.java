package zds.dc.org.zhaodashen.webService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.model.bean.HomePageBean;

/**目测此接口未必有用
 * 如果要用  请商议修改UserModel相关字段
 * Created by 2017 on 2017/12/6.
 */

public interface SortUsersService {
    @GET("user/sortUsers")
    Call<HomePageBean.HPUserModel> SortUsers();
}
