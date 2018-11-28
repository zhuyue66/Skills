package zds.dc.org.zhaodashen.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import zds.dc.org.zhaodashen.MyApplication;

/**
 * Created by 2017 on 2017/11/24.
 * 帮助处理键盘显示与否
 */

public class HandleKeyBoard {

    public static void showKeyBoard(EditText editText){
        InputMethodManager inputMethodManager = (InputMethodManager) MyApplication.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText,InputMethodManager.SHOW_IMPLICIT);

    }

    public static void hideKeyBoard(EditText editText){
        InputMethodManager inputMethodManager = (InputMethodManager) MyApplication.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken()
                ,InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
