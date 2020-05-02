package cn.yuheng.server.service;

import cn.yuheng.server.mapper.UserMapper;
import cn.yuheng.server.model.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/10 上午8:56
 */
@Service
public class UserService {
    @Autowired
    @Setter
    private UserMapper userDao;

    public User getByID(Integer id) {
        return userDao.selectByID(id);
    }

    public User getByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    public User getByWeChatID(String wechatID) {
        return userDao.selectByWeChatID(wechatID);
    }

    public boolean addUser(User user) {
        int change = userDao.insert(user);
        return change == 1;
    }

    public boolean removeUser(User user) {
        int change = userDao.deleteByPrimaryKey(user.getId());
        return change == 1;
    }

    public boolean removeUser(Integer id) {
        int change = userDao.deleteByPrimaryKey(id);
        return change == 1;
    }

    public boolean update(User user) {
        int change = userDao.updateByPrimaryKey(user);
        return change == 1;
    }

    public boolean updateSelective(User user) {
        int change = userDao.updateByPrimaryKeySelective(user);
        return change == 1;
    }
}
