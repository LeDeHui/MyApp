package top.ledehui.wechat.myapp.module.wechat.service.impl;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.ledehui.wechat.myapp.constant.WeChatEventTypeConstant;
import top.ledehui.wechat.myapp.constant.WeChatMsgTypeConstant;
import top.ledehui.wechat.myapp.constant.WeChatRedisTypeConstant;
import top.ledehui.wechat.myapp.module.wechat.entity.WeChatEntity;
import top.ledehui.wechat.myapp.module.wechat.mapper.WeChatMapper;
import top.ledehui.wechat.myapp.module.wechat.service.IWeChatService;
import top.ledehui.wechat.myapp.utils.MapEntityUtil;
import top.ledehui.wechat.myapp.utils.RedisUtil;
import top.ledehui.wechat.myapp.utils.WeiXinUtil;
import top.ledehui.wechat.myapp.vo.wechat.inputMessageType.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class WeChatServiceImpl extends ServiceImpl<WeChatMapper, WeChatEntity> implements IWeChatService {
    @Value("${weixin.appid}")
    private String APPID;
    @Override
    public String doall(HttpServletRequest req) {

        // 1.解析消息
        Map<String, String> param = null;
        try {
            param = WeiXinUtil.parseRequest(req.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //todo  关键字回复&关注回复&Other
        String s;
        String event = param.get("Event");
        //判断是关注消息还是普通消息
        //普通消息
        if (event == null || event.length() == 0) {
            String msgType = param.get("MsgType");
            log.info("消息类型*******" + msgType);
            classifyMessage(param, msgType);
            // 3.回复消息
            String content = param.get("Content");

            if ("你好".equals(content)) {
                s = "hahah";
            } else if ("666".equals(content)) {
                s = "小心翻车";
            } else {
                s = "联系我WeChat:18379233301";
            }
            //return WeiXinUtil.resNews(param.get("FromUserName"), param.get("ToUserName"), s);
            return WeiXinUtil.resText(param.get("FromUserName"), param.get("ToUserName"), s);
            //return  WeiXinUtil.resImage( param.get("FromUserName"), param.get("ToUserName") ,s);
        } else {
            //订阅消息
            if (WeChatEventTypeConstant.SUBSCRIBE.equals(event)||WeChatEventTypeConstant.UNSUBSCRIBE.equals(event)) {
                //关注获取openID并存储
                followOrUnFollow(param, event);
                return WeiXinUtil.resText(param.get("FromUserName"), param.get("ToUserName"), "我还是等到你了,还好我没放弃");
            }  else if (WeChatEventTypeConstant.LOCATION.equals(event)) {
                log.info("位置信息" + param);
                //return  null;
            } else {
                log.info("普通消息" + param);
            }
        }
        return null;
    }

    @Override
    public void getRidInfo(String rid) {
        //todo  https://developers.weixin.qq.com/doc/offiaccount/openApi/get_rid_info.html
    }

    private Object getInfo(Map<String, String> param, Class<?> beanClass) {
        Object o;
        try {
            o = MapEntityUtil.mapToObject(param, beanClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    private void classifyMessage(Map<String, String> param, String msgType) {

        if (WeChatMsgTypeConstant.TEXT.equals(msgType)) {
            inputText info = (inputText) getInfo(param, inputText.class);
            log.info("文本消息内容*******" + info);
            //todo
        } else if (WeChatMsgTypeConstant.IMAGE.equals(msgType)) {
            inputImage info = (inputImage) getInfo(param, inputImage.class);
            log.info("图片消息内容*******" + info);
        } else if (WeChatMsgTypeConstant.LOCATION.equals(msgType)) {
            inputLocation info = (inputLocation) getInfo(param, inputLocation.class);
            log.info("位置信息内容*******" + info);
        } else if (WeChatMsgTypeConstant.SHORTVIDEO.equals(msgType)) {
            inputShortVideo info = (inputShortVideo) getInfo(param, inputShortVideo.class);
            log.info("短视频消息内容*******" + info);
        } else if (WeChatMsgTypeConstant.VIDEO.equals(msgType)) {
            inputVideo info = (inputVideo) getInfo(param, inputVideo.class);
            log.info("视频消息内容*******" + info);
        } else if (WeChatMsgTypeConstant.VOICE.equals(msgType)) {
            inputVoice info = (inputVoice) getInfo(param, inputVoice.class);
            log.info("语音消息内容*******" + info);
        } else {
            log.info("无法识别的内容");
        }


    }

    //关注&取消关注
    public void followOrUnFollow(Map<String, String> param, String type) {
        if (WeChatEventTypeConstant.UNSUBSCRIBE.equalsIgnoreCase(type)) {
            //取消关注删除相应的openID信息
            String fromUserName = param.get("FromUserName");
            RedisUtil.del(WeChatRedisTypeConstant.FOLLOW +APPID+":"+ fromUserName);
            log.info("用户" + fromUserName + "取消关注了");
        } else if (WeChatEventTypeConstant.SUBSCRIBE.equalsIgnoreCase(type)) {
            //关注获取openID并存储
            String fromUserName = param.get("FromUserName");
            log.info("用户" + fromUserName + "关注了");
            RedisUtil.set(WeChatRedisTypeConstant.FOLLOW + APPID+":"+ fromUserName, fromUserName);
        }
    }
}
