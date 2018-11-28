package zds.dc.org.zhaodashen.ui.activities;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import retrofit2.Call;
import retrofit2.Callback;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jp.wasabeef.richeditor.RichEditor;
import retrofit2.Response;
import zds.dc.org.zhaodashen.ImageUriToPath;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.ArticleModel;
import zds.dc.org.zhaodashen.model.bean.PictureModel;
import zds.dc.org.zhaodashen.webApi.ArticlePublishAPI;
import zds.dc.org.zhaodashen.webService.ArticleApiService;

/**
 */

public class EditArticleActivity extends AppCompatActivity {
    private ImageView imageCover;
    private EditText etArticleName;
    private TextView tvArticleClass;
    private TagFlowLayout tagArticleTags;
    private Button btnPostArticle;

    private String imagePath;
    private ImageUriToPath imageUritoPath;

    private RichEditor richEditor;
    private String strArticleClass,strArticleName,strArticleContent,strCoverUrl;
    private LayoutInflater layoutInflater;

    private final String[] strSrvTags = new String[]{"设计","程序员","摄影",
            "视频","运动","游戏",
            "绘画","语言","美妆",
            "穿搭","书法","产品",
            "音乐","创意","小技巧",
            "数码"};
    private Set<Integer> setChooseArtcTags = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        layoutInflater = LayoutInflater.from(this);
        tagArticleTags = findViewById(R.id.tag_article_flowlayout);
        tagArticleTags.setAdapter(new TagAdapter<String>(strSrvTags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvTag = (TextView) layoutInflater.inflate(R.layout.tag_item,tagArticleTags,false);
                tvTag.setText(s);
                return tvTag;
            }
        });
        imageCover = findViewById(R.id.img_article_cover);
        imageCover.setOnClickListener(clickListener);
        tvArticleClass = findViewById(R.id.tv_article_class);
        tvArticleClass.setOnClickListener(clickListener);
        etArticleName = findViewById(R.id.et_article_name);
        richEditor = findViewById(R.id.rich_editor_article);
        richEditor.setPlaceholder("输入内容");
        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                richEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                richEditor.setItalic();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                richEditor.insertImage("http://bmob-cdn-17804.b0.upaiyun.com/2018/04/03/03e844825fcd420181a3b1f874287f9f.jpg!/scale/20","pic");
            }
        });
        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyApplication.getContext(),"插入一个链接",Toast.LENGTH_SHORT).show();
            }
        });

        btnPostArticle = findViewById(R.id.btn_post_article);
        btnPostArticle.setOnClickListener(clickListener);
        imageUritoPath = new ImageUriToPath(getContentResolver());


    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_article_class:
                    //Toast.makeText(MyApplication.getContext(),"弹出一个类别窗口",Toast.LENGTH_SHORT).show();
                    //showPopupWindow();
                    if (tagArticleTags.getVisibility()==View.VISIBLE){
                        tagArticleTags.setVisibility(View.GONE);
                    }else if (tagArticleTags.getVisibility()==View.GONE){
                        tagArticleTags.setVisibility(View.VISIBLE);
                        get_set_tvClass();
                    }

                    break;
                case R.id.btn_post_article:
                    postArticle();
                    break;
                case R.id.img_article_cover:
                    openAlbum();
                    break;

                default:
                    break;
            }
        }
    };

    private void get_set_tvClass(){
        tagArticleTags.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //获取选中标签的值
                setChooseArtcTags = selectPosSet;
                for (int i:setChooseArtcTags){
                    strArticleClass = strSrvTags[i];
                }
                tvArticleClass.setText(strArticleClass);
                tagArticleTags.setVisibility(View.GONE);
            }
        });
    }
    private void postArticle() {
        getEveryContent();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String postDate = dateFormat.format(date);
        if (checkEveryContentNotNull()==false){
            Toast.makeText(MyApplication.getContext(),"请确保所有的信息都填写完整",Toast.LENGTH_SHORT).show();
            return;
        }

        ArticlePublishAPI articlePublishAPI = new ArticlePublishAPI();
        ArticleApiService articleApiService = articlePublishAPI.getService();
        Call<ArticleModel> response = articleApiService.articlePublish(strArticleName,
                strArticleClass,strArticleContent,"8",postDate);
        response.enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                Toast.makeText(MyApplication.getContext(),"发布成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"发布失败",Toast.LENGTH_SHORT).show();
                Log.d("FFFFFFFFF", "onFailure: "+t.getMessage());
            }
        });
    }


    private void getEveryContent(){
        strArticleName = etArticleName.getText().toString();
        strArticleClass = tvArticleClass.getText().toString();
        strArticleContent = richEditor.getHtml();
    }

    private boolean checkEveryContentNotNull(){

        if (strArticleName==null&&strArticleClass==null){
            return false;
        }else if (strArticleContent==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(EditArticleActivity.this,"您无此权限！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }



    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT >= 19){
                        imagePath = imageUritoPath.handleImageOnKitKat(data,this);
                        displayImage(imagePath);
                    }else {
                        imagePath = imageUritoPath.handleImageBeforeKitKat(data);
                        displayImage(imagePath);

                    }
                }
                break;
            default:
                break;
        }
    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageCover.setImageBitmap(bitmap);
        }else {
            Toast.makeText(EditArticleActivity.this,"图片未找到",Toast.LENGTH_SHORT).show();
        }
        updateCover(imagePath);
    }


    private void updateCover(String coverPath){
        File file = new File(coverPath);
        final BmobFile bmobFile = new BmobFile(file);
        btnPostArticle.setClickable(false);
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    PictureModel pictureModel = new PictureModel();
                    pictureModel.setUrl(bmobFile.getUrl());
                    pictureModel.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                btnPostArticle.setClickable(true);
                                Toast.makeText(EditArticleActivity.this,"封面上传成功",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }


}
