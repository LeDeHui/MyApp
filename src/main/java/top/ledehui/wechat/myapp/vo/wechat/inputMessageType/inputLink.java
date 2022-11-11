package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 链接消息
 */
@Data
public class inputLink extends input{
    /*
Title	消息标题
Description	消息描述
Url	消息链接

     */
    public String Title;
    public String Description;
    public String Url;

}
