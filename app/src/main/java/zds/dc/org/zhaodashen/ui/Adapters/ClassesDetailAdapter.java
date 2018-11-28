package zds.dc.org.zhaodashen.ui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;

/**
 * Created by admin on 2017/12/9.
 */

public class ClassesDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private  List<ClassesDetailBean> cssbean;
    private  LayoutInflater layoutInflater;
    private  OnIconClickListener  onIconClickListerner;

    public ClassesDetailAdapter(List<ClassesDetailBean> cssbean){
         this.cssbean = cssbean;
         layoutInflater =LayoutInflater.from(MyApplication.getContext());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.classes_detailitem,parent,false);
        ViewHolderDetail vh = new ViewHolderDetail(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onClick(View v) {
        if(onIconClickListerner!=null){
            onIconClickListerner.iconClick(v,(int)v.getTag());
        }
      //  toNotifyData(); /*不清楚是否真的需要*/
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置图片
        Picasso.with(MyApplication.getContext()).load(/*cssbean.get(position).getClassesimage()).into(((ViewHolderDetail)holder).imageButton*/R.drawable.testimage);
        ((ViewHolderDetail)holder).textView.setText(/*cssbean.get(position).getClassestext()*/"测试文字");
        //设置标志位TAG获取控件的位置
         holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return cssbean.size();
    }

    public void toNotifyData(){
        notifyDataSetChanged();

    }

    /**监听接口*/
    public  interface OnIconClickListener{
        void iconClick(View view,int position);
    }

    public void setOnIconClickListerner(OnIconClickListener onIconClickListerner){
        this.onIconClickListerner=onIconClickListerner;
    }
    public class ViewHolderDetail extends RecyclerView.ViewHolder{
        ImageView image;
        TextView    textView;
       public ViewHolderDetail(View view){
           super(view);
           image = view.findViewById(R.id.classesDetail_Image);
           textView =view.findViewById(R.id.classes_detail_text);
       }
    }
}
