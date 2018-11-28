package zds.dc.org.zhaodashen.ui.Adapters;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.bean.HomePageBean;

/**
 * The type Home page person list adapter.
 *
 * @author zhuyue66
 * @date 2017 /11/21
 */
public class HomePagePersonListAdapter extends BaseQuickAdapter<HomePageBean.HPUserModel.UserListBean,BaseViewHolder> {

    /**
     * Instantiates a new Home page person list adapter.
     *
     * @param layoutResId     子项布局id
     * @param personListBeans 数据
     */
    public HomePagePersonListAdapter(int layoutResId, List<HomePageBean.HPUserModel.UserListBean> personListBeans) {
        super(layoutResId, personListBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageBean.HPUserModel.UserListBean personListBean) {
        Glide.with(MyApplication.getContext())
                .load(personListBean.getPortrait_url())
                .crossFade()
                .into( (ImageView) helper.getView(R.id.person_icon) );
        Log.d(TAG, "convert: 00000昵称" + personListBean.getNick_name());
        helper.setText(R.id.person_name,personListBean.getNick_name());
        helper.setText(R.id.person_skill, personListBean.getIntro());
    }
}