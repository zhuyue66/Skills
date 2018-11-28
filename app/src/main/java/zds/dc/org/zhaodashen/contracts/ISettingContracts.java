package zds.dc.org.zhaodashen.contracts;

/**
 * @author zhuyue66
 * @date 2017/12/9
 */

public interface ISettingContracts {

    interface ISettingActView{

    }

    interface SettingPresenter{
        //清楚缓存
        void DeleteBuffer();
        //推送
        Boolean ShowTips(Boolean permission);
        //推荐找大神
        void Share();
        //意见反馈
        void Suggestion();
        //检查更新
        void checkUpdate();
        //退出登录
        void Logout();
    }
}
