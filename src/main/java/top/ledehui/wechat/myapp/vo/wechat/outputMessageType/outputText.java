package top.ledehui.wechat.myapp.vo.wechat.outputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;
import top.ledehui.wechat.myapp.vo.wechat.inputMessageType.input;

/**
 * 文本消息
 */
@Data
public class outputText extends output {
/*
Content	是	回复的消息内容（换行：在 content 中能够换行，微信客户端就支持换行显示）
 */
    public String Content;
}
