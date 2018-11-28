package zds.dc.org.zhaodashen.presenter;

import android.widget.Toast;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.contracts.ISettingContracts;
import zds.dc.org.zhaodashen.model.UserModel;

/**
 * @author zhuyue66
 * @date 2017/12/9
 */

public class SettingPresenter implements ISettingContracts.SettingPresenter {

    private ISettingContracts.ISettingActView iSettingActView;
    private UserModel userModel;

    public SettingPresenter(ISettingContracts.ISettingActView iSettingActView) {
        this.iSettingActView = iSettingActView;
        userModel = UserModel.getUserModel();
    }

    @Override
    public void Suggestion() {
        Toast.makeText(MyApplication.getContext(),"意见反馈",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkUpdate() {
        Toast.makeText(MyApplication.getContext(),"检查更新",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Logout() {
    }

    @Override
    public void DeleteBuffer() {
        Toast.makeText(MyApplication.getContext(),"清除缓存",Toast.LENGTH_SHORT).show();
    }

    @Override
    public Boolean ShowTips(Boolean permission) {
        if (permission){
            Toast.makeText(MyApplication.getContext(),"已允许",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(MyApplication.getContext(),"已禁止",Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void Share() {
        Toast.makeText(MyApplication.getContext(),"推荐找大神给好友",Toast.LENGTH_SHORT).show();
    }
}
