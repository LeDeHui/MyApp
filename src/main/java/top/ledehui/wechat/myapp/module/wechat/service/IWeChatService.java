package top.ledehui.wechat.myapp.module.wechat.service;/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */


import com.baomidou.mybatisplus.extension.service.IService;
import top.ledehui.wechat.myapp.module.wechat.entity.WeChatEntity;

import javax.servlet.http.HttpServletRequest;

public interface IWeChatService extends IService<WeChatEntity> {
    String doall(HttpServletRequest req);

    void getRidInfo(String rid);
}
