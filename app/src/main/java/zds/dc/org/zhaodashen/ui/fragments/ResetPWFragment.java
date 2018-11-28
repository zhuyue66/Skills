package zds.dc.org.zhaodashen.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import android.widget.Toolbar;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;
import zds.dc.org.zhaodashen.utils.HandleKeyBoard;

/**
 * 验证通过之后重置密码
 */
public class ResetPWFragment extends Fragment {

    private static IGuideContracts.IGuideActView iGuideActView;
    private android.support.v7.widget.Toolbar toolbar;
    private EditText etNewPW;
    private EditText etRepeatPW;
    private Button btnFinishResetPW;

    public ResetPWFragment() {
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
    public static ResetPWFragment newInstance(IGuideContracts.IGuideActView guideActView) {
        ResetPWFragment fragment = new ResetPWFragment();
        iGuideActView = guideActView;
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_pw, container, false);
        view.setOnClickListener(clickListener);
        toolbar = view.findViewById(R.id.tb_reset_pw_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        etNewPW = view.findViewById(R.id.et_reset_pw_second_input_pw);
        etRepeatPW = view.findViewById(R.id.et_reset_pw_second_repeat_pw);
        etNewPW.addTextChangedListener(textWatcher);
        etRepeatPW.addTextChangedListener(textWatcher);
        etNewPW.setOnFocusChangeListener(etONFocusChangeListener);
        etRepeatPW.setOnFocusChangeListener(etONFocusChangeListener);
        etNewPW.setLongClickable(false);
        etRepeatPW.setLongClickable(false);
        btnFinishResetPW = view.findViewById(R.id.btn_reset_pw_second_finish);
        btnFinishResetPW.setOnClickListener(clickListener);
        return view;
    }



    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_reset_pw_second_finish:
                    iGuideActView.alterPassWord(iGuideActView.returnAccountTel(),etRepeatPW.getText().toString(),true);
                    break;
                case R.id.ll_reset_pw_root_view:
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
            btnFinishResetPW.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(etNewPW.getText().toString().length()<7)
                    &&(etNewPW.getText().toString().equals(etRepeatPW.getText().toString()))){
                btnFinishResetPW.setEnabled(true);
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
