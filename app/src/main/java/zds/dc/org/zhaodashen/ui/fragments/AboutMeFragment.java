package zds.dc.org.zhaodashen.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.presenter.MainPresenter;
import zds.dc.org.zhaodashen.ui.activities.EditInfoActivity;
import zds.dc.org.zhaodashen.ui.activities.GuideActivity;
import zds.dc.org.zhaodashen.ui.activities.MainActivity;
import zds.dc.org.zhaodashen.ui.activities.SettingActivity;

public class AboutMeFragment extends Fragment implements View.OnClickListener ,IMainContracts.IAboutMeView{

    private final String TAG = "TAG";
    private static IMainContracts.IMainActView iMainActView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View aboutMe = inflater.inflate(R.layout.fragment_about_me, container, false);

        Toolbar toolbar = aboutMe.findViewById(R.id.me_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setTitle("我");

        RelativeLayout me = aboutMe.findViewById(R.id.me);
        LinearLayout needs = aboutMe.findViewById(R.id.needs);
        LinearLayout skills = aboutMe.findViewById(R.id.skills);
        RelativeLayout save = aboutMe.findViewById(R.id.save);
        RelativeLayout setting = aboutMe.findViewById(R.id.setting);
        RelativeLayout about_us = aboutMe.findViewById(R.id.about_us);
        me.setOnClickListener(this);
        needs.setOnClickListener(this);
        skills.setOnClickListener(this);
        save.setOnClickListener(this);
        setting.setOnClickListener(this);
        about_us.setOnClickListener(this);
        return aboutMe;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.me:
                if (!UserModel.getUserModel().getUserStringInfo("information").equals("login success")){
                    Intent intent = new Intent(getActivity(), GuideActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), EditInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.needs:
                Toast.makeText(MyApplication.getContext(),"需求管理",Toast.LENGTH_LONG).show();
                break;
            case R.id.skills:
                Toast.makeText(MyApplication.getContext(),"技能管理",Toast.LENGTH_LONG).show();
                break;
            case R.id.save:
                Toast.makeText(MyApplication.getContext(),"收藏",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting:
                SettingFragment settingFragment = new SettingFragment();

                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container,settingFragment)
                        .commit();
                /*Toast.makeText(MyApplication.getContext(),"设置",Toast.LENGTH_LONG).show();*/
                break;
            case R.id.about_us:
                Toast.makeText(MyApplication.getContext(),"关于我们",Toast.LENGTH_LONG).show();
                break;
        }
    }

    public static AboutMeFragment newInstance(IMainContracts.IMainActView mainActView){
        AboutMeFragment aboutMeFragment = new AboutMeFragment();
        iMainActView = mainActView;
        return aboutMeFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "fragment中的onCreateOptionsMenu()");
        menu.clear();//清除Activity中toolbar的文字与图标
        inflater.inflate(R.menu.aboutme_toolbar, menu);
    }

    @Override//为toolbar里面的控件设置点击事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutMe_toolbar_massage:
                Toast.makeText(MyApplication.getContext(),"消息",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }
}
