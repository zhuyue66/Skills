package zds.dc.org.zhaodashen.webApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 2017 on 2017/12/6.
 */

public abstract class webApi {
    Retrofit getApi(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public abstract <T> T getService();
}
