package com.teamProject.ezmeal.domain.restAPIDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDeliveryFeeInfo {
    private Long ordId;
    private String dlvarVend;
    private String invoiceNum;
    private Integer dlvarExpense;
}
