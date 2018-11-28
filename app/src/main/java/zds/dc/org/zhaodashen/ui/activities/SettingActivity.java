package zds.dc.org.zhaodashen.ui.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;
import com.suke.widget.SwitchButton;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.ISettingContracts;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.presenter.SettingPresenter;

public class SettingActivity extends AppCompatActivity implements ISettingContracts.ISettingActView{

    private com.suke.widget.SwitchButton switchButton;
    private Toolbar toolbar;
    private ISettingContracts.SettingPresenter settingPresenter = new SettingPresenter(SettingActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("设置");
        }

        switchButton = findViewById(R.id.switch_button);

        switchButton.setChecked(true);
        switchButton.isChecked();
        switchButton.toggle(true);//开关状态
        switchButton.toggle(true);//switch without animation
        switchButton.setShadowEffect(true);//小圆圈阴影
        switchButton.setEnabled(true);//禁用开关
        switchButton.setEnableEffect(true);//动画
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            Boolean if_selected = false;
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if_selected = settingPresenter.ShowTips(if_selected);
            }
        });

        RelativeLayout deleteBuffer = findViewById(R.id.deleteBuffer);
        deleteBuffer.setOnClickListener(new MyListener());
        RelativeLayout suggestion = findViewById(R.id.suggestion);
        suggestion.setOnClickListener(new MyListener());
        RelativeLayout checkUpdate = findViewById(R.id.checkUpdate);
        checkUpdate.setOnClickListener(new MyListener());
        RelativeLayout share = findViewById(R.id.share);
        share.setOnClickListener(new MyListener());
        RelativeLayout logout = findViewById(R.id.logout);
        logout.setOnClickListener(new MyListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.deleteBuffer:
                    settingPresenter.DeleteBuffer();
                    break;
                case R.id.suggestion:
                    settingPresenter.Suggestion();
                    break;
                case R.id.checkUpdate:
                    settingPresenter.checkUpdate();
                    break;
                case R.id.share:
                    settingPresenter.Share();
                    break;
                case R.id.logout:
                    //settingPresenter.Logout();
                    UserModel.logout();
                    Intent intent = new Intent(SettingActivity.this,GuideActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}
