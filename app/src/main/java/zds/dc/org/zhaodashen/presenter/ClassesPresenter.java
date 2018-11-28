package zds.dc.org.zhaodashen.presenter;

import java.util.List;

import zds.dc.org.zhaodashen.contracts.IMainContracts;
import zds.dc.org.zhaodashen.model.ClassesModel;
import zds.dc.org.zhaodashen.model.bean.ClassesDetailBean;
import zds.dc.org.zhaodashen.ui.fragments.ClassesFragment;

/**
 * @author zhuyue66
 * @date 2017/12/6
 */

public class ClassesPresenter implements IMainContracts.IClassesPresenter {
    private IMainContracts.IClassesView iClassesView;
    private IMainContracts.IClassesModel iClassesModel;

    public ClassesPresenter(IMainContracts.IClassesView iClassesView) {
        this.iClassesView = iClassesView;
        iClassesModel = new ClassesModel();
    }
    public List<ClassesDetailBean> getAdapterData(){
        return iClassesModel.getAdapterData();
    }





}


