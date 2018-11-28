package zds.dc.org.zhaodashen.model;

import java.util.ArrayList;
import java.util.List;

import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;

/**
 * @author 黩武
 * @date 2017/12/8
 */

public class ClassesModel implements IMainContracts.IClassesModel{

    List<ClassesDetailBean> classesDetailBeansList = new ArrayList<>();
    //ClassesListItem 未使用网络则不写.
    /**用来写网络访问的请求，获得服务器的数据。*/


    @Override
    public List<ClassesDetailBean> getAdapterData(){
        /**这里开启线程，并异步操作，将数据返回到*/

       /* for (int i=0;i<9;i++){
             ClassesDetailBean s = new ClassesDetailBean(null,"666");
            classesDetailBeansList.add(s);
        }*/
        return classesDetailBeansList;
    }
}
