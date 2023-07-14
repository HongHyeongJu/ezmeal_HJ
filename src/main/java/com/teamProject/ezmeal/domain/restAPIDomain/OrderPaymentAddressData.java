package com.teamProject.ezmeal.domain.restAPIDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentAddressData {
    private List<Long> eventList;
    private String prodSummaryName;
    private Long paymentPk;
    private String paymentMethod;
    private Long deliveryPk;
    private  String deliveryPlace;
    private String deliveryDetail;
    private String deliveryInput;
    private String deliveryMsg;
}