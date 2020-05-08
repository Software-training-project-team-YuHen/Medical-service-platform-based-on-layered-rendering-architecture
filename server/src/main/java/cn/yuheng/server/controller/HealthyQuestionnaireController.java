package cn.yuheng.server.controller;

import cn.yuheng.server.model.HealthQuestionnaire;
import cn.yuheng.server.service.HealthQuestionnaireService;
import cn.yuheng.server.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/8 下午6:12
 */
@RestController
public class HealthyQuestionnaireController {
    @Autowired
    private HealthQuestionnaireService healthQuestionnaireService;

    @PostMapping("/api/healthy-questionnaire/add")
    public Result add(@RequestBody HealthQuestionnaire healthQuestionnaire) {
        return Result.successOrFail(healthQuestionnaireService.add(healthQuestionnaire));
    }

    @GetMapping("/api/healthy-questionnaire/get/by-id")
    public Result<HealthQuestionnaire> get(int id) {
        return Result.successOrFail(healthQuestionnaireService.getByID(id));
    }

    @GetMapping("/api/healthy-questionnaire/get/by-id-time")
    public Result<List<HealthQuestionnaire>> get(Integer userId, Date minTime, Date maxTime) {
        return Result.successOrFail(healthQuestionnaireService.getByIdAndTime(userId, minTime, maxTime));
    }
}
