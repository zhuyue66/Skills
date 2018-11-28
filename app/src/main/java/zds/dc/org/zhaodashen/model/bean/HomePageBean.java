package zds.dc.org.zhaodashen.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyue66
 * @date 2017/12/9
 */

public class HomePageBean {

     /*** 首页顶部轮播是死的 */
    public static class TopBean {
        private String imageUrl;
        private String title;

        public TopBean(String imageUrl, String title) {
            this.imageUrl = imageUrl;
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public List<TopBean> getTopBeans() {
        //模仿构造数据
        List<TopBean> list = new ArrayList<>();
        list.add(new TopBean("https://api.i-meto.com/bing","JAVA容器-自问自学学HashMap"));
        list.add(new TopBean("https://api.i-meto.com/bing?color=White", "JAVA容器-自问自学学HashMap"));
        list.add(new TopBean("https://api.i-meto.com/bing?cat=T", "JAVA容器-自问自学学HashMap"));
        return list;
    }

    /**首页大神用户列表*/
    public static class HPUserModel{

        /**
         * userList : [{"qq":"000111","gender":"男","latitude":"","skill_learn":"","topic_collect":"","experience":0,"auth_code":"111000","user_collect":"","collect_times":1,"password":"110","user_id":8,"nick_name":"此地无银三百两","intro":"隔壁王五不曾偷","article_collect":"","name":"王五","portrait_url":"http://localhost:80/userPortrait/8.jpg","account":"110","longitude":""},{"qq":"","gender":"","latitude":"","skill_learn":"","topic_collect":"","experience":0,"auth_code":"111000","user_collect":"8","collect_times":0,"password":"","user_id":9,"nick_name":"","intro":"","article_collect":"","name":"","portrait_url":"","account":"119","longitude":""}]
         * information : sort success
         * status : 1
         */

        private String information;
        private String status;
        private List<UserListBean> userList;

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            /**
             * qq : 000111
             * gender : 男
             * latitude :
             * skill_learn :
             * topic_collect :
             * experience : 0
             * auth_code : 111000
             * user_collect :
             * collect_times : 1
             * password : 110
             * user_id : 8
             * nick_name : 此地无银三百两
             * intro : 隔壁王五不曾偷
             * article_collect :
             * name : 王五
             * portrait_url : http://localhost:80/userPortrait/8.jpg
             * account : 110
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

            public UserListBean(String nick_name, String intro, String portrait_url) {
                this.nick_name = nick_name;
                this.intro = intro;
                this.portrait_url = portrait_url;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getSkill_learn() {
                return skill_learn;
            }

            public void setSkill_learn(String skill_learn) {
                this.skill_learn = skill_learn;
            }

            public String getTopic_collect() {
                return topic_collect;
            }

            public void setTopic_collect(String topic_collect) {
                this.topic_collect = topic_collect;
            }

            public int getExperience() {
                return experience;
            }

            public void setExperience(int experience) {
                this.experience = experience;
            }

            public String getAuth_code() {
                return auth_code;
            }

            public void setAuth_code(String auth_code) {
                this.auth_code = auth_code;
            }

            public String getUser_collect() {
                return user_collect;
            }

            public void setUser_collect(String user_collect) {
                this.user_collect = user_collect;
            }

            public int getCollect_times() {
                return collect_times;
            }

            public void setCollect_times(int collect_times) {
                this.collect_times = collect_times;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getArticle_collect() {
                return article_collect;
            }

            public void setArticle_collect(String article_collect) {
                this.article_collect = article_collect;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPortrait_url() {
                return portrait_url;
            }

            public void setPortrait_url(String portrait_url) {
                this.portrait_url = portrait_url;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }
    }

    /**首页订单列表*/
    public static class HPOrderModel{

        /**
         * information : sort orders success
         * orders : [{"orders_id":5,"distance":0,"releaser":2,"second_category":"Java","serve":"视频","first_category":"学习","release_date":"20171123","price":"100元/一部分","intro":"","name":"JFinal","details":"系统学习后台，熟练运用框架","accepter":null,"appoint_date":"20171123","status":0},{"orders_id":6,"distance":0,"releaser":2,"second_category":"Java","serve":"视频","first_category":"学习","release_date":"20171123","price":"100元/一部分","intro":"","name":"JFinal","details":"系统学习后台，熟练运用框架","accepter":null,"appoint_date":"20171123","status":0},{"orders_id":7,"distance":0,"releaser":2,"second_category":"Java","serve":"视频","first_category":"学习","release_date":"20171123","price":"100元/一部分","intro":"","name":"JFinal","details":"系统学习后台，熟练运用框架","accepter":null,"appoint_date":"20171123","status":0},{"orders_id":9,"distance":0,"releaser":2,"second_category":"Java","serve":"视频","first_category":"学习","release_date":"20171123","price":"100元/一部分","intro":"","name":"JFinal","details":"系统学习后台，熟练运用框架","accepter":null,"appoint_date":"20171123","status":0},{"orders_id":10,"distance":0,"releaser":2,"second_category":"Java","serve":"视频视频","first_category":"学习","release_date":"20171123","price":"100元/一部分100元/一部分","intro":"","name":"JFinalJFinal","details":"系统学习后台，熟练运用框架系统学习后台，熟练运用框架","accepter":null,"appoint_date":"2017112320171123","status":0},{"orders_id":11,"distance":0,"releaser":2,"second_category":"Java","serve":"视频视频","first_category":"学习","release_date":"20171123","price":"100元/一部分100元/一部分","intro":"","name":"JFinalJFinal","details":"系统学习后台，熟练运用框架系统学习后台，熟练运用框架","accepter":null,"appoint_date":"2017112320171123","status":0},{"orders_id":12,"distance":0,"releaser":2,"second_category":"Java","serve":"视频视频","first_category":"学习","release_date":"20171123","price":"100元/一部分100元/一部分","intro":"","name":"JFinalJFinal","details":"系统学习后台，熟练运用框架系统学习后台，熟练运用框架","accepter":null,"appoint_date":"2017112320171123","status":0},{"orders_id":14,"distance":0,"releaser":8,"second_category":"Java","serve":"视频","first_category":"学习","release_date":"20171123","price":"100元/一部分","intro":"","name":"JFinal","details":"系统学习后台，熟练运用框架","accepter":null,"appoint_date":"20171123","status":0}]
         * status : 1
         */

        private String information;
        private String status;
        private List<OrdersBean> orders;

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * orders_id : 5
             * distance : 0
             * releaser : 2
             * second_category : Java
             * serve : 视频
             * first_category : 学习
             * release_date : 20171123
             * price : 100元/一部分
             * intro :
             * name : JFinal
             * details : 系统学习后台，熟练运用框架
             * accepter : null
             * appoint_date : 20171123
             * status : 0
             */

            private int orders_id;
            private int distance;
            private int releaser;
            private String second_category;
            private String serve;
            private String first_category;
            private String release_date;
            private String price;
            private String intro;
            private String name;
            private String details;
            private Object accepter;
            private String appoint_date;
            private int status;

            public OrdersBean(int orders_id, int releaser, String release_date, String price, String name, String details) {
                this.orders_id = orders_id;
                this.releaser = releaser;
                this.release_date = release_date;
                this.price = price;
                this.name = name;
                this.details = details;
            }

            public int getOrders_id() {
                return orders_id;
            }

            public void setOrders_id(int orders_id) {
                this.orders_id = orders_id;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getReleaser() {
                return releaser;
            }

            public void setReleaser(int releaser) {
                this.releaser = releaser;
            }

            public String getSecond_category() {
                return second_category;
            }

            public void setSecond_category(String second_category) {
                this.second_category = second_category;
            }

            public String getServe() {
                return serve;
            }

            public void setServe(String serve) {
                this.serve = serve;
            }

            public String getFirst_category() {
                return first_category;
            }

            public void setFirst_category(String first_category) {
                this.first_category = first_category;
            }

            public String getRelease_date() {
                return release_date;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public Object getAccepter() {
                return accepter;
            }

            public void setAccepter(Object accepter) {
                this.accepter = accepter;
            }

            public String getAppoint_date() {
                return appoint_date;
            }

            public void setAppoint_date(String appoint_date) {
                this.appoint_date = appoint_date;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }

}
