package zds.dc.org.zhaodashen.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.contracts.IGuideContracts;

/**
 * Created by 2017 on 2017/12/4.
 */

public class UserModel implements IGuideContracts.IUserModel{

    /**
     * userMessage : {"qq":"","gender":"","latitude":"","skill_learn":"","topic_collect":"","experience":0,"auth_code":"111000","user_collect":"","collect_times":0,"password":"17786510750","user_id":11,"nick_name":"","intro":"","article_collect":"","name":"","portrait_url":"","account":"17786510750","longitude":""}
     * information : login success
     * status : 1
     */

    // TODO: 2017/12/4 完成用户信息本地存取的统一接口

    private static UserModel userModel = new UserModel();

    public static UserModel getUserModel(){
        return userModel;
    }

    @SerializedName("userMessage")
    private UserMessage userMessage = new UserMessage();
    private String information;
    private String status;


    private static SharedPreferences userSP;

    private static SharedPreferences.Editor userSPeditor;

    private UserModel(){
        Log.d("UserModel", "UserModel: ");
        userSPeditor = MyApplication.getContext()
                .getSharedPreferences("User_SharedPreference", Context.MODE_PRIVATE).edit();
        userSPeditor.apply();
        userSP = MyApplication.getContext()
                .getSharedPreferences("User_SharedPreference", Context.MODE_PRIVATE);
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    /**
     *
     * @param askKey
     * @return please check the value is not null for use
     */
    public String getUserStringInfo(String askKey){
        return userSP.getString(askKey,"");
    }

    /**
     * @param askKey
     * @return if return -1:please handle the null value
     */
    public int getUserIntInfo(String askKey){
        return userSP.getInt(askKey,-1);
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;

    }

    public static void logout(){
        userSPeditor.clear();
        userSPeditor.apply();
    }


    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
        userSPeditor.putString("information",information);
        userSPeditor.apply();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        userSPeditor.putString("status",status);
        userSPeditor.apply();
    }

    public void initUserLocal(UserMessage userMessage) {
        this.userMessage.setQq(userMessage.qq);
        this.userMessage.setGender(userMessage.gender);
        this.userMessage.setLatitude(userMessage.latitude);
        this.userMessage.setSkill_learn(userMessage.skill_learn);
        this.userMessage.setTopic_collect(userMessage.topic_collect);
        this.userMessage.setExperience(userMessage.experience);
        this.userMessage.setAuth_code(userMessage.auth_code);
        this.userMessage.setUser_collect(userMessage.user_collect);
        this.userMessage.setCollect_times(userMessage.collect_times);
        this.userMessage.setPassword(userMessage.password);
        this.userMessage.setUser_id(userMessage.user_id);
        this.userMessage.setNick_name(userMessage.nick_name);
        this.userMessage.setIntro(userMessage.intro);
        this.userMessage.setArticle_collect(userMessage.article_collect);
        this.userMessage.setName(userMessage.name);
        this.userMessage.setPortrait_url(userMessage.portrait_url);
        this.userMessage.setAccount(userMessage.account);
        this.userMessage.setLongitude(userMessage.longitude);
    }

    public static class UserMessage {
        /**
         * qq :
         * gender :
         * latitude :
         * skill_learn :
         * topic_collect :
         * experience : 0
         * auth_code : 111000
         * user_collect :
         * collect_times : 0
         * password : 17786510750
         * user_id : 11
         * nick_name :
         * intro :
         * article_collect :
         * name :
         * portrait_url :
         * account : 17786510750
         * longitude :
         */

        private String qq;
        private String gender;
        private String latitude;
        private String skill_learn;
        private String topic_collect;
        private int experience;
        private String auth_code;
        private String user_collect;
        private int collect_times;
        private String password;
        private int user_id;
        private String nick_name;
        private String intro;
        private String article_collect;
        private String name;
        private String portrait_url;
        private String account;
        private String longitude;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
            putString("qq",qq);
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
            putString("gender",gender);
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
            putString("latitude",latitude);
        }

        public String getSkill_learn() {
            return skill_learn;
        }

        public void setSkill_learn(String skill_learn) {
            this.skill_learn = skill_learn;
            putString("skill_learn",skill_learn);
        }

        public String getTopic_collect() {
            return topic_collect;
        }

        public void setTopic_collect(String topic_collect) {
            this.topic_collect = topic_collect;
            putString("topic_collect",topic_collect);
        }

        public int getExperience() {
            return experience;
        }

        public void setExperience(int experience) {
            this.experience = experience;
            putInt("experience", experience);
        }

        public String getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(String auth_code) {
            this.auth_code = auth_code;
            putString("auth_code",auth_code);
        }

        public String getUser_collect() {
            return user_collect;
        }

        public void setUser_collect(String user_collect) {
            this.user_collect = user_collect;
            putString("user_collect",user_collect);
        }

        public int getCollect_times() {
            return collect_times;
        }

        public void setCollect_times(int collect_times) {
            this.collect_times = collect_times;
            putInt("collect_times",collect_times);
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            putString("password",password);
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
            putInt("user_id",user_id);
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
            putString("nick_name",nick_name );
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
            putString("intro",intro);
        }

        public String getArticle_collect() {
            return article_collect;
        }

        public void setArticle_collect(String article_collect) {
            this.article_collect = article_collect;
            putString("article_collect",article_collect);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            putString("name",name);
        }

        public String getPortrait_url() {
            return portrait_url;
        }

        public void setPortrait_url(String portrait_url) {
            this.portrait_url = portrait_url;
            putString("portrait_url",portrait_url);
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
            putString("account",account);
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
            putString("longitude",longitude);
        }

        private String getString(String key){
            return userSP.getString(key,null);
        }

        private void putString(String key,String value){
            userSPeditor.putString(key,value);
            userSPeditor.apply();
        }

        private int getInt(String key){
            return userSP.getInt(key,0);
        }

        private void putInt(String key,int value){
            userSPeditor.putInt(key,value);
            userSPeditor.apply();
        }
    }

}
