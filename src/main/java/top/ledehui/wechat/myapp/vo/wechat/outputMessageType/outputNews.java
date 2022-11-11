package top.ledehui.wechat.myapp.vo.wechat.outputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 链接消息
 */
@Data
public class outputNews extends output {
    /*
ArticleCount	是	图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
Articles	是	图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
Title	是	图文消息标题
Description	是	图文消息描述
PicUrl	是	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
Url	是	点击图文消息跳转链接
     */
    public String ArticleCount;
    public String Articles;
    public String Title;
    public String Description;
    public String PicUrl;
    public String Url;

}
