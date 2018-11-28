package zds.dc.org.zhaodashen.ui.activities;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.ClassesModel;
import zds.dc.org.zhaodashen.model.OrderModel;
import zds.dc.org.zhaodashen.model.SkillModel;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;
import zds.dc.org.zhaodashen.model.bean.ClassesItemsBean;
import zds.dc.org.zhaodashen.webApi.DemandPublishAPI;
import zds.dc.org.zhaodashen.webApi.SkillPublishAPI;
import zds.dc.org.zhaodashen.webService.DemandPublish;
import zds.dc.org.zhaodashen.webService.SkillPublishService;

public class EditSkillActivity extends AppCompatActivity {
    private TagFlowLayout tagSkillWays,tagSkillTime,tagSkillClass,tagSkillClassSecond;
    private String strSkillName,strSkillClass,strSkillClassSecond,strSkillWays=".",strSkillTime="",strSkillDetails;
    private final String[] strsSkillWays = new String[]{"教程详解","面对面服务","远程服务"};
    private final String[] strsSkillTime = new String[]{"周一","周二","周三","周四","周五","周六","周日"};
    private final String[] strsSkillTags = new String[]{"热门推荐","运动健康","技术服务","艺术培训",
            "语言培训","游戏培训","生活服务",
            "丽人时尚","租赁服务"};


    private Set<Integer> setSkillTags = new HashSet<>(),setSkillTime = new HashSet<>(),setSkillWays = new HashSet<>();

