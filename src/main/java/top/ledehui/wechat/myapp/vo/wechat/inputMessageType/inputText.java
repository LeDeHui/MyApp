package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 文本消息
 */
@Data
public class inputText extends input{
/*
Content	文本消息内容
 */
    public String Content;
}
