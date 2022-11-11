package top.ledehui.wechat.myapp.vo.wechat.outputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;
import top.ledehui.wechat.myapp.vo.wechat.inputMessageType.input;

/**
 * 图片消息
 */
@Data
public class outputImage extends output {

    /*
  MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
     */
    public String MediaId;

}
