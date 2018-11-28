package zds.dc.org.zhaodashen.model.bean;

import java.util.ArrayList;
import java.util.List;

import zds.dc.org.zhaodashen.R;

/**
 * Created by Aaron on 2018/3/18.
 */

public class CollectionBean {

    private static List<CollectionItem> collectionItemList = new ArrayList<>();

    public static List<CollectionItem> getCollectionItemList(){
        CollectionItem collectionItem0 = new CollectionItem(R.drawable.img_collection_design,"设计","关于平面设计的一切");
        CollectionItem collectionItem1 = new CollectionItem(R.drawable.img_collection_coding,"程序员","你一定想看的编程知识");
        CollectionItem collectionItem2 = new CollectionItem(R.drawable.img_collection_sheying,"摄影","用镜头记录下的世界");
        CollectionItem collectionItem3 = new CollectionItem(R.drawable.img_collection_shipin,"视频","这是视频制作的一个角落");
        CollectionItem collectionItem4 = new CollectionItem(R.drawable.img_collection_yundong,"运动","你一定想看的编程知识");
        CollectionItem collectionItem5 = new CollectionItem(R.drawable.img_collection_youxii,"游戏","一起感受游戏的快乐");
        CollectionItem collectionItem6 = new CollectionItem(R.drawable.img_collection_huihua,"绘画","笔尖划过的地方是向往");
        CollectionItem collectionItem7 = new CollectionItem(R.drawable.img_collection_yuyan,"语言","语言学习得有技巧才行");
        CollectionItem collectionItem8 = new CollectionItem(R.drawable.img_collection_meizhuang,"美妆","美妆达人教你如何化妆");
        CollectionItem collectionItem9 = new CollectionItem(R.drawable.img_collection_chuanda,"穿搭","不会穿搭？看这里！");
        CollectionItem collectionItem10 = new CollectionItem(R.drawable.img_collection_shufa,"书法","中华传统文化的瑰宝");
        CollectionItem collectionItem11  = new CollectionItem(R.drawable.img_collection_chanpin,"产品","好的产品是让人习以为常");
        CollectionItem collectionItem12 = new CollectionItem(R.drawable.img_collection_yinyue,"音乐","用心灵去感受每一个音符");
        CollectionItem collectionItem13 = new CollectionItem(R.drawable.img_collection_chuangyi,"创意","每个idea都可以无限放大");
        CollectionItem collectionItem14 = new CollectionItem(R.drawable.img_collection_xiaojiqiao,"小技巧","最实用生活小技巧");
        CollectionItem collectionItem15 = new CollectionItem(R.drawable.img_collection_shuma,"数码","网罗最新鲜的数码资讯");
        collectionItemList.add(collectionItem0);
        collectionItemList.add(collectionItem1);
        collectionItemList.add(collectionItem2);
        collectionItemList.add(collectionItem3);
        collectionItemList.add(collectionItem4);
        collectionItemList.add(collectionItem5);
        collectionItemList.add(collectionItem6);
        collectionItemList.add(collectionItem7);
        collectionItemList.add(collectionItem8);
        collectionItemList.add(collectionItem9);
        collectionItemList.add(collectionItem10);
        collectionItemList.add(collectionItem11);
        collectionItemList.add(collectionItem12);
        collectionItemList.add(collectionItem13);
        collectionItemList.add(collectionItem14);
        collectionItemList.add(collectionItem15);
        return collectionItemList;
    }

    public static class CollectionItem{
        public int imageResId;
        public String titleRes;
        public String descRes;

        public CollectionItem(int id, String title,String descRes){
            this.imageResId = id;
            this.titleRes = title;
            this.descRes = descRes;
        }

    }
}
