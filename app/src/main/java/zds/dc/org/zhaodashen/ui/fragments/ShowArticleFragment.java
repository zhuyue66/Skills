package zds.dc.org.zhaodashen.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zds.dc.org.zhaodashen.R;

/**
 * A simple {@link Fragment} subclass.
 * 展示文章的Fragment
 */
public class ShowArticleFragment extends Fragment {


    public ShowArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_article, container, false);
    }

}
