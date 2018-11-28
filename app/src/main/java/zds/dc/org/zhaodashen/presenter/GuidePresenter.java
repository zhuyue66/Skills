package zds.dc.org.zhaodashen.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.ui.activities.EditInfoActivity;
import zds.dc.org.zhaodashen.webApi.AlterPWAPI;
import zds.dc.org.zhaodashen.webApi.LoginAPI;
import zds.dc.org.zhaodashen.webApi.RegisterAPI;
import zds.dc.org.zhaodashen.webService.*;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.ui.activities.GuideActivity;

/**
 * Created by 2017 on 2017/11/14.
 */

public class GuidePresenter implements IGuideContracts.IGuidePresenter{

    public final String baseUrl = "http://123.207.36.208/";

    public static final String PASSWORD_INCORRECT = "password incorrect";
    public static final String ACCOUNT_NOT_EXIST = "account not exist";
    public static final String LOGIN_SUCCESS = "login success";
    public static final String ACC_ALREADY_EXIST_WITH_PW = "account has benn registered, have set password";
    public static final String ACC_ALREADY_EXIST_WITHOUT_PW = "account has benn registered, not set password";
    public static final String REGISTER_SUCCESS = "register success";
    public static final String ALTER_SUCCESS = "alter success";

    private String account;
    private Context mContext;

    private IGuideContracts.IGuideActView iGuideActView;

    public GuidePresenter(IGuideContracts.IGuideActView iGuideActView,Context context){
        this.iGuideActView = iGuideActView;
        this.mContext = context;
    }


    @Override
    public void loginAccount(String accountTel, String password) {
        Login(accountTel,password);
    }

    @Override
    public void registerAccount(String accountTel, String authCode,boolean isRealRegister) {
        Register(accountTel,authCode,isRealRegister);
    }

    @Override
    public void alterPassWord(String accountTel, String password,boolean isRealAlterPW) {
        AlterPW(accountTel,password,isRealAlterPW);
    }


    @Override
    public String returnAccount() {
        return account;
    }

    private boolean Login(String accountTel,String password){
        LoginAPI loginAPI = new LoginAPI();
        LoginApiService loginApiService = loginAPI.getService();
        Call<UserModel> response = loginApiService.Login(accountTel,password);
        response.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                String loginInformation = response.body().getInformation();
                switch(loginInformation){
                    case LOGIN_SUCCESS:
                        Toast.makeText(MyApplication.getContext(),"登录成功！",Toast.LENGTH_SHORT).show();
                        response.body().initUserLocal(response.body().getUserMessage());
                        response.body().setInformation(loginInformation);
                        response.body().setStatus(response.body().getStatus());
                        Log.d("PRESENTER", "onResponse: "+response.body().getUserMessage().getNick_name());
                        if (response.body().getUserMessage().getPortrait_url().equals("")){
                            Toast.makeText(MyApplication.getContext(),"请完善个人信息!",Toast.LENGTH_SHORT).show();
                            mContext.startActivity(new Intent(mContext, EditInfoActivity.class));
                            return;
                        }
                        iGuideActView.skipToMainAct();
                        break;
                    case ACCOUNT_NOT_EXIST:
                        Toast.makeText(MyApplication.getContext(),"该账户不存在！",Toast.LENGTH_SHORT).show();
                        break;
                    case PASSWORD_INCORRECT:
                        Toast.makeText(MyApplication.getContext(),"密码错误，请确认后重试",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MyApplication.getContext(),"网络繁忙，请稍后重试！",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("LoginFailed", "onFailure: "+t.getMessage());
                if (t.getMessage().equals("Failed to connect to /123.207.36.208:80")){
                    Toast.makeText(MyApplication.getContext(),"网络异常，请确认是否已经连接网络！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return false;
    }

    private void Register(final String accountTel, String authCode, final boolean isRealRegister){
        RegisterAPI registerAPI = new RegisterAPI();
        RegisterApiService registerApiService = registerAPI.getService();
        Call<UserModel> response = registerApiService.Register(accountTel,authCode);
        response.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                String loginInformation = response.body().getInformation();
                switch(loginInformation){
                    case REGISTER_SUCCESS:
                    case ACC_ALREADY_EXIST_WITHOUT_PW:
                        // TODO: 2017/12/5 进入密码设置，未设置密码直接返回 会导致无法继续注册（该账号已经注册） 
                        Toast.makeText(MyApplication.getContext(),"注册成功,请设置密码!",Toast.LENGTH_SHORT).show();
                        iGuideActView.checkoutFrag(GuideActivity.SIGN_UP_SECOND_FRAGMENT);
                        account =accountTel;
                        break;
                    case ACC_ALREADY_EXIST_WITH_PW:
                        if (isRealRegister){
                            Toast.makeText(MyApplication.getContext(),"该账户已经存在！如忘记密码请前往重置！",Toast.LENGTH_LONG).show();
                        }else {
                            iGuideActView.checkoutFrag(GuideActivity.RESET_PW_FRAGMENT);
                            account = accountTel;
                        }
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("LoginFailed", "onFailure: "+t.getMessage());
            }
        });
    }

    private void AlterPW(final String accountTel, final String password, boolean isRealAlterPW){
        AlterPWAPI alterPWAPI = new AlterPWAPI();
        AlterPWApiService alterPWApiService = alterPWAPI.getService();
        Call<UserModel> response = alterPWApiService.AlterPW(accountTel,password);
        response.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                try {
                    String loginInformation = response.body().getInformation();
                    Log.d("XXXXXXXXXXX", "onResponse: "+loginInformation);
                    switch(loginInformation){
                        case ALTER_SUCCESS:
                            Toast.makeText(MyApplication.getContext(),"密码设置成功！",Toast.LENGTH_SHORT).show();
                            iGuideActView.loginAccount(accountTel,password);
                            break;
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("LoginFailed", "onFailure: "+t.getMessage());
            }
        });

    }
}

