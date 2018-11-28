package zds.dc.org.zhaodashen.ui.Adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.bean.HomePageBean;

/**
 * @author zhuyue66
 * @date 2017/12/9
 */

public class HomepageTopViewPagerAdapter extends PagerAdapter {

    private List<HomePageBean.TopBean> list;
    private List<View> viewList = new ArrayList<>();

    public HomepageTopViewPagerAdapter(List<HomePageBean.TopBean> list) {
        this.list = list;
        initView();
    }

    private void initView() {

        class ViewHolder{
            private TextView textView;
            private ImageView imageView;
        }

        for (int i=0; i<list.size();i++) {
            LayoutInflater layoutInflater = LayoutInflater.from(MyApplication.getContext());
            View view;
            view = layoutInflater.inflate(R.layout.viewpager_item,null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.iv_top);
            viewHolder.textView = view.findViewById(R.id.tv_top_title);
            Glide.with(MyApplication.getContext())
                    .load(list.get(i).getImageUrl())
                    .into(viewHolder.imageView);
            viewHolder.textView.setText(list.get(i).getTitle());
            viewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(viewList.get(position));
        //todo:ViewPager子项的点击事件
        viewList.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getContext(),list.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

}
