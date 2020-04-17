package cn.yuheng.server.service;

import cn.yuheng.server.dao.LoginHistoryDao;
import cn.yuheng.server.pojo.LoginHistory;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/10 下午2:30
 */
@Service
public class LoginService {
    @Setter
    @Autowired
    private LoginHistoryDao loginHistoryDao;

    public boolean addLoginHistory(Integer user) {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setUser(user);
        int change = loginHistoryDao.insertSelective(loginHistory);
        return change == 1;
    }
}
