package cn.yuheng.server.mapper;

import cn.yuheng.server.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 下午4:27
 */

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByEmail(@Param("email") String email);

    User selectByWxOpenid(@Param("wxOpenid") String wxOpenid);

    User selectByWxSession(@Param("wxSession") String wxSession);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}