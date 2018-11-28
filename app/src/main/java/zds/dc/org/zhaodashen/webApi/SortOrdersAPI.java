package zds.dc.org.zhaodashen.webApi;

import retrofit2.Retrofit;
import zds.dc.org.zhaodashen.webService.SortOrdersService;

/**
 * @author zhuyue66
 * @date 2018/3/6
 */

public class SortOrdersAPI extends webApi {
    String url = "http://123.207.36.208/";
    Retrofit retrofit = getApi(url);

    @Override
    public <T> T getService() {
        return (T) retrofit.create(SortOrdersService.class) ;
    }
}
