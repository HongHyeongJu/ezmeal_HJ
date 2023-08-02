package com.teamProject.ezmeal.domain.restAPIDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundleData {
    private List<Long> ord_id;
    private List<Long> dlvar_id;
}
