package top.ledehui.wechat.myapp.module.wechat.controller;
/*
 * @author  ledehui
 * @data 2022/10/25)
 * @ApiNote
 */

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.ledehui.wechat.myapp.constant.MoLiTypeConstant;
import top.ledehui.wechat.myapp.constant.WeChatAPIURLConstant;
import top.ledehui.wechat.myapp.constant.WeChatRedisTypeConstant;
import top.ledehui.wechat.myapp.module.wechat.service.IWeChatService;
import top.ledehui.wechat.myapp.task.wechat.AccesToken.GetAccessToken;
import top.ledehui.wechat.myapp.utils.HeaderUtils;
import top.ledehui.wechat.myapp.utils.HttpUtil;
import top.ledehui.wechat.myapp.utils.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    public static final String TOKEN = "123456";

    @Autowired
    IWeChatService weChatService;

    @Value("${weixin.appid}")
    private String APPID;

    @Autowired
    GetAccessToken getAccessToken;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public void test(){
        HttpHeaders headers = HeaderUtils.MoliHeader();
        JSONObject body = HeaderUtils.MoliBody();
// 发送的内容
        body.set("content", "讲个笑话");
        HttpEntity<String> formEntity = new HttpEntity<String>(body.toString(), headers);
        JSONObject jsonObject = restTemplate.postForEntity(MoLiTypeConstant.MOLIYUNURL, formEntity, JSONObject.class).getBody();
         log.info(jsonObject.toString());
        String code = (String)jsonObject.get("code");
        log.info(code);
        if (MoLiTypeConstant.OVER.equalsIgnoreCase((String) jsonObject.get("code"))) {
            log.error("每日免费请求次数已用完，不可再使用");
        }else if (MoLiTypeConstant.SUCCESS.equalsIgnoreCase((String) jsonObject.get("code"))){
            //todo 存储
            log.info("请求成功");
        }else {
            log.error("未知错误");
        }
    }

    @GetMapping("/GetToken")
    public  void GetToken(){
        getAccessToken.getAccessToken();
    }


    /**
     *  //查询 rid 信息
     * @param rid
     */
    @GetMapping("/getRidInfo/{rid}")
    public  void getRidInfo( @PathVariable String rid) {
        weChatService.getRidInfo(rid);
    }
    /**
     * 获取用户分组
     */
    @GetMapping("/getUserGroup")
    public  void getUserGroup( ){
        String  ACCESS_TOKEN = (String) RedisUtil.get(WeChatRedisTypeConstant.ACCEXX_TOKEN + APPID);
        String url = WeChatAPIURLConstant.WXAPI+ "user/get?access_token="+ACCESS_TOKEN ;
        log.info("请求地址信息："+url);
        Map<String, Object> stringObjectMap = HttpUtil.doGet(url);
        log.info("stringObjectMap==>"+stringObjectMap);
        RedisUtil.set("USER_GROUP", stringObjectMap);
    }

    /**
     * 获取用户基本信息（包括 UnionID 机制）
     * @param openid
     */
    @GetMapping("/getUserInfo/{openid}")
    public  void getUserInfo(@PathVariable String openid){
        String  ACCESS_TOKEN = (String) RedisUtil.get(WeChatRedisTypeConstant.ACCEXX_TOKEN + APPID);
        String url =    WeChatAPIURLConstant.WXAPI+"user/info?access_token="+ACCESS_TOKEN+"&openid="+openid+"&lang=zh_CN";
        log.info("请求地址信息："+url);
        Map<String, Object> stringObjectMap = HttpUtil.doGet(url);
        log.info("stringObjectMap==>"+stringObjectMap);
        RedisUtil.set("USER_INGO:"+openid, stringObjectMap);
    }


    /**
     * 公众号菜单
     * @return
     */
    @GetMapping(value = "/setMenu",produces = "text/html;charset=utf-8")
    public String setMenu() {
        String accessToken = (String) RedisUtil.get(WeChatRedisTypeConstant.ACCEXX_TOKEN + APPID);
        log.info("accessToken----"+accessToken);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        //一级菜单
        JSONObject menu1 = new JSONObject();
        menu1.put("type", "view");
        menu1.put("name", "知识星球");
        menu1.put("url", "https://www.photosir.com/videoTeaching.html");
        jsonArray.add(menu1);
        //一级菜单
        //JSONObject menu2 = new JSONObject();
        //menu2.put("type", "miniprogram");
        //menu2.put("name", "悟空传图");
        //menu2.put("url", "http://mp.weixin.qq.com");
        //menu2.put("appid", "公众号appid");
        //menu2.put("pagepath", "小程序配置路径");
        //jsonArray.add(menu2);
        //一级菜单
        JSONObject menu3 = new JSONObject();
        menu3.put("name", "商业合作");
        JSONArray jsonArray2 = new JSONArray();
        //二级菜单
        JSONObject menu4 = new JSONObject();
        menu4.put("type", "view");
        menu4.put("name", "百度");
        menu4.put("url", "https://www.baidu.com/");
        jsonArray2.add(menu4);
        JSONObject menu5 = new JSONObject();
        menu5.put("type", "view");
        menu5.put("name", "搜搜");
        menu5.put("url", "http://www.soso.com/");
        jsonArray2.add(menu5);
        menu3.put("sub_button", jsonArray2);
        jsonArray.add(menu3);
        jsonObject.put("button", jsonArray);
        String url =  WeChatAPIURLConstant.WXAPI+"menu/create?access_token=" + accessToken;
        log.info("请求地址信息：" +url );
        log.info("jsonObject +++" +jsonObject.toJSONString(0) );
        String result = String.valueOf(HttpUtil.doPost(url, jsonObject.toJSONString(0),6000));
        System.out.println(result);
        return "设置完成";
    }

    /**
     * 公众号接受消息
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @PostMapping("/Mywechat/{appid}")
    public String echoPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        return weChatService.doall(req);


    }



    /**
     * 配置回调
     * @param appid
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     * @throws Exception
     */
    @GetMapping("/Mywechat/{appid}")
    public String WeChatToken(@PathVariable String appid,
                              @RequestParam(name = "signature", required = false) String signature,
                              @RequestParam(name = "timestamp", required = false) String timestamp,
                              @RequestParam(name = "nonce", required = false) String nonce,
                              @RequestParam(name = "echostr", required = false) String echostr) throws Exception {
        boolean result = check(timestamp, nonce, signature);
        System.out.println("appid-->" + appid);

        if (result) {
            // 3.校验成功返回echostr
            return echostr;
        }
        return echostr;
    }

    public static boolean check(String timestamp, String nonce, String signature) {
        // 1.按字典序对TOKEN, timestamp和nonce排序
        String[] arr = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        // 2.将3个参数拼成一个字符串进行sha1加密
        String str = arr[0]+arr[1]+arr[2];
        // 3.用commons-codec包中的工具类进行sha1加密
        str = DigestUtils.sha1Hex(str);
        // 4.将加密后的字符串和signature比较
        System.out.println(signature);
        return str.equalsIgnoreCase(signature);
    }
}
