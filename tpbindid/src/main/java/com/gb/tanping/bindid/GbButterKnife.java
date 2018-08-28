package com.gb.tanping.bindid;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;


public final class GbButterKnife {


    public static <T> void bind(T target)  {
        bind(target,target);
    }

    public static <T,D> void bind(T target,D source)  {

        HashMap<Integer, Field> hashMap = BindViewApt.analysis(target);

        try {
            if (source instanceof Activity) {
                Activity activity = (Activity) target;
                initValue(target,activity.getWindow().getDecorView(), hashMap);
            } else if (source instanceof Dialog) {
                initValue(target,(Dialog) source, hashMap);
            } else if (source instanceof  View){
                initValue(target, (View) source, hashMap);
            } else if (source instanceof IGbFindView) {
                initValue(target,(IGbFindView) source, hashMap);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    private static void initValue(final Object target ,View view, HashMap<Integer, Field> hashMap) throws IllegalAccessException {
            bind(view, hashMap, new Gbind<View>() {
                @Override
                public void bind(int id, Field field, View source) throws IllegalAccessException {
                    Object obj =  source.findViewById(id);
                    field.set(target,obj);
                }
            });

    }

    private static void initValue(final Object target , final Dialog dialog, HashMap<Integer, Field> hashMap) throws IllegalAccessException {
        bind(dialog, hashMap, new Gbind<Dialog>() {
            @Override
            public void bind(int id, Field field, Dialog source) throws IllegalAccessException {
                Object obj =  dialog.findViewById(id);
                field.set(target,obj);
            }


        });
    }

    private static void initValue(final Object target, final IGbFindView gbFindView, HashMap<Integer, Field> hashMap) throws IllegalAccessException {
        bind(gbFindView, hashMap, new Gbind<View>() {
            @Override
            public void bind(int id, Field field, View source) throws IllegalAccessException {
                Object obj =  gbFindView.findView(id);
                field.set(target,obj);
            }
        });
    }

    private static <T> void bind(T source, HashMap<Integer, Field> hashMap, Gbind gbind) throws IllegalAccessException {
        if (hashMap != null) {
            Iterator<Integer> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                int id = it.next();
                Field field =  hashMap.get(id);
                gbind.bind(id,field,source);
            }
        }
    }


    interface Gbind<T> {
        void bind(int id, Field field, T source) throws IllegalAccessException;
    }
}
