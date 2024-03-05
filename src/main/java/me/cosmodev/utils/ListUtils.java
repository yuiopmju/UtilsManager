package me.cosmodev.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.StringBuilder;

public abstract class ListUtils {

    public static String listToString(List<Object> list){
        StringBuilder stb = new StringBuilder();

        for(int i = 0; i != (list.size() - 1); i++){
            stb.append(list.get(i));
            if((i + 1) != list.size()){
                stb.append(", ");
            }
        }

        return stb.toString();
    }


    public static List<Object> arrayToList(Object[] array){
        List<Object> rt = new ArrayList<>();

        Collections.addAll(rt, array);

        return rt;
    }
}
