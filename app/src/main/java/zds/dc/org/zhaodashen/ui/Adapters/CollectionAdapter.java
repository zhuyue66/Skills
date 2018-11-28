package zds.dc.org.zhaodashen.ui.Adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.bean.CollectionBean;

/**
 * Created by Aaron on 2018/3/19.
 */

public class CollectionAdapter extends BaseQuickAdapter<CollectionBean.CollectionItem,BaseViewHolder> {
    public CollectionAdapter(int layoutResId, @Nullable List<CollectionBean.CollectionItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBean.CollectionItem item) {
        helper.setImageResource(R.id.iv_collection_item,item.imageResId);
        helper.setText(R.id.tv_collection_item_title,item.titleRes);
        helper.setText(R.id.tv_collection_item_desc,item.descRes);
    }
}
