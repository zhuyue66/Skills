package zds.dc.org.zhaodashen;

import android.app.Application;
import android.content.Context;

/**
 * Created by 2017 on 2017/11/19.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
