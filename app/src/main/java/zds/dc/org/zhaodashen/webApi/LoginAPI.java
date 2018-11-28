package zds.dc.org.zhaodashen.webApi;

import retrofit2.Retrofit;
import zds.dc.org.zhaodashen.webService.LoginApiService;

/**账号登录
 * Created by 2017 on 2017/12/6.
 */

public class LoginAPI extends webApi{
    String url = "http://123.207.36.208/";
    Retrofit retrofit = getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(LoginApiService.class);
    }
}
