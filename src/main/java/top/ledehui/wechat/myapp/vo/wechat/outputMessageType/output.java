package top.ledehui.wechat.myapp.vo.wechat.outputMessageType;/*
 * @author  ledehui
 * @data 2022/11/1)
 * @ApiNote
 */

public class output
{
    /*
    参数	是否必须	描述
ToUserName	是	接收方帐号（收到的OpenID）
FromUserName	是	开发者微信号
CreateTime	是	消息创建时间 （整型）
MsgType	是	消息类型，文本为text
     */
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
}
