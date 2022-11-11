package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 图片消息
 */
@Data
public class inputImage extends input{

    /*
    PicUrl	图片链接（由系统生成）
MediaId	图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    public String PicUrl;
    public String MediaId;

}
