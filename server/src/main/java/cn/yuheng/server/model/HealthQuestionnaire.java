package cn.yuheng.server.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 王子陶
 * @date 2020/5/2 上午9:59
 * @version 1.0
 */

/**
 * 健康调查表
 */
@Data
public class HealthQuestionnaire {
    private Integer id;

    private Date time;

    private Integer userid;

    private BigDecimal height;

    private BigDecimal weight;

    private BigDecimal waistCircumference;

    private BigDecimal bodyTemperature;

    private BigDecimal heartRate;

    private Integer lungCapacity;

    private BigDecimal bloodPressure;

    private BigDecimal bloodSugar;
}