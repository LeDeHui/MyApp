package top.ledehui.wechat.myapp.vo.wechat.menu;/*
 * @author  ledehui
 * @data 2022/10/25)
 * @ApiNote
 */

import lombok.Data;

@Data
public class Menu extends Button{
    private  Button[] button;
}
