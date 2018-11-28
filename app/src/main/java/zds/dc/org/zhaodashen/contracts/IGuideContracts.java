package zds.dc.org.zhaodashen.contracts;

import android.os.Bundle;

import zds.dc.org.zhaodashen.model.UserModel;

/**
 * Created by 2017 on 2017/11/14.
 */

public interface IGuideContracts {

    interface IGuideActView{
        void checkoutFrag(String fragmentName);
        void directBackDesktop(boolean justQuitApp);
        void loginAccount(String accoutTel,String password);
        void registerAccount(String accountTel,String authCode,boolean isRealRegister);
        void alterPassWord(String accountTel,String password,boolean isRealAlterPW);
        void skipToMainAct();
        //include account and password
        String returnAccountTel();
    }

    interface IGuideFragView{}

    interface IForgetFragView{}

    interface ISignUpFirstFragView{}

    interface ISignUpSecondFragView{}

    interface ISignInFragView{}


    interface IGuidePresenter{
        void loginAccount(String accountTel,String password);
        void registerAccount(String accountTel,String authCode,boolean isRealRegister);
        void alterPassWord(String accountTel,String password,boolean isRealAlterPW);
        String returnAccount();
    }

    interface IUserModel{
        void initUserLocal(UserModel.UserMessage userMessage);
    }

}
