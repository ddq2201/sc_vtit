package com.ddq.sc.controller;

import com.ddq.sc.entity.StepCondition;
import com.ddq.sc.service.ConditionService;
import com.ddq.sc.repository.StepConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/steps")
public class StepController {

    @Autowired
    private StepConditionRepository stepConditionRepository;

    @Autowired
    private ConditionService conditionService;

    @GetMapping("/{stepId}/evaluate")
    public boolean evaluateStepConditions(@PathVariable int stepId) {
        // Retrieve conditions for the step
        List<StepCondition> conditions = stepConditionRepository.findByStepId(stepId);

        // Group the conditions by level (group_id)
        Map<Integer, List<StepCondition>> groupedConditions = conditions.stream()
                .collect(Collectors.groupingBy(StepCondition::getGroupId));

        // Evaluate the grouped conditions
        return conditionService.evaluateGroupedConditions(groupedConditions);
    }
}
