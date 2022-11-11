package top.ledehui.wechat.myapp.utils;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class MapEntityUtil {
    //map转java对象
    public static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {
        String jsonStr = JSONObject.toJSONString(map);
        return JSONObject.parseObject(jsonStr, beanClass);
    }

    //java对象转map
    public static Map<String, Object> objectToMap(Object obj) {
        String jsonStr = JSONObject.toJSONString(obj);
        return JSONObject.parseObject(jsonStr);
    }
}
