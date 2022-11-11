package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;


@Data
public class input {
    /*
参数	描述
ToUserName	开发者微信号
FromUserName	发送方帐号（一个OpenID）
CreateTime	消息创建时间 （整型）
MsgType	消息类型，文本为text,图片为image,语音为voice,视频为video,小视频为shortvideo,链接为link,地理位置为location
MsgId	消息id，64位整型
MsgDataId	消息的数据ID（消息如果来自文章时才有）
Idx	多图文时第几篇文章，从1开始（消息如果来自文章时才有）
     */
    public String ToUserName;
        public String FromUserName;
    public String CreateTime;
    public String MsgType;
    public String MsgId;
    public String MsgDataId;
    public String Idx;

}
