package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 小视频消息
 */
@Data
public class inputShortVideo extends input{
    /*
MediaId	视频消息媒体id，可以调用获取临时素材接口拉取数据。
ThumbMediaId	视频消息缩略图的媒体id，可以调用获取临时素材接口拉取数据。
     */
    public String MediaId;
    public String ThumbMediaId;
}
