package zds.dc.org.zhaodashen.ui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zds.dc.org.zhaodashen.R;

/**
 * Created by Aaron on 2017/12/7.
 */

public class ArticleTagsAdapter extends RecyclerView.Adapter<ArticleTagsAdapter.TagsVH> {

    public ArticleTagsAdapter(){

    }


    @Override
    public TagsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TagsVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class TagsVH extends RecyclerView.ViewHolder{
        TextView tvTag;
        public TagsVH(View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag);
        }
    }
}
