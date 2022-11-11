package top.ledehui.wechat.myapp.vo.wechat.outputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;
import top.ledehui.wechat.myapp.vo.wechat.inputMessageType.input;

/**
 * 链接消息
 */
@Data
public class outputMusic extends output {
    /*
Title	否	音乐标题
Description	否	音乐描述
MusicURL	否	音乐链接
HQMusicUrl	否	高质量音乐链接，WIFI环境优先使用该链接播放音乐
ThumbMediaId	是	缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id

     */
    public String Title;
    public String Description;
    public String MusicURL;
    public String HQMusicUrl;
    public String ThumbMediaId;

}
