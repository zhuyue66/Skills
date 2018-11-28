package zds.dc.org.zhaodashen.ui.fragments;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.model.bean.User;
import zds.dc.org.zhaodashen.ui.activities.CollectionDetailActivity;
import zds.dc.org.zhaodashen.ui.activities.GuideActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private android.support.v7.app.ActionBar actionBar;
    private RelativeLayout relativeLayout;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        Toolbar toolbar = view.findViewById(R.id.setting_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("设置");
        }
        setHasOptionsMenu(true);
        relativeLayout = view.findViewById(R.id.logout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GuideActivity.class);
                startActivity(intent);
                getActivity().finish();
                UserModel.logout();
            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                AboutMeFragment aboutMeFragment = new AboutMeFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container,aboutMeFragment)
                        .commit();
                break;
        }
        return true;
    }


}
