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
import android.widget.TextView;
import android.widget.Toast;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.ui.activities.GuideActivity;
import zds.dc.org.zhaodashen.utils.HandleKeyBoard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPWFragment extends Fragment {

    private static IGuideContracts.IGuideActView iGuideActView;
    private Toolbar toolbar;
    private EditText etForgetPWTel;
    private EditText etForgetPWValidate;
    private TextView tvForgetGetValidate;
    private Button btnForgetToNext;


    public ForgetPWFragment() {
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

    public static ForgetPWFragment newInstance(IGuideContracts.IGuideActView guideActView) {
        ForgetPWFragment fragment = new ForgetPWFragment();
        iGuideActView = guideActView;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_pw, container, false);
        view.setOnClickListener(clickListener);
        toolbar = view.findViewById(R.id.tb_forget_pw_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        etForgetPWTel = view.findViewById(R.id.et_forget_pw_tel_num);
        etForgetPWValidate = view.findViewById(R.id.et_forget_pw_validate);
        etForgetPWValidate.setLongClickable(true);
        etForgetPWValidate.addTextChangedListener(textWatcher);
        etForgetPWTel.addTextChangedListener(textWatcher);
        etForgetPWTel.setOnFocusChangeListener(etONFocusChangeListener);
        etForgetPWValidate.setOnFocusChangeListener(etONFocusChangeListener);
        //
        tvForgetGetValidate = view.findViewById(R.id.tv_forget_pw_get_validate);
        btnForgetToNext = view.findViewById(R.id.btn_forget_pw_next);
        btnForgetToNext.setOnClickListener(clickListener);
        tvForgetGetValidate.setOnClickListener(clickListener);
        return view;
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            btnForgetToNext.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(etForgetPWTel.getText().toString().length() < 11 ||
                    etForgetPWValidate.getText().toString().length()<6)){
                btnForgetToNext.setEnabled(true);
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

    View.OnClickListener  clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_forget_pw_root_view:
                    InputMethodManager imm = (InputMethodManager) MyApplication.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    break;
                case R.id.btn_forget_pw_next:
                    iGuideActView.registerAccount(etForgetPWTel.getText().toString()
                            ,etForgetPWValidate.getText().toString(),false);
                    break;
                case R.id.tv_forget_pw_get_validate:
                    Toast.makeText(MyApplication.getContext(),"获取验证码",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

}
