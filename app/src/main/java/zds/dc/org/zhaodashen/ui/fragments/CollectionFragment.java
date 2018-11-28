package zds.dc.org.zhaodashen.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;
import zds.dc.org.zhaodashen.model.bean.CollectionBean;
import zds.dc.org.zhaodashen.ui.Adapters.CollectionAdapter;
import zds.dc.org.zhaodashen.ui.activities.CollectionDetailActivity;

public class CollectionFragment extends Fragment implements IMainContracts.ICollectionView {

    private RecyclerView recyclerView;
    private NestedScrollView nestedScrollView;
    private List<CollectionBean.CollectionItem> collectionItemList;

    private static IMainContracts.IMainActView iMainActView;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        Toolbar toolbar = view.findViewById(R.id.collection_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.rv_collection_list);
        nestedScrollView = view.findViewById(R.id.nessc_collection);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        collectionItemList = CollectionBean.getCollectionItemList();
        final CollectionAdapter collectionAdapter = new CollectionAdapter(R.layout.collection_item_layout, collectionItemList);
        recyclerView.setAdapter(collectionAdapter);
        collectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CollectionBean.CollectionItem collectionItem = collectionItemList.get(position);
                Intent intent = new Intent(getActivity(), CollectionDetailActivity.class);
                intent.putExtra("img",collectionItem.imageResId);
                intent.putExtra("title",collectionItem.titleRes);
                intent.putExtra("desc",collectionItem.descRes);
                startActivity(intent);
            }
        });
        return view;
    }

    public static CollectionFragment newInstance(IMainContracts.IMainActView MainActView){
        CollectionFragment collectionFragment = new CollectionFragment();
        iMainActView = MainActView;
        return collectionFragment;
    }

    private void initView(){

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
