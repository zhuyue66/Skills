package zds.dc.org.zhaodashen.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.presenter.HomePagePresenter;
import zds.dc.org.zhaodashen.ui.Adapters.HomePagePersonListAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.HomePageSkillListAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.HomepageTopViewPagerAdapter;


public class HomePageFragment extends Fragment implements IMainContracts.IHomePageView {

    private static IMainContracts.IMainActView iMainActView;
    private IMainContracts.HomePagePresenter homePagePresenter = new HomePagePresenter(this);

    private String TAG = "TAG";
    private ViewPager homepage_ViewPager;
    private RecyclerView personList;
    private RecyclerView skillList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homepage = inflater.inflate(R.layout.fragment_home_page,container,false);
        //toolbar的初始化
        Toolbar toolbar = homepage.findViewById(R.id.homepage_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setTitle("我");
        //ViewPager的初始化
        homepage_ViewPager = homepage.findViewById(R.id.homePage_viewpager);
        HomepageTopViewPagerAdapter adapter = homePagePresenter.getTopAdapter();
        homepage_ViewPager.setAdapter(adapter);
        //人物列表的初始化
        LinearLayoutManager linearLayoutManager_person = new LinearLayoutManager(MyApplication.getContext());
        linearLayoutManager_person.setOrientation(LinearLayoutManager.HORIZONTAL);
        personList = homepage.findViewById(R.id.person_list);
        personList.setLayoutManager(linearLayoutManager_person);
        HomePagePersonListAdapter personListAdapter = homePagePresenter.getPersonListAdapter();
        personListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: ");
                Toast.makeText(MyApplication.getContext(), "人物列表" + position, Toast.LENGTH_SHORT).show();
            }
        });
        personList.setAdapter(personListAdapter);
        //技能列表的初始化
        skillList = homepage.findViewById(R.id.skill_list);
        LinearLayoutManager linearLayoutManager_skills = new LinearLayoutManager(MyApplication.getContext());
        linearLayoutManager_skills.setOrientation(LinearLayoutManager.VERTICAL);
        skillList.setLayoutManager(linearLayoutManager_skills);
        HomePageSkillListAdapter skillListAdapter = homePagePresenter.getSkillListAdapter();
        skillListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: ");
                Toast.makeText(MyApplication.getContext(), "技能列表" + position, Toast.LENGTH_SHORT).show();
            }
        });
        skillList.setAdapter(skillListAdapter);
        /*MyDividerItemDecoration(Context context, int orientation, int dividerHeight, int dividerColor)*/
        /*skillList.addItemDecoration(new MyDividerItemDecoration(MyApplication.getContext(),
                MyDividerItemDecoration.VERTICAL_LIST,10,Color.argb(255,240,240,240)));*/
        return homepage;
    }

    public static HomePageFragment newInstance(IMainContracts.IMainActView MainActView){
        HomePageFragment homePageFragment = new HomePageFragment();
        iMainActView = MainActView;
        return homePageFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "fragment中的onCreateOptionsMenu()");
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
