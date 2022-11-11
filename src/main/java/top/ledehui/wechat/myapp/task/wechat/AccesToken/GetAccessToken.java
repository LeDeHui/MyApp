package top.ledehui.wechat.myapp.task.wechat.AccesToken;/*
 * @author  ledehui
 * @data 2022/10/27)
 * @ApiNote
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.ledehui.wechat.myapp.constant.WeChatAPIURLConstant;
import top.ledehui.wechat.myapp.constant.WeChatRedisTypeConstant;
import top.ledehui.wechat.myapp.utils.HttpUtil;
import top.ledehui.wechat.myapp.utils.RedisUtil;

import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class GetAccessToken {

    @Value("${weixin.appid}")
    private String APPID;
    @Value("${weixin.secret}")
    private String APPSECRET;

    //每一小时获取一次access_token
    @Scheduled(cron = "0 0 */1 * * ? ")
    //@Scheduled(cron = " */10 * * * * ? ")
    public void getAccessToken() {
        // "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
        String url = WeChatAPIURLConstant.WXAPI + "token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
        log.info("请求地址信息："+url);
        Map<String, Object> stringObjectMap = HttpUtil.doGet(url);
        log.info(WeChatRedisTypeConstant.ACCEXX_TOKEN + stringObjectMap);
        String access_token = (String) stringObjectMap.get("access_token");
        Double expires_in = (Double) stringObjectMap.get("expires_in");
        getApiDomainIP(access_token);
        getCallbackIP(access_token);
        RedisUtil.set(WeChatRedisTypeConstant.ACCEXX_TOKEN + APPID, access_token, expires_in.longValue());
    }

    //获取微信服务器 IP 地址
    //1. 获取微信 API 接口 IP地址
    public void getApiDomainIP(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=" + access_token;
        log.info("请求地址信息："+url);
        Map<String, Object> stringObjectMap = HttpUtil.doGet(url);
        Object ip_list = stringObjectMap.get("ip_list");
        log.info("微信API接口IP地址:" + ip_list);
        RedisUtil.set(WeChatRedisTypeConstant.API_DOMAIN_IP + APPID, ip_list);
    }

    //获取微信服务器 IP 地址
    //2. 获取微信callback IP地址
    public void getCallbackIP(String access_token) {
        String url = WeChatAPIURLConstant.WXAPI+"getcallbackip?access_token=" + access_token;
        log.info("请求地址信息："+url);
        Map<String, Object> stringObjectMap = HttpUtil.doGet(url);
        Object ip_list = stringObjectMap.get("ip_list");
        log.info("微信callbackIP地址:" + ip_list);
        RedisUtil.set(WeChatRedisTypeConstant.CALLBACKIP + APPID, ip_list);
    }
}
