package top.ledehui.wechat.myapp.module.wechat.mapper;
/*
 * @author  ledehui
 * @data 2022/10/31)
 * @ApiNote
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.ledehui.wechat.myapp.module.wechat.entity.WeChatEntity;
@Mapper
public interface WeChatMapper  extends BaseMapper<WeChatEntity> {
}
