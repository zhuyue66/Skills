package zds.dc.org.zhaodashen.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.ui.activities.GuideActivity;
import zds.dc.org.zhaodashen.utils.HandleKeyBoard;

/**
 *登陆
 */
public class SignInFragment extends Fragment implements IGuideContracts.ISignInFragView{

    private static IGuideContracts.IGuideActView iGuideActView;
    private android.support.v7.widget.Toolbar toolbar;
    private Button btnLogin;
    private EditText etTelAccount;//手机号长度限制在11位
    private EditText etPassWord;//密码最少7位，最多16位
    private TextView tvForgetPW;

    private boolean isTelAccountRight;
    private boolean isPassWordRight;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(IGuideContracts.IGuideActView guideActView) {
        SignInFragment fragment = new SignInFragment();
        iGuideActView = guideActView;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            iGuideActView.directBackDesktop(true);
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        view.setOnClickListener(clickListener);
        toolbar = view.findViewById(R.id.tb_sign_in_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        btnLogin = view.findViewById(R.id.btn_sign_in_login);
        btnLogin.setEnabled(false);
        btnLogin.setOnClickListener(clickListener);

        etTelAccount = view.findViewById(R.id.et_sign_in_tel_account);
        etPassWord = view.findViewById(R.id.et_sign_in_pass_word);
        etTelAccount.addTextChangedListener(textWatcher);
        etPassWord.addTextChangedListener(textWatcher);
        etTelAccount.setOnFocusChangeListener(etONFocusChangeListener);
        etPassWord.setOnFocusChangeListener(etONFocusChangeListener);
        tvForgetPW = view.findViewById(R.id.tv_sign_in_forget_pw);
        tvForgetPW.setOnClickListener(clickListener);
        return view;
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            btnLogin.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(etTelAccount.getText().toString().length() < 3 ||
                    etPassWord.getText().toString().length()<3)){
                btnLogin.setEnabled(true);
            }
        }
    };

    View.OnFocusChangeListener etONFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                HandleKeyBoard.hideKeyBoard((EditText) v);
            }
        }
    };




    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_sign_in_frag_root_view:
                    InputMethodManager imm = (InputMethodManager) MyApplication.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    break;
                case R.id.btn_sign_in_login:
                    // TODO: 2017/11/20 在账号密码输入都符合条件之后，通过接口将账户密码返回给后台确定是否登录成功
                    if (etTelAccount.getText().toString().length() < 3){
                        Toast.makeText(MyApplication.getContext(),"请输入正确的手机账号!",Toast.LENGTH_SHORT).show();
                    }else {
                        //UserBean.getUser().setUserPhone(etTelAccount.getText().toString());
                        iGuideActView.loginAccount(etTelAccount.getText().toString(),
                                etPassWord.getText().toString());
                    }
                    break;
                case R.id.tv_sign_in_forget_pw:
                    iGuideActView.checkoutFrag(GuideActivity.FORGET_PW_FRAGMENT);
                    break;
                default:
                    break;
            }

        }
    };


}
