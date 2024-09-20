package com.ddq.sc.repository;

import com.ddq.sc.entity.StepCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepConditionRepository extends JpaRepository<StepCondition, Integer> {
    List<StepCondition> findByStepId(int stepId);
}
