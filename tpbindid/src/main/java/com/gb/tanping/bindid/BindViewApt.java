package com.gb.tanping.bindid;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * 项目名称: bind
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/8/7 8:35
 */
class BindViewApt {

    public String name ;
    public List<Method> methods;


    public BindViewApt() {
        methods = new LinkedList<>();
    }
    /**
     * 执行结果，将会执行
     * @param entity 值
     * @param params 值
     */
    public <T> void invoke(T entity, Object ...params)   {
        if (methods == null){
            return;
        }
        try {
            for (Method method : methods){
                if (method.getParameterTypes().length ==0){
                    method.invoke(entity);
                }else {
                    method.invoke(entity,params);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查找所有的點
     * @param obj
     * @return
     */
    public static HashMap<Integer,Field> analysis(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields!=null){
            HashMap hashMap = new HashMap();
            for (Field field : fields){
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations !=null || annotations.length != 0){
                    for (Annotation annotation : annotations){
                        if (annotation.annotationType().equals(BindView.class)) {
                            BindView bindView = (BindView) annotation;
                            int id = bindView.value();
                            hashMap.put(id,field);
                        }
                    }
                }
            }
            return hashMap;
        }
        return null;
    }


}
