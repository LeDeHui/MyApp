package top.ledehui.wechat.myapp.vo.wechat.outputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;
import top.ledehui.wechat.myapp.vo.wechat.inputMessageType.input;

/**
 * 视频消息
 */
@Data
public class outputVideo extends output {
    /*
MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
Title	否	视频消息的标题
Description	否	视频消息的描述
     */
    public String MediaId;
    public String Title;
    public String Description;
}
