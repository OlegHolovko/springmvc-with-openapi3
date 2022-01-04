package com.holovko.springmvc.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateOrderDTO {
    private Long id;
    private String buyerName;
    private Integer amount;
    private Integer totalPrice;
}
