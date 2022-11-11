package top.ledehui.wechat.myapp.vo.wechat.menu;/*
 * @author  ledehui
 * @data 2022/10/25)
 * @ApiNote
 */

import lombok.Data;

@Data
public class Button {
    private  String type;
    private  String name;
    private  Button[] sub_button;
}