    private TextView tvSkillClass,tvSkillSecondClass;
    private EditText etSkillName,etSkillDetails;
    private Button btnPostSkill;
    private LayoutInflater layoutInflater;
    private String[] tagsSecond;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_skill);
        initViews();
    }

    private void initViews(){
        layoutInflater = LayoutInflater.from(this);
        tagSkillTime = findViewById(R.id.tag_choose_skill_time);
        tagSkillWays = findViewById(R.id.tag_choose_skill_ways);
        tagSkillClass = findViewById(R.id.tag_skill_tags);
        tagSkillClassSecond = findViewById(R.id.tag_skill_tags_2);
        tvSkillClass = findViewById(R.id.tv_skill_class);
        tvSkillClass.setOnClickListener(clickListener);
        tvSkillSecondClass = findViewById(R.id.tv_skill_second_class);
        tvSkillSecondClass.setOnClickListener(clickListener);
        etSkillName = findViewById(R.id.et_skill_name);
        //etSkillPrice = findViewById(R.id.et_skill_price);
        etSkillDetails = findViewById(R.id.et_skill_details);
        btnPostSkill = findViewById(R.id.btn_post_skill);
        btnPostSkill.setOnClickListener(clickListener);
        tagSkillTime.setAdapter(new TagAdapter<String>(strsSkillTime) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagSkillTime,false);
                tvTag.setText(s);
                return tvTag;
            }
        });
        tagSkillWays.setAdapter(new TagAdapter<String>(strsSkillWays) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagSkillWays,false);
                tvTag.setText(s);
                return tvTag;
            }
        });
        tagSkillClass.setAdapter(new TagAdapter<String>(strsSkillTags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagSkillClass,false);
                tvTag.setText(s);
                return tvTag;
            }
        });

        tagSkillWays.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setSkillWays = selectPosSet;
            }
        });
        tagSkillTime.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                setSkillTime = selectPosSet;
            }
        });
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_skill_class:
                    tagSkillClassSecond.setVisibility(View.GONE);
                    tvSkillSecondClass.setText("");
                    if (tagSkillClass.getVisibility()==View.VISIBLE){
                        tagSkillClass.setVisibility(View.GONE);
                    }else if (tagSkillClass.getVisibility()==View.GONE){
                        tagSkillClass.setVisibility(View.VISIBLE);
                        get_set_tvClass();
                    }
                    break;
                case R.id.tv_skill_second_class:
                    if (tvSkillClass.getText().toString().equals("")){
                        Toast.makeText(EditSkillActivity.this,"请先选择一级分类",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!tvSkillClass.getText().toString().equals("")){
                        tagSkillClass.setVisibility(View.GONE);
                        List<ClassesDetailBean> classesItemsBeanList = ClassesItemsBean.getClassItemsList(tvSkillClass.getText().toString());
                        tagsSecond = new String[classesItemsBeanList.size()];
                        for (int i=0 ; i<tagsSecond.length ; i++){
                            tagsSecond[i] = classesItemsBeanList.get(i).getClassestext();
                        }
                        tagSkillClassSecond.setVisibility(View.VISIBLE);
                        tagSkillClassSecond.setAdapter(new TagAdapter<String>(tagsSecond) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagSkillClassSecond,false);
                                tvTag.setText(s);
                                return tvTag;
                            }
                        });
                        get_set_tvSecondClass();
                    }
                    break;
                case R.id.btn_post_skill:
                    //待处理，按钮重复点击
                    postSkill();
                    break;
                default:
                    break;
            }
        }
    };

    private void get_set_tvClass(){
        tagSkillClass.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setSkillTags = selectPosSet;
                for (int i:setSkillTags){
                    strSkillClass = strsSkillTags[i];
                }
                tvSkillClass.setText(strSkillClass);
                tagSkillClass.setVisibility(View.GONE);
            }
        });
    }

    private void get_set_tvSecondClass(){
        tagSkillClassSecond.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setSkillTags = selectPosSet;
                for (int i:setSkillTags){
                    strSkillClassSecond = tagsSecond[i];
                }
                tvSkillSecondClass.setText(strSkillClassSecond);
                tagSkillClassSecond.setVisibility(View.GONE);
            }
        });
    }


    private void postSkill(){
        btnPostSkill.setClickable(false);
        getEveryContent();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String postDate = dateFormat.format(date);
        if (checkEveryContentNotNull()==false){
            Toast.makeText(MyApplication.getContext(),"请确保所有的信息都填写完成",Toast.LENGTH_SHORT).show();
            return;
        }
        //如果数据没有空值将提交
        SkillPublishAPI skillPublishAPI = new SkillPublishAPI();
        SkillPublishService skillPublishService = skillPublishAPI.getService();
        Call<SkillModel> response = skillPublishService.skillPublish(strSkillName,"学生",strSkillWays,strSkillTime
                , String.valueOf(UserModel.getUserModel().getUserIntInfo("user_id")) ,postDate,strSkillDetails,strSkillClass,strSkillClassSecond);
        Log.d("IDIDIDIIDIIDIDI", "postSkill: "+UserModel.getUserModel().getUserIntInfo("user_id"));
        response.enqueue(new Callback<SkillModel>() {
            @Override
            public void onResponse(Call<SkillModel> call, Response<SkillModel> response) {
                Toast.makeText(MyApplication.getContext(),"发布成功",Toast.LENGTH_SHORT).show();
                btnPostSkill.setClickable(true);
            }

            @Override
            public void onFailure(Call<SkillModel> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"发布失败",Toast.LENGTH_SHORT).show();
                btnPostSkill.setClickable(true);
            }
        });


    }


    //获取所有的内容
    private void getEveryContent(){
        // TODO: 18-2-6 需求分类 strDemandClass
        strSkillClassSecond = "";
        strSkillClass = "";
        strSkillTime = "";
        strSkillWays = "";
        strSkillName = etSkillName.getText().toString();
        strSkillClass = tvSkillClass.getText().toString();
        strSkillDetails = etSkillDetails.getText().toString();
        strSkillClassSecond = tvSkillSecondClass.getText().toString();
        //获取服务方式
        for (int i:setSkillWays){
            strSkillWays= strSkillWays+strsSkillWays[i]+",";
        }
        if (setSkillWays.size()>0){
            strSkillWays= strSkillWays.substring(0,strSkillWays.length()-1);
        }
        //获取服务时间
        for (int i:setSkillTime){
            strSkillTime= strSkillTime+strsSkillTime[i]+",";
        }
        if (setSkillWays.size()>0){
            strSkillTime= strSkillTime.substring(0,strSkillTime.length()-1);
        }
    }

    //确保所有的内容都不为空
    private boolean checkEveryContentNotNull(){
        if (strSkillClass==null&&strSkillDetails==null){
            return false;
        }else if (strSkillName==null&&strSkillClassSecond.equals("")){
            return false;
        }else if (strSkillTime==null&&strSkillWays==null){
            return false;
        }else return true;
    }
}
