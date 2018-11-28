package zds.dc.org.zhaodashen.model.bean;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.R;

/**
 * Created by Aaron on 2018/3/18.
 */

public class ClassesItemsBean {

    public static Map<String,int[]> allClassesItems = new HashMap<String, int[]>();
    public static ArrayList<String> allClasses = new ArrayList<>();
    public static void getItems(){

        int[] ints0 = {R.drawable.item_hot_c,R.drawable.item_hot_yumaoqiu,R.drawable.item_hot_cosplay,R.drawable.item_zlfw_shipingzhanghao,
        R.drawable.item_yxpw_wzry,R.drawable.item_yspx_ui,R.drawable.item_yspx_jita,R.drawable.item_yspx_huihua,R.drawable.item_yypx_qita};

        int[] ints1 = {R.drawable.item_yundong_lanqiu,R.drawable.item_yundong_lunhua,R.drawable.item_yundong_paiqiu,
        R.drawable.item_yundong_paobu,R.drawable.item_yundong_ppq,R.drawable.item_yundong_qixing,R.drawable.item_yundong_youyong};

        int[] ints2 = {R.drawable.item_jsfw_computer,R.drawable.item_jsfw_phone,R.drawable.item_jsfw_picture,R.drawable.item_jsfw_text};

        int[] ints3 = {R.drawable.item_yspx_huihua,R.drawable.item_yspx_jita,R.drawable.item_yspx_koucai,R.drawable.item_yspx_shufa,
                R.drawable.item_yspx_ui,R.drawable.item_yspx_wudao,R.drawable.item_yspx_wushu,R.drawable.item_yspx_yinyue};

        int[] ints4 = {R.drawable.item_yypx_alaboyu,R.drawable.item_yypx_deyu,R.drawable.item_yypx_english,
                R.drawable.item_yypx_eyu,R.drawable.item_yypx_fayu,R.drawable.item_yypx_hanyu,
                R.drawable.item_yypx_riyu,R.drawable.item_yypx_xiyu,R.drawable.item_yypx_qita};

        int[] ints5 = {R.drawable.item_yxpw_cf,R.drawable.item_yxpw_chiji,R.drawable.item_yxpw_dnf,
                R.drawable.item_yxpw_qipai,R.drawable.item_yxpw_shouyou,R.drawable.item_yxpw_wzry,
                R.drawable.item_yxpw_yxlm};

        int[] ints6 = {R.drawable.item_camera,R.drawable.item_ktv,R.drawable.item_movie,R.drawable.item_other};

        int[] ints7 = {R.drawable.item_lrss_dapei,R.drawable.item_lrss_meifa,R.drawable.item_lrss_meijia,
                R.drawable.item_lrss_meirong,R.drawable.item_lrss_meizhuang,R.drawable.item_lrss_shoushen};

        int[] ints8 = {R.drawable.item_zlfw_paotui,R.drawable.item_zlfw_shipingzhanghao,R.drawable.item_zlfw_xzzhanghao,
                R.drawable.item_zlfw_yyzhanghao,R.drawable.item_zlfw_zc,R.drawable.item_zlfw_zufang};

        allClassesItems.put("热门推荐",ints0);
        allClassesItems.put("运动健康",ints1);
        allClassesItems.put("技术服务",ints2);
        allClassesItems.put("艺术培训",ints3);
        allClassesItems.put("语言培训",ints4);
        allClassesItems.put("游戏陪玩",ints5);
        allClassesItems.put("生活服务",ints6);
        allClassesItems.put("丽人时尚",ints7);
        allClassesItems.put("租赁服务",ints8);

    }

    public static int[] getDetailItems(String className){
        if (allClassesItems.size()==0){
            getItems();
        }
        int[] items = allClassesItems.get(className);
        return items;
    }

