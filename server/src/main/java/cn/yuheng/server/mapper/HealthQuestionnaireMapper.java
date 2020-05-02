package cn.yuheng.server.mapper;

import cn.yuheng.server.model.HealthQuestionnaire;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 上午9:59
 */

public interface HealthQuestionnaireMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HealthQuestionnaire record);

    int insertSelective(HealthQuestionnaire record);

    HealthQuestionnaire selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HealthQuestionnaire record);

    int updateByPrimaryKey(HealthQuestionnaire record);

    HealthQuestionnaire[] selectByUserAndTime(@Param("userid") Integer userid, @Param("begin") Date begin, @Param("end") Date end);
}