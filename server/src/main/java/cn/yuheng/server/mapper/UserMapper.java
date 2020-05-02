package cn.yuheng.server.mapper;

import cn.yuheng.server.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByID(Integer id);

    User selectByEmail(String email);

    User selectByWeChatID(String wechatID);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}