package zds.dc.org.zhaodashen.webApi;

import retrofit2.Retrofit;
import zds.dc.org.zhaodashen.webService.DemandPublish;

/**
 * Created by Aaron on 2018/3/4.
 */

public class DemandPublishAPI extends webApi{
    String url = "http://123.207.36.208/";
    Retrofit retrofit = getApi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(DemandPublish.class);
    }
}
