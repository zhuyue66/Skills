package zds.dc.org.zhaodashen.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import zds.dc.org.zhaodashen.R;

public class CollectionDetailActivity extends AppCompatActivity {

    private ImageView iv_collection_detail_head;
    private TextView tv_collection_detail_title,tv_collection_detail_desc;
    private int imgResId;
    private String titleRes,descRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        imgResId = getIntent().getIntExtra("img",0);
        titleRes = getIntent().getStringExtra("title");
        descRes = getIntent().getStringExtra("desc");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);
        initView();
    }


    private void initView(){
        iv_collection_detail_head = findViewById(R.id.iv_collection_detail_head);
        tv_collection_detail_title = findViewById(R.id.tv_collection_detail_head_title);
        tv_collection_detail_desc = findViewById(R.id.tv_collection_detail_head_desc);
        iv_collection_detail_head.setImageResource(imgResId);
        tv_collection_detail_title.setText(titleRes);
        tv_collection_detail_desc.setText(descRes);
    }
}
