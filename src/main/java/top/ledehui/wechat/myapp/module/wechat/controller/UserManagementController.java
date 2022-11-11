package top.ledehui.wechat.myapp.module.wechat.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.ledehui.wechat.myapp.constant.WeChatAPIURLConstant;
import top.ledehui.wechat.myapp.constant.WeChatRedisTypeConstant;
import top.ledehui.wechat.myapp.utils.HeaderUtils;
import top.ledehui.wechat.myapp.utils.HttpUtil;
import top.ledehui.wechat.myapp.utils.RedisUtil;

import javax.websocket.server.PathParam;
import java.util.Map;

/*
 * @author  ledehui
 * @data 2022/11/10)
 * @ApiNote 用户管理模块
 */
@RestController
@RequestMapping("/UserManagement")
@Slf4j
public class UserManagementController {
    @Value("${weixin.appid}")
    private String APPID;
    //1.获取关注者列表接口 /user/get--get
    @GetMapping("/user/get")
    public void getUser(@PathParam(value = "nextOpenId") String nextOpenId) {
        String  ACCESS_TOKEN = (String) RedisUtil.get(WeChatRedisTypeConstant.ACCEXX_TOKEN + APPID);
        String url = WeChatAPIURLConstant.WXAPI+ "user/get?access_token="+ACCESS_TOKEN ;
        if (nextOpenId!=null) {
            url=url+"&next_openid="+nextOpenId;
        }
        Map<String, Object> stringObjectMap = HttpUtil.doGet(url);
        log.info("stringObjectMap==>"+stringObjectMap);
        RedisUtil.set("USER_INGO:"+nextOpenId, stringObjectMap);
    }


    //2.获取用户基本信息接口 /user/info --get


    //3.查询分组接口/groups/get--get
    //4.创建分组接口/groups/create--post
    //5.修改分组接口/groups/update--post
    //6.移动用户分组接口/groups/member/update--post
    //7.查询用户分组id接口/groups/getid--post
}
