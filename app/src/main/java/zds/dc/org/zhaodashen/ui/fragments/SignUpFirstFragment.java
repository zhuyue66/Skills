package zds.dc.org.zhaodashen.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.ui.activities.GuideActivity;
import zds.dc.org.zhaodashen.utils.HandleKeyBoard;

/**
 * 注册账号的时候用来验证手机号
 */
public class SignUpFirstFragment extends Fragment implements IGuideContracts.ISignUpFirstFragView{

    private static IGuideContracts.IGuideActView iGuideActView;
    private Toolbar toolbar;
    private EditText etSignUpTel;
    private EditText etSignUpValidate;
    private TextView tvGetValidate;
    private Button btnNextSetPW;



    public SignUpFirstFragment() {
        // Required empty public constructor
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

    public static SignUpFirstFragment newInstance(IGuideContracts.IGuideActView guideActView) {
        SignUpFirstFragment fragment = new SignUpFirstFragment();
        iGuideActView = guideActView;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_first, container, false);
        view.setOnClickListener(clickListener);
        //set and get actionbar
        initView(view);
        return view;
    }


    private void initView(View view){
        toolbar = view.findViewById(R.id.tb_sign_up_first_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        //set edittext to support get/lost focusable and could paste validate text
        etSignUpTel = view.findViewById(R.id.et_sign_up_first_tel);
        etSignUpValidate = view.findViewById(R.id.et_sign_up_first_validate);
        etSignUpValidate.setLongClickable(true);
        etSignUpTel.addTextChangedListener(textWatcher);
        etSignUpValidate.addTextChangedListener(textWatcher);
        etSignUpTel.setOnFocusChangeListener(etONFocusChangeListener);
        etSignUpValidate.setOnFocusChangeListener(etONFocusChangeListener);
        //set textview while be clicked to get validate text
        tvGetValidate = view.findViewById(R.id.tv_sign_up_first_get_validate);
        tvGetValidate.setOnClickListener(clickListener);
        //set button change status follow the edittext's status
        btnNextSetPW = view.findViewById(R.id.btn_sign_up_first_next);
        btnNextSetPW.setOnClickListener(clickListener);
        btnNextSetPW.setEnabled(false);
    }



    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            btnNextSetPW.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(etSignUpTel.getText().toString().length() < 11 ||
                    etSignUpValidate.getText().toString().length()<6)){
                btnNextSetPW.setEnabled(true);
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
                case R.id.ll_sign_up_first_frag_root_view:
                    InputMethodManager imm = (InputMethodManager) MyApplication.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    break;
                case R.id.et_sign_up_first_validate:
                    etSignUpValidate.setFocusable(true);
                    break;
                case R.id.tv_sign_up_first_get_validate:
                    Toast.makeText(MyApplication.getContext(),"获取验证码",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_sign_up_first_next:
                    iGuideActView.registerAccount(etSignUpTel.getText().toString()
                            ,etSignUpValidate.getText().toString(),true);
                    break;
                default:
                    break;
            }
        }
    };

}
