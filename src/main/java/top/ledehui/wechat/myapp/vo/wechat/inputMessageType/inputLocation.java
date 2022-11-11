package top.ledehui.wechat.myapp.vo.wechat.inputMessageType;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import lombok.Data;

/**
 * 地理位置消息
 */
@Data
public class inputLocation extends input{
    /*
Location_X	地理位置纬度
Location_Y	地理位置经度
Scale	地图缩放大小
Label	地理位置信息
     */
    public String Location_X;
    public String Location_Y;
    public String Scale;
    public String Label;
}