    public static List<ClassesDetailBean> getClassItemsList(String className){
        if (allClassesItems.size()==0){
            getItems();
        }
        int[] items = allClassesItems.get(className);
        List<ClassesDetailBean> classesDetailBeanList = new ArrayList<>();
        classesDetailBeanList.clear();
        if (className=="热门推荐"){

            /*int[] ints0 = {R.drawable.item_hot_c,R.drawable.item_hot_yumaoqiu,
                        R.drawable.item_hot_cosplay,R.drawable.item_zlfw_shipingzhanghao,
                        R.drawable.item_yxpw_wzry,R.drawable.item_yspx_ui,
                        R.drawable.item_yspx_jita,R.drawable.item_yspx_huihua,R.drawable.item_yypx_qita};*/

            classesDetailBeanList.add(new ClassesDetailBean(items[0],"程序编写","用代码改变我们的生活"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"羽毛球","在奔跑和跳跃中锻炼一下"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"CosPlay","CosPlay,Cos你想成为的角色"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"视频制作","高大上的视频制作其实很简单"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"王者荣耀","召唤师峡谷的河蛙还在等你"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"UI/UE设计","设计是一种生活态度"));
            classesDetailBeanList.add(new ClassesDetailBean(items[6],"乐器","学习一门乐器吧"));
            classesDetailBeanList.add(new ClassesDetailBean(items[7],"绘画手绘","用笔尖描绘世界"));
            classesDetailBeanList.add(new ClassesDetailBean(items[8],"其他","你想的找大神都有"));
            return classesDetailBeanList;
        }
        if (className=="运动健康"){
            /*int[] ints1 = {R.drawable.item_yundong_lanqiu,R.drawable.item_yundong_lunhua,R.drawable.item_yundong_paiqiu,
                    R.drawable.item_yundong_paobu,R.drawable.item_yundong_ppq,
                    R.drawable.item_yundong_qixing,R.drawable.item_yundong_youyong};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"篮球","篮球教学，就找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"轮滑","轮滑教学，就找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"排球","排球教学，就找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"跑步","跑步教学，就找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"乒乓球","乒乓球教学，就找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"骑行","骑行教学，就找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[6],"游泳","游泳教学，就找大神！"));
            return classesDetailBeanList;
        }
        if (className=="技术服务"){
            /*int[] ints2 = {R.drawable.item_jsfw_computer,R.drawable.item_jsfw_phone,R.drawable.item_jsfw_picture,R.drawable.item_jsfw_text};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"电脑维修","电脑出了问题别怕，来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"手机维修","手机出了问题别怕，来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"图片处理","不会处理图片？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"文字处理","不会处理文字？来找大神！"));
            return classesDetailBeanList;
        }
        if (className=="艺术培训"){
            /*int[] ints3 = {R.drawable.item_yspx_huihua,R.drawable.item_yspx_jita,R.drawable.item_yspx_koucai,R.drawable.item_yspx_shufa,
                    R.drawable.item_yspx_ui,R.drawable.item_yspx_wudao,R.drawable.item_yspx_wushu,R.drawable.item_yspx_yinyue};*/

            classesDetailBeanList.add(new ClassesDetailBean(items[0],"绘画","不会画画？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"吉他","不会吉他？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"口才","口才不好？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"书法","想学书法？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"UI设计","想学设计？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"舞蹈","想学舞蹈？来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[6],"武术","武术防身，来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[7],"音乐","想学音乐？来找大神！"));
            return classesDetailBeanList;
        }
        if (className=="语言培训"){
            /*int[] ints4 = {R.drawable.item_yypx_alaboyu,R.drawable.item_yypx_deyu,R.drawable.item_yypx_english,
                    R.drawable.item_yypx_eyu,R.drawable.item_yypx_fayu,R.drawable.item_yypx_hanyu,
                    R.drawable.item_yypx_qita,R.drawable.item_yypx_riyu,R.drawable.item_yypx_xiyu};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"阿拉伯语","阿拉伯语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"德语","德语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"英语","英语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"俄语","俄语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"法语","法语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"汉语","汉语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[6],"日语","日语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[7],"西班牙语","西班牙语培训还是得来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[8],"其他","培训还是得来找大神！"));
            return classesDetailBeanList;
        }
        if (className=="游戏陪玩"){
            /*int[] ints5 = {R.drawable.item_yxpw_cf,R.drawable.item_yxpw_chiji,R.drawable.item_yxpw_dnf,
                    R.drawable.item_yxpw_qipai,R.drawable.item_yxpw_shouyou,R.drawable.item_yxpw_wzry,
                    R.drawable.item_yxpw_yxlm};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"穿越火线","不充钱想玩CF就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"绝地求生","不充钱想玩绝地求生就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"DNF","不充钱想玩DNF就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"棋牌","不充钱想玩棋牌就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"手游","不充钱想玩手游就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"王者荣耀","不充钱想玩王者就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[6],"英雄联盟","不充钱想玩LOL就来找大神！"));
            return classesDetailBeanList;
        }
        if (className=="生活服务"){
            /*int[] ints6 = {R.drawable.item_camera,R.drawable.item_ktv,R.drawable.item_movie,R.drawable.item_other};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"照相","找大神来帮你照相啊！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"KTV","找大神来带你K歌啊！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"电影","找大神来约电影啊啊！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"其他","找大神，啥都有！"));
            return classesDetailBeanList;
        }
        if (className=="丽人时尚"){
           /* int[] ints7 = {R.drawable.item_lrss_dapei,R.drawable.item_lrss_meifa,R.drawable.item_lrss_meijia,
                    R.drawable.item_lrss_meirong,R.drawable.item_lrss_meizhuang,R.drawable.item_lrss_shoushen};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"搭配","大神来教你如何搭配！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"美发","大神来教你如何美发！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"美甲","大神来教你如何美甲！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"美容","大神来教你如何美容！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"美妆","大神来教你如何美妆！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"瘦身","大神来教你如何瘦身！"));
            return classesDetailBeanList;
        }
        if (className=="租赁服务"){
            /*int[] ints8 = {R.drawable.item_zlfw_paotui,R.drawable.item_zlfw_shipingzhanghao,R.drawable.item_zlfw_xzzhanghao,
                    R.drawable.item_zlfw_yyzhanghao,R.drawable.item_zlfw_zc,R.drawable.item_zlfw_zufang};*/
            classesDetailBeanList.add(new ClassesDetailBean(items[0],"跑腿","专业跑腿就来找大神！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[1],"视频账号","视频会员闲置租赁！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[2],"下载账号","下载会员闲置租赁！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[3],"音乐账号","各大音乐平台会员闲置租赁！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[4],"租车","单车电单车闲置租赁！"));
            classesDetailBeanList.add(new ClassesDetailBean(items[5],"租房","周边空置房屋租赁"));
            return classesDetailBeanList;
        }
        else{
            Toast.makeText(MyApplication.getContext(),"异常！！！",Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}
