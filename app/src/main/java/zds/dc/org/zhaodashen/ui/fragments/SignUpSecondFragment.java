package zds.dc.org.zhaodashen.ui.fragments;

import android.content.Context;
import android.net.Uri;
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
import android.widget.Toast;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.utils.HandleKeyBoard;

/**
 * 验证通过之后输入密码
 */
public class SignUpSecondFragment extends Fragment implements IGuideContracts.ISignUpSecondFragView{

    private static IGuideContracts.IGuideActView iGuideActView;
    private Toolbar toolbar;
    private EditText etSignNewPW;//密码最少7位最多16位
    private EditText etSignRepeatPW;
    private Button btnSignFinish;

    public SignUpSecondFragment() {
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
        setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            iGuideActView.directBackDesktop(true);
        }
        return true;
    }

    // TODO: Rename and change types and number of parameters
    public static SignUpSecondFragment newInstance(IGuideContracts.IGuideActView guideActView) {
        SignUpSecondFragment fragment = new SignUpSecondFragment();
        iGuideActView = guideActView;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_second, container, false);
        view.setOnClickListener(clickListener);
        toolbar = view.findViewById(R.id.tb_sign_up_second_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        etSignNewPW = view.findViewById(R.id.et_sign_up_second_input_pw);
        etSignRepeatPW = view.findViewById(R.id.et_sign_up_second_repeat_pw);
        etSignNewPW.addTextChangedListener(textWatcher);
        etSignRepeatPW.addTextChangedListener(textWatcher);
        etSignNewPW.setLongClickable(false);
        etSignNewPW.setOnFocusChangeListener(etONFocusChangeListener);
        etSignRepeatPW.setOnFocusChangeListener(etONFocusChangeListener);
        etSignRepeatPW.setLongClickable(false                                                                                                                                                                                );
        btnSignFinish  = view.findViewById(R.id.btn_sign_up_second_finish);
        btnSignFinish.setOnClickListener(clickListener);
        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_sign_up_second_finish:
                    //Toast.makeText(MyApplication.getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    iGuideActView.alterPassWord(iGuideActView.returnAccountTel(),etSignRepeatPW.getText().toString(),false);
                    break;
                case R.id.ll_sign_up_second_frag_root_view:
                    InputMethodManager imm = (InputMethodManager) MyApplication.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    break;
                default:
                    break;
            }
        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            btnSignFinish.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(etSignNewPW.getText().toString().length()<7)
                    &&(etSignNewPW.getText().toString().equals(etSignRepeatPW.getText().toString()))){
                btnSignFinish.setEnabled(true);
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


}
