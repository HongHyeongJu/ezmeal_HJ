package com.teamProject.ezmeal.domain.restAPIDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAPIData {
    private long paymentPk;
    private int finalPrice;
    private String buyerName;
    private String phone;
    private String  email;

}
