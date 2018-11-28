package zds.dc.org.zhaodashen.webApi;

import retrofit2.Retrofit;
import zds.dc.org.zhaodashen.webService.AlterPWApiService;

/**密码重置
 * Created by 2017 on 2017/12/6.
 */

public class AlterPWAPI extends webApi {

    String url = "http://123.207.36.208/";

    Retrofit retrofit = getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(AlterPWApiService.class);
    }
}
