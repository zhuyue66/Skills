package zds.dc.org.zhaodashen.contracts;

import zds.dc.org.zhaodashen.ui.Adapters.HomePagePersonListAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.HomePageSkillListAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.HomepageTopViewPagerAdapter;
import android.view.View;
import java.util.List;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;

/**
 * @author zhuyue66
 * @date 2017/11/25
 *
 * MainActivity托管4个Fragment
 */

public interface IMainContracts {

    //MainActivity
    interface IMainActView{
        void checkoutFrag(String fragmentName);
        void changeColor(String fragmentName);
        void clearStatus();
    }
    interface MainPresenter{}

    //HomePageFragment
    interface IHomePageView{}
    interface HomePagePresenter{
        //获得首页顶部ViewPager的adapter
        HomepageTopViewPagerAdapter getTopAdapter();
        //获得任务列表RecyclerView的Adapter
        HomePagePersonListAdapter getPersonListAdapter();
        //获得技能列表RecyclerView的Adapter
        HomePageSkillListAdapter getSkillListAdapter();

    }

    //ClassesFragment
    interface IClassesView{
        void  initView(View v);
        void  refreshAdapter(View v);
        void  setOnClickEvent();
    }
    interface IClassesPresenter{}
    interface IClassesModel{
        List<ClassesDetailBean> getAdapterData();
    }
    //CollectionFragment
    interface ICollectionView{}
    interface CollectionPresenter{}

    //AboutMeFragment
    interface IAboutMeView{}
    interface AboutMePresenter{}

}
