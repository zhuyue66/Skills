package zds.dc.org.zhaodashen.model;

import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zds.dc.org.zhaodashen.MyApplication;
import zds.dc.org.zhaodashen.model.bean.HomePageBean;
import zds.dc.org.zhaodashen.presenter.HomePagePresenter;
import zds.dc.org.zhaodashen.webApi.SortOrdersAPI;
import zds.dc.org.zhaodashen.webApi.SortUserAPI;
import zds.dc.org.zhaodashen.webService.SortOrdersService;
import zds.dc.org.zhaodashen.webService.SortUsersService;

/**
 * @author zhuyue66
 * @date 2017/12/7
 */

public class HomePageModel {
    private String TAG = "HomePageModel";
    private HomePageBean homePageBean = new HomePageBean();

    List<HomePageBean.HPUserModel.UserListBean> userList = new ArrayList<>();//提供返回
    List<HomePageBean.HPOrderModel.OrdersBean> ordersList = new ArrayList<>();//提供返回

    public  List<HomePageBean.TopBean> getTopBean(){
        return homePageBean.getTopBeans();
    }

    public List<HomePageBean.HPUserModel.UserListBean> getPersonList() {
        // TODO: 2018/3/6  进行网络访问获取用户数据
        SortUserAPI sortUserAPI = new SortUserAPI();
        SortUsersService sortUsersService = sortUserAPI.getService();
        Call<HomePageBean.HPUserModel> response = sortUsersService.SortUsers();
        response.enqueue(new Callback<HomePageBean.HPUserModel>() {
            @Override
            public void onResponse(Call<HomePageBean.HPUserModel> call, Response<HomePageBean.HPUserModel> response) {
                //Toast.makeText(MyApplication.getContext(),"用户获取成功",Toast.LENGTH_SHORT).show();
                List<HomePageBean.HPUserModel.UserListBean> innerUserList = new ArrayList<>();
                for (int i=0; i<response.body().getUserList().size();i++){
                    Log.d(TAG, "onResponse: 姓名：" + response.body().getUserList().get(i).getNick_name());
                    HomePageBean.HPUserModel.UserListBean bean = new HomePageBean.HPUserModel.UserListBean(
                            response.body().getUserList().get(i).getNick_name(),
                            response.body().getUserList().get(i).getIntro(),
                            response.body().getUserList().get(i).getPortrait_url()
                    );
                    innerUserList.add(bean);
                }
                userList = innerUserList;//改为使用回调实现
            }

            @Override
            public void onFailure(Call<HomePageBean.HPUserModel> call, Throwable t) {
                //Toast.makeText(MyApplication.getContext(),"用户获取失败",Toast.LENGTH_SHORT).show();
            }
        });
        return userList;
    }

    public List<HomePageBean.HPOrderModel.OrdersBean> getSkillList(){
        // TODO: 2018/3/6 进行网络访问获取订单，调用ser方法与bean进行数据存储
        SortOrdersAPI sortOrdersAPI = new SortOrdersAPI();
        SortOrdersService sortOrdersService  = sortOrdersAPI.getService();
        Call<HomePageBean.HPOrderModel> response = sortOrdersService.SortOrders();
        response.enqueue(new Callback<HomePageBean.HPOrderModel>() {
            @Override
            public void onResponse(Call<HomePageBean.HPOrderModel> call, Response<HomePageBean.HPOrderModel> response) {
                //Toast.makeText(MyApplication.getContext(),"获取订单成功",Toast.LENGTH_SHORT).show();
                List<HomePageBean.HPOrderModel.OrdersBean> innerOrdersList = new ArrayList<>();
                for (int i=0; i<response.body().getOrders().size();i++){
                    Log.d(TAG, "onResponse: 订单发布者：" + response);
                     HomePageBean.HPOrderModel.OrdersBean bean = new HomePageBean.HPOrderModel.OrdersBean(
                              response.body().getOrders().get(i).getOrders_id(),
                             response.body().getOrders().get(i).getReleaser(),
                             response.body().getOrders().get(i).getRelease_date(),
                             response.body().getOrders().get(i).getPrice(),
                             response.body().getOrders().get(i).getName(),
                             response.body().getOrders().get(i).getDetails()
                     );
                     innerOrdersList.add(bean);
                }
                ordersList = innerOrdersList;//改为使用回调实现
            }

            @Override
            public void onFailure(Call<HomePageBean.HPOrderModel> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"获取订单失败",Toast.LENGTH_SHORT).show();
            }
        });
        return ordersList;
    }
}
