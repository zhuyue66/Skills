package zds.dc.org.zhaodashen.ui.Adapters;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.bean.HomePageBean;

/**
 * @author zhuyue66
 * @date 2017/11/21
 */

public class HomePageSkillListAdapter extends BaseQuickAdapter<HomePageBean.HPOrderModel.OrdersBean,BaseViewHolder> {

    /**
     * Instantiates a new Home page skill list adapter.
     *
     * @param layoutResId 子项布局
     * @param data        数据
     */
    public HomePageSkillListAdapter(int layoutResId, @Nullable List<HomePageBean.HPOrderModel.OrdersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageBean.HPOrderModel.OrdersBean item) {
        Glide.with(MyApplication.getContext())
                .load(item.getOrders_id())//接口中缺少用户的头像URL暂时用用户ID代替
                .crossFade()
                .into( (ImageView) helper.getView(R.id.mainList_person_icon));
        helper.setText(R.id.mainList_skill_title,item.getFirst_category());
        helper.setText(R.id.mainList_skill_personName, item.getReleaser());
        helper.setText(R.id.mainList_skill_prise, item.getPrice());
        helper.setText(R.id.mainList_skill_describe, item.getDetails());
        helper.setText(R.id.mainList_skill_time, item.getRelease_date());
    }
}
