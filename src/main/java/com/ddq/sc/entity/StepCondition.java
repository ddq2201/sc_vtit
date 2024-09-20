package com.ddq.sc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "step_condition")
public class StepCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private Step step;

    @ManyToOne
    @JoinColumn(name = "information_id")
    private Information information;

    @Column(name = "comparison_operator")
    private String comparisonOperator;  // >, <, >=, <=, =

    @Column(name = "value")
    private Double value;  // Value to compare

    @Column(name = "logical_operator")
    private String logicalOperator;  // AND or OR

    @Column(name = "group_id")
    private Integer groupId;  // Grouping for parentheses
}
