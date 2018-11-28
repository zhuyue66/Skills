package zds.dc.org.zhaodashen.presenter;

import android.content.Context;

import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.HomePageModel;
import zds.dc.org.zhaodashen.ui.Adapters.HomePagePersonListAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.HomePageSkillListAdapter;
import zds.dc.org.zhaodashen.ui.Adapters.HomepageTopViewPagerAdapter;

/**
 * @author zhuyue66
 * @date 2017/12/6
 */

public class HomePagePresenter implements IMainContracts.HomePagePresenter {
    private IMainContracts.IHomePageView iHomePageView;
    private HomePageModel homePageModel;

    public HomePagePresenter(IMainContracts.IHomePageView iHomePageView) {
        this.iHomePageView = iHomePageView;
        homePageModel = new HomePageModel();
    }

    @Override
    public HomepageTopViewPagerAdapter getTopAdapter() {
        return new HomepageTopViewPagerAdapter(homePageModel.getTopBean());
    }

    @Override
    public HomePagePersonListAdapter getPersonListAdapter() {
        return new HomePagePersonListAdapter(R.layout.person_item,homePageModel.getPersonList());
    }

    @Override
    public HomePageSkillListAdapter getSkillListAdapter() {
        return new HomePageSkillListAdapter(R.layout.mainlist_item,homePageModel.getSkillList());
    }


}
