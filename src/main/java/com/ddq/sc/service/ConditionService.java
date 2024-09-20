package com.ddq.sc.service;

import com.ddq.sc.entity.StepCondition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConditionService {

    // Evaluates the conditions of a step grouped by levels
    public boolean evaluateGroupedConditions(Map<Integer, List<StepCondition>> groupedConditions) {
        boolean overallResult = true;

        // Iterate through each group of conditions
        for (Map.Entry<Integer, List<StepCondition>> entry : groupedConditions.entrySet()) {
            Integer groupId = entry.getKey();
            List<StepCondition> conditions = entry.getValue();

            boolean groupResult = evaluateConditionGroup(conditions);

            // Assume top-level groups are combined using AND (can modify based on requirement)
            overallResult = overallResult && groupResult;
        }

        return overallResult;
    }

    // Evaluates conditions within a group
    private boolean evaluateConditionGroup(List<StepCondition> conditions) {
        boolean groupResult = true;
        String logicalOperator = conditions.get(0).getLogicalOperator(); // Ensure all conditions use the same operator

        for (StepCondition condition : conditions) {
            boolean conditionResult = evaluateCondition(condition);

            if ("AND".equals(logicalOperator)) {
                groupResult = groupResult && conditionResult;
            } else if ("OR".equals(logicalOperator)) {
                groupResult = groupResult || conditionResult;
            }
        }

        return groupResult;
    }

    // Evaluates individual conditions
    private boolean evaluateCondition(StepCondition condition) {
        Double infoValue = condition.getInformation().getValue();
        Double conditionValue = condition.getValue();

        switch (condition.getComparisonOperator()) {
            case ">":
                return infoValue > conditionValue;
            case "<":
                return infoValue < conditionValue;
            case ">=":
                return infoValue >= conditionValue;
            case "<=":
                return infoValue <= conditionValue;
            case "=":
                return infoValue.equals(conditionValue);
            default:
                throw new IllegalArgumentException("Invalid comparison operator: " + condition.getComparisonOperator());
        }
    }
}
