package zds.dc.org.zhaodashen.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.presenter.GuidePresenter;
import zds.dc.org.zhaodashen.ui.fragments.ForgetPWFragment;
import zds.dc.org.zhaodashen.ui.fragments.ResetPWFragment;
import zds.dc.org.zhaodashen.ui.fragments.SignInFragment;
import zds.dc.org.zhaodashen.ui.fragments.SignUpFirstFragment;
import zds.dc.org.zhaodashen.ui.fragments.SignUpSecondFragment;

/**
 * Created by 2017 on 2017/11/14.
 */

public class GuideActivity extends AppCompatActivity implements IGuideContracts.IGuideActView{

    private String TAG = "GUIDE_ACTIVITY";
    private FrameLayout fragmentContainer;
    private Button btnSignIn;
    private Button btnSignUp;
    private TextView tvVisitor;


    private android.support.v4.app.FragmentTransaction fragmentTransaction;


    private SignUpFirstFragment signUpFirstFragment;
    private SignUpSecondFragment signUpSecondFragment;
    private ForgetPWFragment forgetPWFragment;
    private SignInFragment signInFragment;
    private ResetPWFragment resetPWFragment;

    private IGuideContracts.IGuidePresenter iGuidePresenter = new GuidePresenter(this,this);

    /**
     * 登录页
     * 注册第一页
     * 注册第二页
     * 重置密码页
     * 忘记密码验证页
     */
    public static final String SIGN_IN_FRAGMENT = "SIGN_IN_FRAGMENT";
    public static final String SIGN_UP_FIRST_FRAGMENT = "SIGN_UP_FIRST_FRAGMENT";
    public static final String SIGN_UP_SECOND_FRAGMENT = "SIGN_UP_SECOND_FRAGMENT";
    public static final String RESET_PW_FRAGMENT = "RESET_PW_FRAGMENT";
    public static final String FORGET_PW_FRAGMENT = "FORGET_PW_FRAGMENT";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Bmob.initialize(this,"162d2d6325afb300e5937f51d54a4647");
        if (UserModel.getUserModel().getUserStringInfo("information").equals("login success")){
            if (UserModel.getUserModel().getUserStringInfo("portrait_url").equals("")){
                Toast.makeText(GuideActivity.this,"请先完善当前账号：<"+UserModel.getUserModel().getUserStringInfo("account")+">的个人信息",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GuideActivity.this,EditInfoActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        initView();
        fragmentContainer = findViewById(R.id.fragment_guide_container);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        initFragment();

    }


    private void initView(){
        btnSignIn = findViewById(R.id.btn_guide_act_sign_in);
        btnSignUp = findViewById(R.id.btn_guide_act_sign_up);
        tvVisitor = findViewById(R.id.tv_guide_act_is_visitor);
        btnSignUp.setOnClickListener(clickListener);
        btnSignIn.setOnClickListener(clickListener);
        tvVisitor.setOnClickListener(clickListener);
    }



    private void initFragment(){
        signUpFirstFragment = SignUpFirstFragment.newInstance(this);
        signUpSecondFragment = SignUpSecondFragment.newInstance(this);
        forgetPWFragment = ForgetPWFragment.newInstance(this);
        signInFragment = SignInFragment.newInstance(this);
        resetPWFragment = ResetPWFragment.newInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void checkoutFrag(String fragmentName) {
        switch (fragmentName){
            case SIGN_IN_FRAGMENT:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_guide_container,signInFragment,SIGN_IN_FRAGMENT)
                        .addToBackStack(SIGN_IN_FRAGMENT).commit();
                break;
            case SIGN_UP_FIRST_FRAGMENT:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_guide_container,signUpFirstFragment,SIGN_UP_FIRST_FRAGMENT)
                        .addToBackStack(SIGN_UP_FIRST_FRAGMENT).commit();
                break;
            case SIGN_UP_SECOND_FRAGMENT:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_guide_container,signUpSecondFragment,SIGN_UP_SECOND_FRAGMENT)
                        .addToBackStack(SIGN_UP_SECOND_FRAGMENT).commit();
                break;
            case FORGET_PW_FRAGMENT:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_guide_container,forgetPWFragment,FORGET_PW_FRAGMENT)
                        .addToBackStack(FORGET_PW_FRAGMENT).commit();
                break;
            case RESET_PW_FRAGMENT:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_guide_container,resetPWFragment,RESET_PW_FRAGMENT)
                        .addToBackStack(RESET_PW_FRAGMENT).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void directBackDesktop(boolean justQuitApp) {
        onBackPressed();
    }

    @Override
    public void loginAccount(String accoutTel, String password) {
        iGuidePresenter.loginAccount(accoutTel,password);
    }

    @Override
    public void registerAccount(String accountTel, String authCode,boolean isRealRegister) {
        iGuidePresenter.registerAccount(accountTel,authCode,isRealRegister);
    }

    @Override
    public void alterPassWord(String accountTel, String password,boolean isRealAlterPW) {
        iGuidePresenter.alterPassWord(accountTel,password,isRealAlterPW);
    }

    @Override
    public void skipToMainAct() {
        Intent intent = new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public String returnAccountTel() {
        return iGuidePresenter.returnAccount();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_guide_act_sign_in:
                    checkoutFrag(GuideActivity.SIGN_IN_FRAGMENT);
                    break;
                case R.id.btn_guide_act_sign_up:
                    checkoutFrag(GuideActivity.SIGN_UP_FIRST_FRAGMENT);
                    break;
                case R.id.tv_guide_act_is_visitor:
                    Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            super.finish();
        }
        return false;


    }

}
