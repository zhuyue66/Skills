package zds.dc.org.zhaodashen.ui.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import zds.dc.org.zhaodashen.R;

/**
 * Created by admin on 2017/12/1.
 */

public class ClassesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private OnItemClickListener onItemClickListener;
    private List<String>   mlistItem = new ArrayList<>();
    private List<Boolean>  isClicks = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Boolean        isFirstClicks = true;

    public ClassesListAdapter(Context context, List<String> mlistItem){
        layoutInflater=LayoutInflater.from(context);
        this.mlistItem=mlistItem;

        for (int i=0;i<mlistItem.size();i++){
            isClicks.add(false);
        }
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.item);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.classes_listitem_layout,parent,false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //第一次开启fragment，设置默认为第一个Item被选中
        if(isFirstClicks){
            isClicks.set(0,true);
            isFirstClicks=false;
        }
        String itemText = mlistItem.get(position);
        ((ViewHolder)holder).textView.setText(itemText);
        holder.itemView.setTag(position);
        Log.e("666", "onBindViewHolder: " );
        if (isClicks.get(position)){
            ((ViewHolder) holder).textView.setBackgroundColor(Color.parseColor("#f0f0f0"));
        }
        for (int i=0;i<isClicks.size();i++){
            if (!isClicks.get(position)){
                //需要知道系统的默认背景颜色具体的数值为多少
                ((ViewHolder) holder).textView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mlistItem.size();
    }


    @Override
    public void onClick(View v) {
        for(int i=0;i<isClicks.size();i++){
            isClicks.set(i,false);
        }

        if (onItemClickListener!=null){
                onItemClickListener.onItemClick(v,(int)v.getTag());
                isClicks.set((int)v.getTag(),true);
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
