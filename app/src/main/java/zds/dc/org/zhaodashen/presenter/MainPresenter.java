package zds.dc.org.zhaodashen.presenter;

import zds.dc.org.zhaodashen.contracts.IMainContracts;

/**
 * @author zhuyue66
 * @date 2017/11/26
 */

public class MainPresenter implements IMainContracts.MainPresenter {

    private IMainContracts.IHomePageView iHomePageView;

    public MainPresenter(IMainContracts.IHomePageView iHomePageView) {
        this.iHomePageView = iHomePageView;
    }
}
