package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 语音消息
 */
@Data
public class inputVoice extends input{
    /*
    MediaId	语音消息媒体id，可以调用获取临时素材接口拉取数据。
Format	语音格式，如amr，speex等
-- 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息 XML 数据包中，增加一个 Recognition 字段
Recognition	语音识别结果，UTF8编码
     */
    public String MediaId;
    public String Format;
    public String Recognition;
}
