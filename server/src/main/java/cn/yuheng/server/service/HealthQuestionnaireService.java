package cn.yuheng.server.service;

import cn.yuheng.server.dao.HealthQuestionnaireDao;
import cn.yuheng.server.pojo.HealthQuestionnaire;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 上午9:59
 */

@Service
public class HealthQuestionnaireService {

    @Resource
    private HealthQuestionnaireDao healthQuestionnaireMapper;


    public boolean deleteByID(Integer id) {
        return 1 == healthQuestionnaireMapper.deleteByPrimaryKey(id);
    }


    public boolean add(HealthQuestionnaire record) {
        return 1 == healthQuestionnaireMapper.insert(record);
    }


    public boolean addSelective(HealthQuestionnaire record) {
        return 1 == healthQuestionnaireMapper.insertSelective(record);
    }


    public HealthQuestionnaire getByID(Integer id) {
        return healthQuestionnaireMapper.selectByPrimaryKey(id);
    }


    public boolean updateSelective(HealthQuestionnaire record) {
        return 1 == healthQuestionnaireMapper.updateByPrimaryKeySelective(record);
    }


    public boolean update(HealthQuestionnaire record) {
        return 1 == healthQuestionnaireMapper.updateByPrimaryKey(record);
    }

}
