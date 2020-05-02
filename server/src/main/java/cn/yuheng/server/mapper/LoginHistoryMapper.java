package cn.yuheng.server.mapper;

import cn.yuheng.server.model.LoginHistory;

import java.util.List;

public interface LoginHistoryMapper {
    int insert(LoginHistory record);

    int insertSelective(LoginHistory record);

    List<LoginHistory> getByUser(Integer user);
}