package com.example.hello_okhttp.net;

import java.util.Map;
import java.util.Set;

/**
 * @author pikachu
 * @time 2016/7/12 11:56
 * @desc
 */
public class NetUtils {
    public static Param[] map2Params(Map<String, String> params) {
        if (params == null)
            return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }
}
