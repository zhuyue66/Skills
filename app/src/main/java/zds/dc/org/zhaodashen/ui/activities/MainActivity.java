package zds.dc.org.zhaodashen.ui.activities;

/**
 * @author zhuyue66
 * @date 2017/12/6
 */

import android.content.Intent;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.model.bean.User;
import zds.dc.org.zhaodashen.ui.fragments.AboutMeFragment;
import zds.dc.org.zhaodashen.ui.fragments.ClassesFragment;
import zds.dc.org.zhaodashen.ui.fragments.CollectionFragment;
import zds.dc.org.zhaodashen.ui.fragments.HomePageFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IMainContracts.IMainActView{

    private HomePageFragment homePageFragment;
    private ClassesFragment classesFragment;
    private CollectionFragment collectionFragment;
    private AboutMeFragment aboutMeFragment;
    private FrameLayout fragmentContainer;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private final String TAG = "TAG";
    private android.support.v4.app.FragmentManager fragmentManager;
    private ImageView homepage_image,classes_image,add_image,collection_image,aboutMe_image,add_image_x;

    public static final String HOMEPAGE = "HOMEPAGE";
    public static final String CLASSES = "CLASSES";
    public static final String COLLECTION = "COLLECTION";
    public static final String ABOUTME ="ABOUTME";


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fragmentContainer = findViewById(R.id.main_fragment_container);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        initFragment();
        clearStatus();
        checkoutFrag(MainActivity.HOMEPAGE);
        changeColor(MainActivity.HOMEPAGE);
    }

    private void initView(){
        homepage_image = findViewById(R.id.homepage);
        classes_image = findViewById(R.id.classes);
        add_image = findViewById(R.id.add);
        collection_image = findViewById(R.id.collection);
        aboutMe_image = findViewById(R.id.aboutMe);
        homepage_image.setOnClickListener(this);
        classes_image.setOnClickListener(this);
        add_image.setOnClickListener(this);
        collection_image.setOnClickListener(this);
        aboutMe_image.setOnClickListener(this);
    }

    private void initFragment(){
        aboutMeFragment = AboutMeFragment.newInstance(this);
        homePageFragment = HomePageFragment.newInstance(this);
        classesFragment = ClassesFragment.newInstance(this);
        collectionFragment = CollectionFragment.newInstance(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homepage:
                clearStatus();
                checkoutFrag(MainActivity.HOMEPAGE);
                changeColor(MainActivity.HOMEPAGE);
                break;
            case R.id.classes:
                clearStatus();
                checkoutFrag(MainActivity.CLASSES);
                changeColor(MainActivity.CLASSES);
                break;
            case R.id.add:
                showPopWindow();
                break;
            case R.id.collection:
                clearStatus();
                checkoutFrag(MainActivity.COLLECTION);
                changeColor(MainActivity.COLLECTION);
                break;
            case R.id.aboutMe:
                clearStatus();
                checkoutFrag(MainActivity.ABOUTME);
                changeColor(MainActivity.ABOUTME);
                break;
            default:
                break;
        }
    }

    private void showPopWindow(){
        if (!UserModel.getUserModel().getUserStringInfo("information").equals("login success")){
            Toast.makeText(this,"请先登录！",Toast.LENGTH_SHORT).show();
            return;
        }
        View popView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popwindow_layout,null);
        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,
                true);
        popupWindow.setContentView(popView);
        ImageView add_iv_x = popView.findViewById(R.id.iv_add_x);
        add_iv_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        LinearLayout ll_add_skill,ll_add_demand,ll_add_article;
        ll_add_article = popView.findViewById(R.id.ll_add_article);
        ll_add_skill = popView.findViewById(R.id.ll_add_skill);
        ll_add_demand = popView.findViewById(R.id.ll_add_demand);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ll_add_skill:
                        Intent intentSkill = new Intent(MainActivity.this,EditSkillActivity.class);
                        startActivity(intentSkill);
                        break;
                    case R.id.ll_add_demand:
                        Intent intentDemand = new Intent(MainActivity.this,EditDemandActivity.class);
                        startActivity(intentDemand);
                        break;
                    case R.id.ll_add_article:
                        Intent intentArticle = new Intent(MainActivity.this,EditArticleActivity.class);
                        startActivity(intentArticle);
                        break;
                    default:
                        break;
                }
            }
        };
        ll_add_article.setOnClickListener(clickListener);
        ll_add_demand.setOnClickListener(clickListener);
        ll_add_skill.setOnClickListener(clickListener);
        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void checkoutFrag(String fragmentName) {
        switch (fragmentName){
            case HOMEPAGE:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container,homePageFragment,HOMEPAGE)
                        .commit();
                break;
            case CLASSES:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container,classesFragment,CLASSES)
                        .commit();
                break;
            case COLLECTION:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container,collectionFragment,COLLECTION)
                        .commit();
                break;
            case ABOUTME:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container,aboutMeFragment,ABOUTME)
                        .commit();
                break;
            default:
                break;
        }
    }


    @Override
    public void changeColor(String fragmentName){
        switch (fragmentName){
            case HOMEPAGE:
                homepage_image.setImageResource(R.drawable.homepage_selected);
                break;
            case CLASSES:
                classes_image.setImageResource(R.drawable.classes_selected);
                break;
            case COLLECTION:
                collection_image.setImageResource(R.drawable.collection_selected);
                break;
            case ABOUTME:
                aboutMe_image.setImageResource(R.drawable.me_selected);
                break;
            default:
                break;
        }
    }

    //清处当前状态
    @Override
    public void clearStatus() {
        homepage_image.setImageResource(R.drawable.homepage_unselected);
        classes_image.setImageResource(R.drawable.classes_unselected);
        collection_image.setImageResource(R.drawable.collection_unselected);
        aboutMe_image.setImageResource(R.drawable.me_unselected);
    }
}
