package zds.dc.org.zhaodashen.model.bean;

/**
 * Created by admin on 2017/12/9.
 */

public class ClassesDetailBean{
    int classesimage;
    String classestext;
    String classesDecs;
 public ClassesDetailBean(int classesimage,String classestext,String classesDecs){
     this.classesimage=classesimage;
     this.classestext=classestext;
     this.classesDecs=classesDecs;
 }
 public int getClassesimage(){
     return  classesimage;
 }
 public String getClassestext(){
     return  classestext;
 }

    public String getClassesDecs() {
        return classesDecs;
    }
}
