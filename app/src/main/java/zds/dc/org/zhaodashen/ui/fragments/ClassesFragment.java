package zds.dc.org.zhaodashen.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;
import zds.dc.org.zhaodashen.model.bean.ClassesItemsBean;
import zds.dc.org.zhaodashen.presenter.ClassesPresenter;
import zds.dc.org.zhaodashen.ui.Adapters.ClassDetailAdapterBRVAH;
import zds.dc.org.zhaodashen.ui.Adapters.ClassesDetailAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.ClassesListAdapter;
import zds.dc.org.zhaodashen.ui.activities.ClassItemsDetailAct;


public class ClassesFragment extends Fragment implements IMainContracts.IClassesView {

    private List<String> mlistItem = new ArrayList<>();
    private static IMainContracts.IMainActView iMainActView;
    private RecyclerView recyclerViewlist;
    private RecyclerView recyclerViewdetail;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager   gridLayoutManager;
    private ClassesListAdapter classesListAdapter;
    private ClassesDetailAdapter classesDetailAdapter;
    private Boolean IsFirstClicks = true;
    private ClassesPresenter classesPresenter;
    private ClassDetailAdapterBRVAH classDetailAdapterBRVAH;

    private List<ClassesDetailBean> classesDetailBeanList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initListData();
        View view = inflater.inflate(R.layout.fragment_classes,container,false);
        initView(view);
        setOnClickEvent();
        recyclerViewlist.setAdapter(classesListAdapter);
        recyclerViewdetail.setAdapter(classDetailAdapterBRVAH);
        return view;
    }

    @Override
    public  void initView(View view){

        Toolbar toolbar = view.findViewById(R.id.classes_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        //调用classesPresenter的构造函数。
        classesPresenter = new ClassesPresenter(ClassesFragment.this);

        recyclerViewlist = view.findViewById(R.id.classes_listItem);
        linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerViewlist.setLayoutManager(linearLayoutManager);
        classesListAdapter = new ClassesListAdapter(MyApplication.getContext(),mlistItem);
        recyclerViewlist.setHasFixedSize(true);
        recyclerViewdetail=view.findViewById(R.id.classes_detailItem);
        gridLayoutManager = new GridLayoutManager(MyApplication.getContext(),3);
        recyclerViewdetail.setLayoutManager(gridLayoutManager);
        classesDetailBeanList = ClassesItemsBean.getClassItemsList("热门推荐");
        classDetailAdapterBRVAH = new ClassDetailAdapterBRVAH(R.layout.classes_detailitem,classesDetailBeanList);
        classDetailAdapterBRVAH.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClassesDetailBean classesDetailBean = classesDetailBeanList.get(position);
                Intent intent = new Intent(getActivity(), ClassItemsDetailAct.class);
                intent.putExtra("img",classesDetailBean.getClassesimage());
                intent.putExtra("title",classesDetailBean.getClassestext());
                intent.putExtra("desc",classesDetailBean.getClassesDecs());
                startActivity(intent);
            }
        });
        recyclerViewdetail.setAdapter(classesDetailAdapter);
        }

    @Override
    public void refreshAdapter(View view){      //刷新数据
        classesDetailAdapter.toNotifyData();
    }

    @Override
    public void setOnClickEvent(){
            classesListAdapter.setOnItemClickListener(new ClassesListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    classesDetailBeanList.clear();
                    classesDetailBeanList = ClassesItemsBean.getClassItemsList(mlistItem.get(position));
                    classDetailAdapterBRVAH = new ClassDetailAdapterBRVAH(R.layout.classes_detailitem,classesDetailBeanList);
                    classDetailAdapterBRVAH.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            ClassesDetailBean classesDetailBean = classesDetailBeanList.get(position);
                            Intent intent = new Intent(getActivity(), ClassItemsDetailAct.class);
                            intent.putExtra("img",classesDetailBean.getClassesimage());
                            intent.putExtra("title",classesDetailBean.getClassestext());
                            intent.putExtra("desc",classesDetailBean.getClassesDecs());
                            startActivity(intent);

                        }
                    });
                    recyclerViewdetail.setAdapter(classDetailAdapterBRVAH);
                }
            });
        }

    /**初始化列表数据*/
    private void initListData(){
        if(IsFirstClicks){
            mlistItem.add("热门推荐");
            mlistItem.add("运动健康");
            mlistItem.add("技术服务");
            mlistItem.add("艺术培训");
            mlistItem.add("语言培训");
            mlistItem.add("游戏陪玩");
            mlistItem.add("生活服务");
            mlistItem.add("丽人时尚");
            mlistItem.add("租赁服务");
            IsFirstClicks=false;
        }

    }


    public static ClassesFragment newInstance(IMainContracts.IMainActView MainActView){
        ClassesFragment classesFragment = new ClassesFragment();
        iMainActView = MainActView;
        return classesFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();//清除Activity中toolbar的文字与图标
        inflater.inflate(R.menu.homepage_toolbar, menu);
    }

    @Override//为toolbar里面的控件设置点击事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.classes_toolbar_Message:
                Toast.makeText(MyApplication.getContext(),"消息",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }
}
