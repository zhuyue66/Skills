package zds.dc.org.zhaodashen.ui.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.OrderModel;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;
import zds.dc.org.zhaodashen.model.bean.ClassesItemsBean;
import zds.dc.org.zhaodashen.webApi.DemandPublishAPI;
import zds.dc.org.zhaodashen.webService.DemandPublish;

public class EditDemandActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TagFlowLayout tagFlowLayout,tagClassify,tagSecondClass;
    private LinearLayout llChooseServWays;
    private TextView tvDemandClass,tvStartTime,tvEndTime,tvDemandSecondClass;
    private EditText etDemandName,etDemandPrice,etDemandDetails;
    private Button btnPostDemand;
    private final String[] strSrvWays = new String[]{"教程详解","面对面服务","远程服务"};
    private final String[] strSrvTags = new String[]{"热门推荐","运动健康","技术服务","艺术培训",
            "语言培训","游戏培训","生活服务",
            "丽人时尚","租赁服务"};

    private String[] tagsSecond;

    private LayoutInflater layoutInflater;
    private Set<Integer> setChooseSvrWays = new HashSet<>();
    private Set<Integer> setChooseSvrTags = new HashSet<>();
    private boolean postStatus=true;
    private String strDemandTag,strDemandSecondTag;

    private String strDemandClass,strStartTime,strEndTime,strDemandName,strDemandPrice,strDemandDetails,strDemandWays="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_demand);
        initViews();
    }

    private void initViews(){
        tagFlowLayout = findViewById(R.id.tag_choose_service_ways);
        tagSecondClass = findViewById(R.id.tag_demand_tags_2);
        tvDemandClass = findViewById(R.id.tv_demand_class);
        tvStartTime = findViewById(R.id.tv_demand_start_time);
        tvEndTime = findViewById(R.id.tv_demand_end_time);
        tvDemandSecondClass = findViewById(R.id.tv_demand_second_class);
        tvDemandSecondClass.setOnClickListener(clickListener);
        etDemandName = findViewById(R.id.et_demand_name);
        etDemandPrice = findViewById(R.id.et_demand_price);
        etDemandDetails = findViewById(R.id.et_demand_details);
        btnPostDemand = findViewById(R.id.btn_post_demand);
        llChooseServWays = findViewById(R.id.ll_choose_serve_ways);
        //点击事件
        tvDemandClass.setOnClickListener(clickListener);
        tvStartTime.setOnClickListener(clickListener);
        tvEndTime.setOnClickListener(clickListener);
        btnPostDemand.setOnClickListener(clickListener);
        llChooseServWays.setOnClickListener(clickListener);
        layoutInflater = LayoutInflater.from(this);
        tagFlowLayout.setAdapter(new TagAdapter<String>(strSrvWays) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagFlowLayout,false);
                tvTag.setText(s);
                return tvTag;
            }
        });

        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setChooseSvrWays = selectPosSet;
            }
        });

        tagClassify = findViewById(R.id.tag_demand_tags);
        tagClassify.setAdapter(new TagAdapter<String>(strSrvTags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagClassify,false);
                tvTag.setText(s);
                return tvTag;
            }
        });
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_demand_class:
                    tvDemandSecondClass.setText("");
                    if (tagClassify.getVisibility()==View.VISIBLE){
                        tagClassify.setVisibility(View.GONE);
                    }else if (tagClassify.getVisibility()==View.GONE){
                        tagClassify.setVisibility(View.VISIBLE);
                        get_set_tvClass();
                    }

                    break;
                case R.id.tv_demand_start_time:
                    showStartCalendar();
                    break;
                case R.id.tv_demand_end_time:
                    showEndCalendar();
                    break;
                case R.id.btn_post_demand:
                    //待处理，按钮重复点击
                    postDemand();
                    break;
                case R.id.ll_choose_serve_ways:
                    //Toast.makeText(MyApplication.getContext(),"弹出一个方式窗口",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_demand_second_class:
                    if (tvDemandClass.getText().toString().equals("")){
                        Toast.makeText(EditDemandActivity.this,"请先选择一级分类",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!tvDemandClass.getText().toString().equals("")){
                        tagClassify.setVisibility(View.GONE);
                        List<ClassesDetailBean> classesItemsBeanList = ClassesItemsBean.getClassItemsList(tvDemandClass.getText().toString());
                        tagsSecond = new String[classesItemsBeanList.size()];
                        for (int i=0 ; i<tagsSecond.length ; i++){
                            tagsSecond[i] = classesItemsBeanList.get(i).getClassestext();
                        }
                        tagSecondClass.setVisibility(View.VISIBLE);
                        tagSecondClass.setAdapter(new TagAdapter<String>(tagsSecond) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagSecondClass,false);
                                tvTag.setText(s);
                                return tvTag;
                            }
                        });
                        get_set_tvSecondClass();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void get_set_tvSecondClass() {
        tagSecondClass.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setChooseSvrTags = selectPosSet;
                for (int i:setChooseSvrTags){
                    strDemandSecondTag = tagsSecond[i];
                }
                tvDemandSecondClass.setText(strDemandSecondTag);
                tagSecondClass.setVisibility(View.GONE);
            }
        });



    }

    private void get_set_tvClass(){
        tagClassify.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setChooseSvrTags = selectPosSet;
                for (int i:setChooseSvrTags){
                    strDemandTag = strSrvTags[i];
                }
                tvDemandClass.setText(strDemandTag);
                tagClassify.setVisibility(View.GONE);
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.N)
    private void showStartCalendar(){
        Calendar now = Calendar.getInstance();
        @SuppressLint("WrongConstant") DatePickerDialog dpd = DatePickerDialog.newInstance(
                EditDemandActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "DatepickerdialogStart");
        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                strStartTime = ""+year+"-"+monthOfYear+"-"+dayOfMonth;
                tvStartTime.setText(strStartTime);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void showEndCalendar(){
        Calendar now = Calendar.getInstance();
        @SuppressLint("WrongConstant") DatePickerDialog dpd = DatePickerDialog.newInstance(
                EditDemandActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "DatepickerdialogEnd");
        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                strEndTime = ""+year+"-"+monthOfYear+"-"+dayOfMonth;
                tvEndTime.setText(strEndTime);
            }
        });

    }

    private void postDemand(){
        btnPostDemand.setClickable(false);
        //获取所有的数据
        getEveryContent();
        //有空值则提示
        if (!checkEveryContentNotNull()){
            Toast.makeText(MyApplication.getContext(),"请确保所有内容都填充完整!",Toast.LENGTH_SHORT).show();
            return;
        }
        //无空值则将数据提交
        DemandPublishAPI demandPublishAPI = new DemandPublishAPI();
        DemandPublish demandPublish = demandPublishAPI.getService();

        Call<OrderModel> response = demandPublish.demandPublish(strDemandName,strDemandDetails,strDemandPrice,strDemandWays,strDemandClass,
                strDemandSecondTag,strStartTime,strEndTime,String.valueOf(UserModel.getUserModel().getUserIntInfo("user_id")));
        response.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                Toast.makeText(MyApplication.getContext(),"发布成功",Toast.LENGTH_SHORT).show();
                btnPostDemand.setClickable(true);
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"发布失败",Toast.LENGTH_SHORT).show();
                btnPostDemand.setClickable(true);
            }
        });


        //将标签值清空
        strDemandWays="";
    }

    private void getEveryContent(){
        // TODO: 18-2-6 需求分类 strDemandClass
        strDemandName = etDemandName.getText().toString();
        strDemandClass = tvDemandClass.getText().toString();
        strDemandPrice = etDemandPrice.getText().toString();
        strDemandDetails = etDemandDetails.getText().toString();
        for (int i:setChooseSvrWays){
            strDemandWays= strDemandWays+strSrvWays[i]+",";
        }
        if (setChooseSvrWays.size()>0){
            strDemandWays= strDemandWays.substring(0,strDemandWays.length()-1);
        }
    }


    //确保所有的内容都不为空
    private boolean checkEveryContentNotNull(){
        if (strDemandClass==null&&strDemandName==null){
            return false;
        }else if (strStartTime==null&&strEndTime==null){
            return false;
        }else if (strDemandWays==null&&strDemandPrice==null){
            return false;
        }else if (strDemandDetails==null&&strDemandSecondTag.equals("")){
            return false;
        }else {
            return true;
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    }
}
