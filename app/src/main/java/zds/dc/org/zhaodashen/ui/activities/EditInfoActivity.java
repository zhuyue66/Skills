package zds.dc.org.zhaodashen.ui.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;
import zds.dc.org.zhaodashen.ImageUriToPath;
import zds.dc.org.zhaodashen.R;
import zds.dc.org.zhaodashen.model.UserModel;
import zds.dc.org.zhaodashen.model.bean.PictureModel;

public class EditInfoActivity extends AppCompatActivity {

    private Toolbar tbEditInfoAct;
    private CircleImageView circleImageViewHeadPic;
    private TextInputLayout tilnickName,tilIntro,tilQq,tilName;
    private EditText etNickName,etIntro,etQq,etName;
    private CheckBox cbBoy,cbGirl;
    private Button btnUpdate;
    private String imagePath;
    private ImageUriToPath imageUritoPath;

    private String strGender,strNickName,strIntro,strQq,strName;


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        initView();
    }

    private void initView(){
        tbEditInfoAct = findViewById(R.id.tb_edit_info_activity);
        setSupportActionBar(tbEditInfoAct);
        getSupportActionBar().setTitle("个人信息");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        circleImageViewHeadPic = findViewById(R.id.cirlv_edit_info_head_pic);
        circleImageViewHeadPic.setOnClickListener(clickListener);
        tilIntro = findViewById(R.id.til_intro);
        tilnickName = findViewById(R.id.til_nick_name);
        tilQq = findViewById(R.id.til_qq);
        tilName = findViewById(R.id.til_name);
        etIntro = findViewById(R.id.et_intro);
        etNickName = findViewById(R.id.et_nick_name);
        etName = findViewById(R.id.et_name);
        etQq = findViewById(R.id.et_qq);
        cbGirl = findViewById(R.id.cb_girl);
        progressBar = findViewById(R.id.progress_edit_info);

        cbBoy = findViewById(R.id.cb_boy);
        cbBoy.setOnCheckedChangeListener(checkedChangeListener);
        cbGirl.setOnCheckedChangeListener(checkedChangeListener);
        btnUpdate = findViewById(R.id.btn_check_update_info);
        imageUritoPath = new ImageUriToPath(getContentResolver());
        circleImageViewHeadPic.setOnClickListener(clickListener);
        btnUpdate.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cirlv_edit_info_head_pic:
                    if(ContextCompat.checkSelfPermission(EditInfoActivity.this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(EditInfoActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }else {
                        openAlbum();
                    }
                    break;
                case R.id.btn_check_update_info:
                    checkBeforeUpdate();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(EditInfoActivity.this,"您无此权限！",Toast.LENGTH_SHORT).show();
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
            circleImageViewHeadPic.setImageBitmap(bitmap);
        }else {
            Toast.makeText(EditInfoActivity.this,"图片未找到",Toast.LENGTH_SHORT).show();
        }
    }


    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.cb_boy:
                    if (isChecked){
                        cbGirl.setChecked(false);
                    }
                    break;
                case R.id.cb_girl:
                    if (isChecked){
                        cbBoy.setChecked(false);
                    }
            }

        }
    };

    private void checkBeforeUpdate(){

        strNickName = etNickName.getText().toString();
        strIntro = etIntro.getText().toString();
        strQq = etQq.getText().toString();
        strName = etName.getText().toString();

        if (imagePath==null){
            Toast.makeText(EditInfoActivity.this,"请上传头像",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cbBoy.isChecked()&&!cbGirl.isChecked()){
            Toast.makeText(EditInfoActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
            return;
        }else if (cbBoy.isChecked()){
            strGender = "男生";
        }else if (cbGirl.isChecked()){
            strGender = "女生";
        }
        if (strName.equals("")){
            Toast.makeText(EditInfoActivity.this,"请填写姓名",Toast.LENGTH_SHORT).show();
            return;
        }

        if (strNickName.equals("")){
            Toast.makeText(EditInfoActivity.this,"请填写昵称",Toast.LENGTH_SHORT).show();
            return;
        }
        if (strIntro.equals("")){
            Toast.makeText(EditInfoActivity.this,"请填写个人简介",Toast.LENGTH_SHORT).show();
            return;
        }
        if (strQq.equals("")){
            Toast.makeText(EditInfoActivity.this,"请填写QQ",Toast.LENGTH_SHORT).show();
            return;
        }
        updateHeadPic(imagePath);
    }


    private void updateHeadPic(String path){
        progressBar.setVisibility(View.VISIBLE);
        File file = new File(path);
        final BmobFile bmobFile = new BmobFile(file);
        btnUpdate.setClickable(false);
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
                                btnUpdate.setClickable(true);
                                progressBar.setVisibility(View.INVISIBLE);
                                UserModel.getUserModel().getUserMessage().setPortrait_url(bmobFile.getUrl());
                                UserModel.getUserModel().getUserMessage().setGender(strGender);
                                UserModel.getUserModel().getUserMessage().setName(strName);
                                UserModel.getUserModel().getUserMessage().setNick_name(strNickName);
                                UserModel.getUserModel().getUserMessage().setIntro(strIntro);
                                UserModel.getUserModel().getUserMessage().setQq(strQq);

                                Toast.makeText(EditInfoActivity.this,"个人信息更新成功",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditInfoActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        UserModel.logout();
    }
}
