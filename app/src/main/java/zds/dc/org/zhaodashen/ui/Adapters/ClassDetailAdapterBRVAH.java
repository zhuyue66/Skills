package zds.dc.org.zhaodashen.ui.Adapters;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;

/**
 * Created by Aaron on 2018/3/18.
 */

public class ClassDetailAdapterBRVAH extends BaseQuickAdapter<ClassesDetailBean,BaseViewHolder> {


    public ClassDetailAdapterBRVAH(int layoutResId, @Nullable List<ClassesDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassesDetailBean item) {
        helper.setImageResource(R.id.classesDetail_Image,item.getClassesimage());
        helper.setText(R.id.classes_detail_text,item.getClassestext());

    }
}
