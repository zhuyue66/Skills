package zds.dc.org.zhaodashen.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import zds.dc.org.zhaodashen.R;

public class ClassItemsDetailAct extends AppCompatActivity {
    private int imgResId;
    private String titleRes,descRes;
    private ImageView imgHeader;
    private TextView tb_title,header_title,header_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        imgResId =  getIntent().getIntExtra("img",0);
        titleRes = getIntent().getStringExtra("title");
        descRes = getIntent().getStringExtra("desc");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_items_detail);
        initView();

    }

    private void initView(){
        imgHeader = findViewById(R.id.iv_class_item_detail_act_header);
        header_title = findViewById(R.id.tv_class_item_detail_act_header_title);
        header_desc = findViewById(R.id.tv_class_item_detail_act_header_desc);
        tb_title = findViewById(R.id.tv_tb_class_item_detail_act);
        imgHeader.setImageResource(imgResId);
        tb_title.setText(titleRes);
        header_title.setText(titleRes);
        header_desc.setText(descRes);
    }
}
