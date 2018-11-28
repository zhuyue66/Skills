package zds.dc.org.zhaodashen.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Aaron on 2018/4/3.
 */

public class PictureModel extends BmobObject {

    public PictureModel(){
        this.setTableName("Picture");
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
