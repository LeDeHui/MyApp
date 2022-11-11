package top.ledehui.wechat.myapp.utils;/*
 * @author  ledehui
 * @data 2022/11/10)
 * @ApiNote
 */

import cn.hutool.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeaderUtils {

    public static HttpHeaders MoliHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Api-Key", "3w15qzg0cfsbwbe5");
        headers.add("Api-Secret", "sw2gii9g");
        return  headers;
    }

    public static JSONObject MoliBody(){
        JSONObject body = new JSONObject();
// 发送的内容
// 消息类型，1：私聊，2：群聊
        body.set("type", "1");
        body.set("from", "消息发送者标识");
        body.set("fromName", "消息发送者昵称");
        return  body;
    }
}
