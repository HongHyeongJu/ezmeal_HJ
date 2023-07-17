package com.teamProject.ezmeal.domain.restAPIDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryData {
    private Long prodCd;
    private Integer qty;

}
