package com.teamProject.ezmeal.domain.restAPIDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentData {
    private List<Long> eventList;
    private String prodSummaryName;
    private Long paymentPk;
    private String paymentMethod;
}
